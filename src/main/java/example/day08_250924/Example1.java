package example.day08_250924;

// 관점지향 프로그래밍 AOP
// 특정 func이 실행되면 특수한 func이 함께 실행됨

class TestService {

    // [1]
    public void enter1(){
        System.out.println("식당 입장");
    } // func end
    
    // [2]
    public void enter2(){
        System.out.println("학원 입장");
    } // func end

} // class end

public class Example1 {
    public static void main(String[] args) {
        TestService testService = new TestService();

        testService.enter1();
        testService.enter2();

    } // main end
} // class end
