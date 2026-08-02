package spring_boot_study.spring_boot_study.practice.point.domain;

// 회원의 포인트 잔액을 표현하는 도메인 객체
public class Point {
    private final Long memberId;
    private final Long balance; // 현재 잔액

    public Point(Long memberId, Long balance) {
        this.memberId = memberId;
        this.balance = balance;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getBalance() {
        return balance;
    }
}
