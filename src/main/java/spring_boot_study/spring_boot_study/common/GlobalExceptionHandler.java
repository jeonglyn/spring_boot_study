package spring_boot_study.spring_boot_study.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// @RestControllerAdvice :모든 RestController에서 던져지는 예외를 여기서 처리
// 컨트롤러 하나하나에 try-catch를 넣지 않아도 되는 이유이다
public class GlobalExceptionHandler {
    // 1) 서비스 로직에서 직접 던진 IllegalArgumentException처리
    //    - ex) "존재하지 않는 물품입니다" (findById 실패 예시)
    //    - 클라이언트 잘못이 아니라 해당 리소스를 못 찾음에 가까우니 404로 매핑한다
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.NOT_FOUND.value(),
                                                       "Not Found",
                                                       // 서비스에서 넣어준 메시지를 활용
                                                       e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // 2) @Valid 검증 실패 시 Spring이 자동으로 던지는 예외 처리
    //    - 컨트롤러 파라미터에 @Valid ProductRequestDto가 있고, @NotBlank 같은게 실패하면 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        // 검증 실패한 필드가 여러 개일수도 있으니, 첫 번째 에러 메시지만 사용
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("입력값이 올바르지 않습니다.");

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "Validation Failed", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 3) 위에서 처리하지 못한 모든 예외의 최종 방어선
    //    - 예상 못한 NullPointerException, RuntimeException 등이 여기로 온다
    //    - 절대 e.getMessage()를 그대로 노출하지 않는다
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "서버 내부 오류가 발생하였습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
