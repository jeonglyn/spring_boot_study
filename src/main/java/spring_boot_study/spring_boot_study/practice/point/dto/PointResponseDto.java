package spring_boot_study.spring_boot_study.practice.point.dto;

import spring_boot_study.spring_boot_study.practice.point.domain.Point;

// 클라이언트에게 내려줄 포인트 조회/사용 결과
public record PointResponseDto(Long memberId, Long balance) {

    public static PointResponseDto from(Point point) {
        return new PointResponseDto(point.getMemberId(), point.getBalance());
    }
}
