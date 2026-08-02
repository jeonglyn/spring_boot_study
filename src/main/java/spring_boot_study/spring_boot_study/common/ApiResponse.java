package spring_boot_study.spring_boot_study.common;

// 모든 API 응답을 감싸는 공통 포맷
// success: 요청 성공 여부, data: 실제 데이터, message: 부가 메시지
public record ApiResponse<T>(boolean success, T data, String message) {

    // 성공 - 데이터만 있고 메시지는 없는 경우
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // 성공 - 메시지까지 같이 주고 싶은 경우
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }

    // 실패 - data는 없고, 실패 사유만 message에 담음
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }
}