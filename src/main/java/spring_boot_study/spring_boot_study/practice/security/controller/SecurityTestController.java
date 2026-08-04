package spring_boot_study.spring_boot_study.practice.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    // 인증 없이 누구나 호출 가능해야 하는 엔드포인트
    @GetMapping("/api/public/hello")
    public String publicHello() {
        return "공개 API 입니다. 누구나 호출 가능해야 합니다.";
    }

    // 로그인(인증)만 되어 있으면 호출 가능해야 하는 엔드포인트
    @GetMapping("/api/user/hello")
    public String userHello() {
        return "인증된 사용자 전용 API 입니다.";
    }

    // ADMIN 권한을 가진 사용자만 호출 가능해야 하는 엔드포인트
    @GetMapping("/api/admin/hello")
    public String adminHello() {
        return "관리자 전용 API 입니다.";
    }
}
