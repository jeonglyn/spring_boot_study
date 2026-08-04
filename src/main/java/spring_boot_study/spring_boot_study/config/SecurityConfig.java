package spring_boot_study.spring_boot_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration          // 이 클래스 안에 @Bean으로 등록된 것들이 있으니, 읽어서 빈을 등록하라는 뜻!
@EnableWebSecurity      // Spring Security 설정을 활성화하는 어노테이션
public class SecurityConfig {

    // TODO 1: PasswordEncoder를 빈으로 등록하세요.
    // - BCryptPasswordEncoder를 사용합니다.
    // - 왜 필요한가? 아래 TODO 2에서 계정 비밀번호를 인코딩할 때 이 빈을 사용하게 됩니다.
    // 힌트: @Bean 메서드 하나 작성, 반환 타입은 PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // TODO 2: 테스트용 계정 2개를 등록하는 UserDetailsService 빈을 작성하세요.
    // - InMemoryUserDetailsManager를 사용합니다. (DB 없이 메모리에 사용자 정보를 저장하는 구현체)
    // - 계정 1: username "user", password "1234", role "USER"
    // - 계정 2: username "admin", password "1234", role "ADMIN"
    //
    // 힌트: User.builder()
    //           .username("...")
    //           .password(passwordEncoder.encode("..."))  ← 위에서 만든 PasswordEncoder로 인코딩!
    //           .roles("...")
    //           .build();
    //
    //      두 UserDetails를 new InMemoryUserDetailsManager(user1, user2) 로 감싸서 반환
    //
    // 주의: 이 메서드 파라미터로 PasswordEncoder를 받아야 합니다.
    //      (스프링이 위에서 등록한 PasswordEncoder 빈을 자동으로 주입해줍니다 — 이것도 힌트!)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {



        return  new InMemoryUserDetailsManager(user1, user2);
    }


    // SecurityFilterChain을 빈으로 등록하는 것이 6.x 방식의 핵심이다.
    // 이전 버전에서는 클래스를 상속해서 configure()를 오버라이드해서 사용했다고 한다...(안써봐서 모르겠다..ㅎ)
    // 지금은 HttpSecurity 객체를 받아서 조립한 뒤 build()로 반환하기만 하면 된다!
    // HttpSecurity : 필터 체인을 어떻게 구성할지 설정하는 빌더 역할
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ===CSRF 설정===
                // - CSRF(Cross-Site Request Forgery)는 브라우저 세션 쿠키를 이용한 위조 요청을 막는 기능이다.
                // REST API는 보통 세션 쿠키 대신 JWT 같은 토큰을 쓰기 때문에 CSRF 공격 대상이 아니라고 함
                // 그래서 REST API 서버에서는 관례적으로 CSRF를 꺼둔다고 한다.
                .csrf(csrf -> csrf.disable())

                // ===세션 정책===
                // STATELESS : 서버가 세션을 아예 만들지도, 사용하지도 않겠다는 뜻.
                // 회사에서 사용하는 HttpSession을 만들어서 서버 메모리에 기억해두는 방법은 STATEFUL

                // 로그인 상태를 서버 메모리(HttpSession)에 두지 않고, 매 요청마다 토큰으로 인증하는 구조(JWT)에서 필수!
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // ===인가 규칙===
                // Lamda DSL 방식이며, 위에서 아래로 순서대로 매칭되므로, 더 구체적인 규칙을 먼저 써야한다.
                // TODO 3: 이번 실습 시나리오에 맞게 경로를 바꿔주세요.
                // - /api/public/** 는 인증없이 누구나 접근 가능 (permitAll)
                // - /api/admin/** 은 ADMIN 권한을 가진 사용자만 접근 가능 (hasRole)
                // - 그 외 나머지 모든 요청은 인증(로그인)만 되어 있으면 접근 가능 (authenticated)
                .authorizeHttpRequests(auth -> auth
                        // 여기에 requestMatchers(...) 규칙 작성
                )

                // ===HTTP Basic 인증===
                // TODO 4: 이번엔 Postman에서 Basic Auth로 테스트할 예정이라 꺼두면 안 됩니다.
                // disable() 대신 Customizer.withDefaults() 를 사용하세요.
                // (formLogin은 REST API에 필요 없으니 계속 꺼둔 채로 둡니다.)
                .httpBasic(basic -> basic.disable())
                .formLogin(form -> form.disable());

        return http.build();
    }
}
