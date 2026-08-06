// 구조화된 동시성 (Structured Concurrency)
// - 여러 비동기 작업을 수행할 때 '하나의 메서드 안에서 시작된 작업들은 모두 같은 메서드 안에서 끝난다' 는 구조적 규칙을 강제하는 API

// 기존 방식(ExecutorService, Future)의 문제
// - 비동기 연쇄 작업을 실행하다가 하나의 작업이 실패해도, 다른 형제 스레드들은 이를 인지 못하고 계속 실행 (자원 낭비)

// 자바 25의 해결책
// - 여러 하위 작업을 하나의 큰 스코프로 묶어서 관리한다!
// - Joiner 인터페이스 디자인을 반영
//      - Joiner.allSuccessfulOrThrow()         : 하위 작업 중 하나라도 실패하면 다른 모든 작업을 즉시 취소하고 예외를 던짐
//      - Joiner.anySuccessfulResultOrThrow()   : 여러 서버에 동일한 요청을 보낸 후, 가장 빨리 성공한 작업의 결과만 취하고, 나머진 즉시 취소

package spring_boot_study.spring_boot_study.practice.java25;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructureConcurrencyPractice {

    // Scoped Value 선언: static final 필드로 선언하고 private 접근 제한을 두는 게 관례
    // ThreadLocal의 static final 필드 선언 관례와 똑같음 - 다른 클래스가 직접 못 건드리게
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) throws InterruptedException {

        // ScopedValue.where(값을 담을 변수, 실제 값).run(실행할 코드)
        // run() 블록 안에서만 REQUEST_ID.get()으로 값을 읽을 수 있고,
        // 블록이 끝나면 바인딩이 자동으로 해제됨 (ThreadLocal처럼 remove() 안 해도 됨)
        ScopedValue.where(REQUEST_ID, "REQ-" + System.currentTimeMillis())
                .run(StructureConcurrencyPractice::processOrder);
    }

    // 이 메서드는 run()이 호출한 콜백이라, 그 안에서는 REQUEST_ID가 바인딩된 상태
    private static void processOrder() {
        System.out.println("[" + REQUEST_ID.get() + "] 주문 처리 시작");

        try {
            // StructuredTaskScope.open() : 기본 Joiner(하나라도 실패하면 즉시 취소)로 스코프를 염
            // try-with-resources라서 블록이 끝나면 scope.close()가 자동 호출됨
            try (var scope = StructuredTaskScope.open()) {

                // fork()로 던진 작업은 각각 새로운 Virtual Thread에서 실행됨
                // 이 시점에 REQUEST_ID 바인딩이 자식 Virtual Thread에도 자동으로 전파됨
                Subtask<String> productTask = scope.fork(StructureConcurrencyPractice::fetchProductInfo);
                Subtask<Integer> stockTask = scope.fork(StructureConcurrencyPractice::checkStock);

                // join() : 두 작업이 모두 끝날 때까지 대기
                // 기본 Joiner라서, 만약 둘 중 하나라도 예외를 던지면
                // 다른 작업도 즉시 취소되고 join()이 예외를 던짐
                scope.join();

                // 여기까지 왔다는 건 둘 다 성공했다는 뜻 - 이제 안전하게 결과를 꺼내 씀
                String product = productTask.get();
                int stock = stockTask.get();

                System.out.println("[" + REQUEST_ID.get() + "] 상품: " + product + ", 재고: " + stock + "개");

            } // scope.close() 자동 호출 - 혹시 남은 자식 스레드가 있으면 여기서 확실히 정리됨

        } catch (Exception e) {
            // fork한 작업 중 하나가 실패하면 join()에서 여기로 예외가 전달됨
            System.out.println("[" + REQUEST_ID.get() + "] 주문 처리 실패: " + e.getMessage());
        }
    }

    // 상품 정보를 조회하는 작업 (실제로는 DB나 외부 API 호출이라고 가정)
    private static String fetchProductInfo() throws InterruptedException {
        // 자식 Virtual Thread 안에서도 REQUEST_ID.get()이 부모가 바인딩한 값과 동일하게 읽힘
        System.out.println("[" + REQUEST_ID.get() + "] 상품 정보 조회 중... (스레드: " + Thread.currentThread() + ")");
        Thread.sleep(300); // 네트워크 호출을 흉내내는 지연
        return "스프링 부트 완전정복";
    }

    // 재고를 확인하는 작업
    private static int checkStock() throws InterruptedException {
        System.out.println("[" + REQUEST_ID.get() + "] 재고 확인 중... (스레드: " + Thread.currentThread() + ")");
        Thread.sleep(200);
        return 17;
    }
}
