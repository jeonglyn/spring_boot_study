package spring_boot_study.spring_boot_study.member.domain;

import java.time.LocalDateTime;

// 도메인(엔티티) 클래스: DB 테이블과 매핑될 데이터의 본체
// 아직 JPA를 배우지 않았으므로 @Entity 없이 순수 자바 객체(POJO)로 작성
public class Member {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    // 생성 시점에 필수 값만 받는 생성자
    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    // getter만 제공 (도메인 객체는 함부로 값이 바뀌지 않도록 setter를 최소화하는 편이 안전)
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // 이름 변경처럼 의미 있는 동작만 메서드로 노출 (무분별한 setter 대신)
    public void changeName(String newName) {
        this.name = newName;
    }
}
