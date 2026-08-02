package spring_boot_study.spring_boot_study.practice.point.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_study.spring_boot_study.common.ApiResponse;
import spring_boot_study.spring_boot_study.practice.point.dto.PointRequestDto;
import spring_boot_study.spring_boot_study.practice.point.dto.PointResponseDto;
import spring_boot_study.spring_boot_study.practice.point.service.PointService;

// 이번 실습의 핵심은 Service/Exception 쪽이라, 컨트롤러는 3번 학습 때 배운 패턴 그대로 제공합니다.
@RestController
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<PointResponseDto>> getPoint(@PathVariable Long memberId) {
        PointResponseDto data = pointService.getPoint(memberId);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PostMapping("/{memberId}/use")
    public ResponseEntity<ApiResponse<PointResponseDto>> usePoint(
            @PathVariable Long memberId,
            @Valid @RequestBody PointRequestDto requestDto) {
        PointResponseDto data = pointService.usePoint(memberId, requestDto.amount());
        return ResponseEntity.ok(ApiResponse.success(data, "포인트가 사용되었습니다."));
    }
}
