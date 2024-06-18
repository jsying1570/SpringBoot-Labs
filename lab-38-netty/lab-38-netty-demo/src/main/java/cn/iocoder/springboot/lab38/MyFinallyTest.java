package cn.iocoder.springboot.lab38;

public class MyFinallyTest {
    public static void main(String[] args) {
        String message = message();
        System.out.println(message);
    }

    private static String message() {
        try {
            int res = 1 / 1;
        } catch (Exception e) {
            return "world";
        } finally {
            System.out.println("=======");
        }
        System.out.println("MyFinallyTest.message");
        System.out.println("**********************");
        return "hello";
    }
}
