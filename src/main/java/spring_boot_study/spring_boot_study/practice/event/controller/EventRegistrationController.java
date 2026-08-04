package spring_boot_study.spring_boot_study.practice.event.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import spring_boot_study.spring_boot_study.common.ApiResponse;
import spring_boot_study.spring_boot_study.practice.event.dto.EventRegistrationRequestDto;

// 검증 로직 자체를 실습하는 게 목적이라, Service/Repository 없이 컨트롤러에서 바로 응답합니다.
// @Valid만 통과하면 성공, 실패하면 GlobalExceptionHandler가 처리합니다.
@RestController
@RequestMapping("/api/events")
public class EventRegistrationController {

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EventRegistrationRequestDto>> register(
            @Valid @RequestBody EventRegistrationRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(requestDto, "신청이 접수되었습니다."));
    }
}
