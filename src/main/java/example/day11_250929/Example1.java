package example.day11_250929;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// [2.0] interface 생성
interface Calculator{
    int plus(int x , int y);
} // inter end

public class Example1 {

    // [1.0] static 메소드 정의
    public static int plus(int x, int y ){
        return x+y;
    } // func end

    public static void main(String[] args) {

        // [1] 일반 메소드 호출
        int result = plus(3,5); // 1.0은 static method 이므로 객체 생성이 필요 없음
        System.out.println("[1] "+result);

        // [2] 인터페이스 추상메소드 호출
        // 구현체 or 익명구현체
        // 익명구현체 (1회성) : new 인터페이스(){구현};
        Calculator calc = new Calculator(){
            @Override
            public int plus(int x, int y) {
                return x+y;
            }
        };
        int result2 = calc.plus(3,5);
        System.out.println("[2] "+result2);

        // [3] 람다식 익명구현체, Java 8 이상 // ( ) -> { }
        Calculator calc2 = ( x , y ) ->  x + y ;
        int result3 = calc2.plus(3,5);
        System.out.println("[3] "+result3);

        // [4] 람다표현식을 사용하는 함수형 인터페이스
        // [4.1] Function < T, R >
        // T·입력을 받아 R·결과를 반환
        // 인스턴스(객체) 생성 시 인스턴스(객체) 내 멤버변수의 타입을 정의
        // apply(T) 메소드를 사용
        Function< Integer, Integer > function = x -> x*2;
        System.out.println("[4.1] " + function.apply(3));

        // [4.2] Supplier < T > : 입력 없이 T·결과를 반환
        // .get(T)
        Supplier<Double> supplier = () -> Math.random();
        System.out.println("[4.2] " + supplier.get());

        // [4.3] Consumer < T > : 입력은 있으나, 결과가 없음
        // .accept(T) :
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("안녕하세요.");

        // [4.4] Predicate< T > : T·입력을 받아 결과를 True·False로 반환
        // .test(T)
        Predicate<Integer> predicate = x -> x%2 ==0 ;
        System.out.println("[4.4] " + predicate.test(4));
        
    } // main end
} // class end
