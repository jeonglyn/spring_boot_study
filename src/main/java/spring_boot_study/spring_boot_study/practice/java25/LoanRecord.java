package spring_boot_study.spring_boot_study.practice.java25;

import module java.base;

//  요구사항
//
//  1. LoanRecord는 LibraryTransaction이라는 부모 클래스를 상속받습니다. 부모는 transactionId(거래 ID, 문자열) 필드 하나를 가집니다.
//  2. LoanRecord는 다음 필드를 추가로 가집니다:
//          bookTitle (책 제목)
//          borrowerName (대출자 이름)
//          dueDays (대출 기간, 일 단위)
//  3. LoanRecord의 생성자에서 super() 호출 전에 (Flexible Constructor Bodies 활용) 다음을 검증하세요:
//          dueDays가 1 이상 30 이하가 아니면 IllegalArgumentException
//          bookTitle 또는 borrowerName이 비어있으면(isBlank()) IllegalArgumentException
//  4. 파일 상단에 import module java.base;를 사용해서 개별 import를 대체해보세요. (LocalDate 등을 대출일/반납예정일 계산에 활용해보시면 좋아요.)
//  5. main()에서 다음 3가지를 테스트하세요:
//          정상 케이스 1개
//          검증 실패 케이스: 대출 기간 범위 초과
//          검증 실패 케이스: 이름이 비어있음
//
//  힌트 - super() 이전에는 this를 참조할 수 없으니, 파라미터(dueDays, bookTitle, borrowerName) 자체만 가지고 검증하세요.
//      - transactionId는 예를 들어 "TXN-" + System.currentTimeMillis() 같은 식으로 생성해서 super(transactionId)에 넘기면 됩니다.
//      - 검증에 실패하면 애초에 객체가 생성되지 않는다는 걸 try-catch로 확인해보세요.

public class LoanRecord extends LibraryTransaction {
    private String bookTitle;
    private String borrowerName;
    private int dueDays;

    public LoanRecord(String transactionId, String bookTitle, String borrowerName, int dueDays) {
        boolean dueDayCheck = ((dueDays >= 1) && (dueDays <= 30));
        boolean borrowerNameCheck = !borrowerName.isBlank();
        boolean bookTitleCheck = !bookTitle.isBlank();

        if(!dueDayCheck) {
            throw new IllegalArgumentException("대출 기간은 1일 이상 30일 이하여야 합니다! 대출 기간: " + dueDays);
        } else if(!borrowerNameCheck) {
            throw new IllegalArgumentException("대출자 이름은 필수입니다!: " + borrowerName);
        } else if(!bookTitleCheck) {
            throw new IllegalArgumentException("대출할 책 제목은 필수 입니다!: " + bookTitle);
        }

        super(transactionId);

        this.bookTitle = bookTitle;
        this.borrowerName = borrowerName;
        this.dueDays = dueDays;

    }

    public static void main(String[] args) {
        String transactionId = "TXN-" + System.currentTimeMillis();

        // 정상 통과
        try {
            new LoanRecord(transactionId, "어린 왕자", "철수", 7);
        }  catch (Exception e) {
            System.out.println("생성 실패: " + e.getMessage());
        }


        // 일수 초과
        try {
            new LoanRecord(transactionId, "가시고기", "미영", 33);
        }  catch (Exception e) {
            System.out.println("생성 실패: " + e.getMessage());
        }


        // 책 제목 없음
        try {
            new LoanRecord(transactionId, " ", "영희", 30);
        }  catch (Exception e) {
            System.out.println("생성 실패: " + e.getMessage());
        }

    }
}
