package spring_boot_study.spring_boot_study.practice.point.service;

import org.springframework.stereotype.Service;
import spring_boot_study.spring_boot_study.practice.point.domain.Point;
import spring_boot_study.spring_boot_study.practice.point.dto.PointResponseDto;
import spring_boot_study.spring_boot_study.practice.point.exception.PointNotEnoughException;
import spring_boot_study.spring_boot_study.practice.point.repository.PointRepository;

/*
 * ===== 실습 안내 =====
 * 아래 두 메서드에 TODO로 표시된 부분을 직접 채워보세요.
 * 완성되면 코드를 Claude에게 보여주고 리뷰를 받으세요.
 *
 * [실습 1] getPoint(memberId)
 *   - 존재하지 않는 memberId로 조회하면 IllegalArgumentException을 던지세요.
 *   - Product 실습(ProductService.getProduct)에서 썼던 것과 같은 패턴입니다.
 *
 * [실습 2] usePoint(memberId, amount)
 *   - 먼저 회원을 조회하세요. (존재하지 않으면 실습 1과 동일하게 처리)
 *   - amount가 현재 balance보다 크면, 직접 작성한 PointNotEnoughException을 던지세요.
 *   - 문제 없으면 balance에서 amount를 뺀 새 Point를 만들어 저장(update)하고 반환하세요.
 *
 * [실습 3] GlobalExceptionHandler
 *   - common/GlobalExceptionHandler.java 에 @ExceptionHandler(PointNotEnoughException.class)를
 *     추가해서 409(Conflict) 상태 코드로 응답하도록 만드세요.
 *   - IllegalArgumentException 핸들러는 이미 있으니, PointService에서 IllegalArgumentException을
 *     던지면 자동으로 404가 나가는지 Postman이나 curl로 확인만 해보면 됩니다.
 */
@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public PointResponseDto getPoint(Long memberId) {
        // TODO: pointRepository.findByMemberId(memberId)를 사용해서
        //       존재하지 않으면 IllegalArgumentException을 던지고,
        //       존재하면 PointResponseDto.from(...)으로 변환해서 반환하세요.
        Point point = pointRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원번호 입니다: " + memberId));
        //throw new UnsupportedOperationException("TODO: getPoint를 구현하세요.");
        return PointResponseDto.from(point);
    }

    public PointResponseDto usePoint(Long memberId, Long amount) {
        // TODO:
        // 1) 회원 조회 (없으면 IllegalArgumentException)
        Point point = pointRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원번호 입니다:" + memberId));


        // 2) amount > balance 이면 PointNotEnoughException
        if(point.getBalance() < amount) {
            throw new PointNotEnoughException("포인트 잔액이 부족합니다. 잔액: " + point.getBalance());
        }

        // 3) 문제 없으면 새 Point(memberId, balance - amount)를 만들어 repository.update() 후 반환
        Point updated = new Point(point.getMemberId(), point.getBalance() - amount);

        Point saved = pointRepository.update(updated);
//        throw new UnsupportedOperationException("TODO: usePoint를 구현하세요.");

        return PointResponseDto.from(saved);
    }
}
