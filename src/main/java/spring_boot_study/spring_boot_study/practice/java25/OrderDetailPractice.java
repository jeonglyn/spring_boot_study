// OrderDetailPractice.java
package spring_boot_study.spring_boot_study.practice.java25;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class OrderDetailPractice {

    // TODO 1: ORDER_ID라는 이름의 ScopedValue<String>을 선언하세요.
    private static final ScopedValue<String> ORDER_ID = ScopedValue.newInstance();


    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== 정상 케이스 ===");
        // TODO 2: ScopedValue.where(...)로 "ORD-1001"이라는 값을 ORDER_ID에 담아서
        //         run()으로 processOrderDetail()을 실행하세요.
        ScopedValue.where(ORDER_ID, "ORD-1001").run(() -> {
           processOrderDetail();
        });


        System.out.println();
        System.out.println("=== 실패 케이스 ===");
        // TODO 3: 이번엔 "INVALID-001"이라는 값으로 똑같이 실행해보세요.
        //         (fetchPaymentHistory 안에서 이 값이면 예외를 던지도록 아래에서 구현할 예정입니다)
        ScopedValue.where(ORDER_ID, "INVALID-001").run(() -> {
           processOrderDetail();
        });

    }

    private static void processOrderDetail() {
        // TODO 4: 시작 시각을 기록하세요 (System.currentTimeMillis())
        //         → 나중에 "순차 실행이었다면 600ms였을 텐데, 실제로는 얼마나 걸렸는지" 비교할 용도입니다.
        long startTime = System.currentTimeMillis();

        System.out.println("시작 시간 : " + startTime);

        try {
            try (var scope = StructuredTaskScope.open()) {

                // TODO 5: 아래 3개 메서드를 각각 fork()로 던지세요.
                // fetchCustomer(), fetchShippingStatus(), fetchPaymentHistory()
                // 각각의 리턴 타입에 맞게 Subtask<String> 변수로 받아두세요.
                Subtask<String> fetchCustomer = scope.fork(OrderDetailPractice::fetchCustomer);
                Subtask<String> fetchShippingStatus = scope.fork(OrderDetailPractice::fetchShippingStatus);
                Subtask<String> fetchPaymentHistory = scope.fork(OrderDetailPractice::fetchPaymentHistory);


                // TODO 6: scope.join()으로 셋 다 끝날 때까지 기다리세요.
                scope.join();

                // TODO 7: 세 결과를 각각 .get()으로 꺼내서 한 줄로 출력하세요.
                // 예: "고객: OOO / 배송: OOO / 결제: OOO"
                String customer = fetchCustomer.get();
                String shippingStatus = fetchShippingStatus.get();
                String paymentHistory = fetchPaymentHistory.get();

                System.out.println("고객 : " + customer + " / 배송: " + shippingStatus + " / 결제: " + paymentHistory);

                // TODO 8: 끝난 시각을 기록해서, 걸린 시간(ms)을 출력하세요.
                // 600ms에 훨씬 못 미치는지 직접 확인해보세요.
                long endTime = System.currentTimeMillis();

                // 절대 시각 두 개를 따로 찍는 대신, 차이값(걸린 시간)을 바로 계산해서 보여줌
                System.out.println("걸린 시간 : " + (endTime - startTime) + "ms");
            }
        } catch (Exception e) {
            // TODO 9: 실패했을 때 ORDER_ID와 함께 실패 사유를 출력하세요.
            // scope.join()이 던지는 예외는 StructuredTaskScope.FailedException(포장지)이라서,
            // e.getMessage()는 포장지 자체의 메시지일 뿐 진짜 실패 원인이 아님.
            // 진짜 원인은 e.getCause() 안에 들어있으므로 getCause().getMessage()로 꺼내야 함.
            System.out.println("[" + ORDER_ID.get() + "] 실패 사유 : " + e.getCause().getMessage());
        }
    }

    // 고객 정보 조회 - 150ms 지연
    private static String fetchCustomer() throws InterruptedException {
        // TODO 10: 로그 찍고(ORDER_ID 포함), Thread.sleep(150), "김철수" 같은 값 반환
        System.out.println("[" + ORDER_ID.get() + "] 고객명 조회 중...(스레드: " + Thread.currentThread() + ")");
        Thread.sleep(150);
        return "김영희";
    }

    // 배송 상태 조회 - 250ms 지연
    private static String fetchShippingStatus() throws InterruptedException {
        // TODO 11: 로그 찍고, Thread.sleep(250), "배송중" 같은 값 반환
        System.out.println("[" + ORDER_ID.get() + "] 배송상태 조회 중...(스레드: " + Thread.currentThread() + ")");
        Thread.sleep(250);
        return "배송중";
    }

    // 결제 내역 조회 - 200ms 지연
    // 단, ORDER_ID가 "INVALID"로 시작하면 예외를 던져야 함
    private static String fetchPaymentHistory() throws InterruptedException {
        // TODO 12: ORDER_ID.get()이 "INVALID"로 시작하는지 확인해서
        //          맞으면 RuntimeException을 던지고, 아니면 정상적으로 값을 반환하세요.
        System.out.println("[" + ORDER_ID.get() + "] 결제내역 조회 중...(스레드: " + Thread.currentThread() + ")");
        Thread.sleep(200);

        // 자기가 던진 예외를 바로 잡아서 다시 감싸 던지던 불필요한 try-catch 제거.
        // 조건문 하나로 바로 던지되, 나중에 실패 사유를 알 수 있도록 메시지를 담아줌.
        boolean isInvalid = ORDER_ID.get().startsWith("INVALID");
        if (isInvalid) {
            throw new RuntimeException("잘못된 주문번호입니다: " + ORDER_ID.get());
        }

        return "결제완료";
    }
}
