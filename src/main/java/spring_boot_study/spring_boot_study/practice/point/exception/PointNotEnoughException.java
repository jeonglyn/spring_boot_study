package spring_boot_study.spring_boot_study.practice.point.exception;

// TODO: 여러분이 직접 작성해보세요.
//
// 이 예외는 "사용하려는 포인트가 잔액보다 많을 때" PointService에서 던질 커스텀 예외입니다.
//
// 요구사항
// 1. RuntimeException을 상속하세요. (Checked Exception이 아닌 이유: 호출부마다 강제로 catch/throws를
//    선언하게 만들고 싶지 않고, GlobalExceptionHandler가 대신 잡아줄 것이기 때문)
// 2. 문자열 message를 받는 생성자를 만들고, 부모(RuntimeException) 생성자에 그대로 전달하세요.
//
// 힌트 - PointService에서 이렇게 사용할 예정입니다.
//   throw new PointNotEnoughException("포인트 잔액이 부족합니다. 현재 잔액: " + balance);

public class PointNotEnoughException extends RuntimeException {
    // TODO: message를 받는 생성자를 작성하세요.
    // 여기서 record를 쓸 수 없었던 이유
    // -> Java는 클래스 하나가 부모를 하나만 가질 수 있다.
    //    근데 해당 클래스는 이미 RuntimeException을 상속하고 있으며,
    //    record의 경우 이미 내부적으로 java.lang.Record 라는 걸 상속하고 있다.
    //    따라서 record는 사용할 수 없었다.
    public PointNotEnoughException(String message) {
        super(message);
        // super는 부모 클래스의 생성자를 호출하라는 뜻
    }



}
