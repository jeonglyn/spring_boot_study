package spring_boot_study.spring_boot_study.practice.java25;

//* 범위 지정 값(Scope Values)
//  - 자바 8의 문제점 : 스레드 내부에서 전역 변수처럼 쓰고 싶을 때 ThreadLocal을 사용했음
//                    하지만 ThreadLocal은 값 변경이 가능해서 다른 곳에서 덮어쓸 위험이 있고,
//                    작업이 끝나고 remove() 를 안해주면 스레드 풀에 값이 남아 메모리 누수가 발생했음
//
//  - 자바 25의 해결책 : Scoped Values는 딱 지정된 영역(Scope) 안에서만 데이터가 살아있고, 불변의 데이터
//                     영역을 벗어나면 자바가 알아서 데이터를 지워줘서 메모리 누수 걱정도 없음

// * 특징 : 불변성, 자동 생명주기 관리

public class ScopeValuesPractice {
    // 예시
    // ScopedValude 선언 (static final로 선언해야함)
    private static final ScopedValue<String> name = ScopedValue.newInstance();

    public static void main(String[] args) {
        // 특정 스코프 내에서 값 바인딩
        ScopedValue.where(name, "6학년 3반").run(() -> {
            System.out.println(name.get()); // "6학년 3반" 출력됨
        });

        // 블록이 끝나면 자동으로 해제 -> 메모리 누수 없음
    }
}
