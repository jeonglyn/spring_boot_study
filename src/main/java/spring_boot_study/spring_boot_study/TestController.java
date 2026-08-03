package spring_boot_study.spring_boot_study;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotBlank;

// 로드맵 1번 실습: Boot 2.x -> 3.x 변경점 + Bean Validation 기초
// @Validated 가 클래스에 있어야 @RequestParam 에 붙은 @NotBlank 가 실제로 동작함
@RestController
@RequestMapping("/api/members")
@Validated
public class TestController {

    @GetMapping("/hello")
    public String answer() {
        return "Hello, Spring Boot 4.1.1!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam @NotBlank String name) {
        String greeting = "안녕하세요, " + name + "!";
        return greeting;
    }
}
