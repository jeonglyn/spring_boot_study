package spring_boot_study.spring_boot_study.practice;

public class ObjectPriactice {
    // 부모 클래스
    static class Parent {
        // 1. static 필드 초기화 - 클래스 로딩 시 딱 한 번
        static int staticField = printAndReturn("Parent - static 필드 초기화", 1);

        // 2. static 블록 - 클래스 로딩 시 딱 한 번
        static {
            System.out.println("Parent - static 블록");
        }

        // 3. 인스턴스 필드 초기화 - new 할 때마다
        int instanceField = printAndReturn("Parent - 인스턴스 필드 초기화", 1);

        // 4. 인스턴스 블록 - new 할 때마다
        {
            System.out.println("Parent - 인스턴스 블록");
        }

        // 5. 생성자
        Parent() {
            System.out.println("Parent - 생성자");
        }

        static int printAndReturn(String msg, int value) {
            System.out.println(msg);
            return value;
        }
    }

    // 자식 클래스
    static class Child extends Parent {
        static int staticField = printAndReturn("Child - static 필드 초기화", 1);

        static {
            System.out.println("Child - static 블록");
        }

        int instanceField = printAndReturn("Child - 인스턴스 필드 초기화", 1);

        {
            System.out.println("Child - 인스턴스 블록");
        }

        Child() {
            // 명시 안 해도 컴파일러가 자동으로 super()를 맨 앞에 넣어줌
            System.out.println("Child - 생성자");
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 첫 번째 객체 생성 시작 =====");
        new Child();

        System.out.println("\n===== 두 번째 객체 생성 시작 =====");
        new Child(); // static은 다시 안 나옴 (이미 로딩됨), 인스턴스 부분만 반복
    }
}
