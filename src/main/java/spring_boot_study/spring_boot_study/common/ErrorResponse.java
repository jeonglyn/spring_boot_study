package spring_boot_study.spring_boot_study.common;

import java.time.LocalDateTime;

// 예외 발생 시 클라이언트에게 내려줄 공통 에러 응답 포맷
// RFC 7807(Problem Details)의 핵심 필드(status, title, detail)를 참고해서 작성
public record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
    // status  : HTTP 상태 코드 (404, 400, 500 등)
    // error   : 에러 요형 요약 (Not Found, Validation Failed)
    // message : 이번 요청에서 구체적으로 무엇이 잘못됐는지
    // timestamp : 에러 발생 시각 (로드 추적용)

    // 정적 메서드 - status, error, message만 넘기면 timestamp은 자동
    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(status, error, message, LocalDateTime.now());
    }
}
