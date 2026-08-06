package spring_boot_study.spring_boot_study.practice.java25;

import module java.base;

public class Java25LanguagePractice {
    /*
     *
     * JAVA 25 버전에선 이전에 공부했던 21버전 기준 여러가지 개선되었다고 하는데, 그중 핵심 3가지만 알아보자.
     *
     * 1) Compact Source Files and Instance Main Methods
     * => 한줄 요약 : Java 프로그램을 처음 배울때 마주치는 왜 public class랑 static을 붙여야하지 라는 장벽을 없애줌..(나도..몰랐는데..)
     * */

    // 기존 방식
    public class HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello World");
        }
    }

    // 초보자 입장에선 public, class, static, void, String[] args를 몰라서 그냥 이렇게 쓰는거야 라고 생각한다
    // 하지만 java 25에선
    void main() {
        IO.println("Hello World");
    }

    // class 선언 자체가 없음! -> 파일 하나가 통째로 이름 없는 클래스 취급을 할 수 있다
    // main()에 public, static, String[] args가 다 빠짐. 이걸 instance main method라 부른다
    // 그리고 System.out.println 대신 Io.println 이라는 클래스를 사용한다


    // 2) Module Import Declarations
    // import 문 개수를 줄여주는 편의 기능이다.
    // 예를 들면, 기존에는 import java.util.List; import java.util.Map; 이런식이나 import java.util.* 식으로 사용함
    // 하지만 지금은 import module java.base; 한줄로 해결이 가능하다


    // 3) Flexible Constructor Bodies
    // 생성자에서 부모 생성자 호출(super()) 보다 먼저 코드를 쓸 수 있게 규칙을 완화하였다.
    // 기존 방식
    public class CreditCard {
        private final String cardNumber;

        public CreditCard(String cardNumber) {
            // 컴파일 에러! super() 보다 먼저 다른 코드가 오면 안됨
            if(cardNumber == null || cardNumber.isEmpty()) {
                throw new IllegalArgumentException("Card number cannot be null or empty");
            }
            super();
            this.cardNumber = cardNumber;
        }
    }

    // 그래서 실무에서는 우회 패턴을 주로 사용하였음
    public class CreditCard2 {
        private final String cardNumber;

        public CreditCard2(String cardNumber) {
            super();
            this.cardNumber = cardNumber;
        }

        // 검증 로직을 생성자 밖, static 헬퍼 메서드로 빼야 했음
        private static String validdate(String cardNumber) {
            if(cardNumber == null || cardNumber.isEmpty()) {
                throw new IllegalArgumentException("Card number cannot be null or empty");
            }
            return cardNumber;
        }
    }

    // 하지만 JAVA25 버전부터는
    public class CreditCard3 {
        private final String cardNumber;

        public CreditCard3(String cardNumber) {
            if(cardNumber == null || cardNumber.isEmpty()) {
                throw new IllegalArgumentException("Card number cannot be null or empty");
            }
            super();
            this.cardNumber = cardNumber;
        }
    }

    // 이렇게 검증 로직을 따로 빼지 않고 생성자 안에서 사용할 수 있게 되었다!
    // 하지만 제약이 하나 있는데, super() 이전 코드에서는 this(만들어지고 있는 객체 자신)을 참조할 수 없다!
    // 쉽게 이야기하자면, this는 왼쪽에 대입받는 대상으로만 사용가능!, 오른쪽에서 읽히거나 호출되는 대상으로는 사용할 수 없다는 말이다
    // ex) this.value = input; 허용 / int x = this.value; 금지  / this.doSomething();  금지
}
