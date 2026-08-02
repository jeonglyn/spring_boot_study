package spring_boot_study.spring_boot_study.practice.point.repository;

import org.springframework.stereotype.Repository;
import spring_boot_study.spring_boot_study.practice.point.domain.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 인메모리 저장소 - Product 실습 때와 동일한 패턴 (Map 기반)
// 서버 시작 시 회원 3명에게 미리 포인트를 넣어둔다 (테스트 편의용)
@Repository
public class PointRepository {

    private final Map<Long, Point> store = new HashMap<>();

    public PointRepository() {
        store.put(1L, new Point(1L, 10000L));
        store.put(2L, new Point(2L, 500L));
        store.put(3L, new Point(3L, 0L));
    }

    public Optional<Point> findByMemberId(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    // 잔액을 새 값으로 갱신 (기존 값을 덮어씀)
    public Point update(Point point) {
        store.put(point.getMemberId(), point);
        return point;
    }
}
