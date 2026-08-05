package spring_boot_study.spring_boot_study.practice;

/*
* Java 버전 및 LTS 여부를 확인하는 간단한 유틸리티 클래스
* */

public class JavaVersionChecker {
    public static void main(String[] args) {
        Runtime.Version version = Runtime.version();

        System.out.println("=== 현재 실행 환경 정보 ===");
        // feature() : 주 버전 번호 (예: 21, 25)
        System.out.println("Feature 버전: " + version.feature());
        // 전체 버전 문자열 (예: 25.0.1+9-LTS)
        System.out.println("전체 버전: " + version);

        // system property로도 확인 가능 (더 상세한 벤더 정보 포함)
        System.out.println("java.version: " + System.getProperty("java.version"));
        System.out.println("java.vendor: " + System.getProperty("java.vendor"));
        System.out.println("java.vm.name: " + System.getProperty("java.vm.name"));

        // LTS 여부는 JDK API로 직접 확인 불가 → 알려진 LTS 목록과 비교하는 방식으로 판단
        int[] ltsVersions = {8, 11, 17, 21, 25};
        boolean isLts = false;
        for (int lts : ltsVersions) {
            if (lts == version.feature()) {
                isLts = true;
                break;
            }
        }
        System.out.println("LTS 버전 여부: " + (isLts ? "예 (LTS)" : "아니오 (STS)"));


    }
}
