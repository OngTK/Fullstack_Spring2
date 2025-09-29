package example.day11_250929;

import java.util.List;
import java.util.stream.Collectors;

public class Example2 {
    public static void main(String[] args) {

        // 임의 List
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        // [1] Stream() + forEach()
        // forEach() : 결과 반환 X
        // 중간 연산 없음
        numbers.stream().forEach( x -> System.out.println("[1] forEach : " + x ) );

        // [2] Stream() + map() + collect()
        // 매개변수를 하나씩 대입하고 결과를 return하는 반복문
        // map() : 중간연상 O, 결과 반환 O
        // collect( Collectors.to타입 ) : 타입변환 + 최종 출력
        List< Integer > newNumbers = numbers.stream().map(x -> x*2 ).collect( Collectors.toList() );
        System.out.println("[2] map + collect" + newNumbers);




    } // main end
} // func end

// [Stream]
//  데이터가 다니는 통로
//  스트림 API : 데이터(매개변수) --> 중간연산(sort/max/min) --> 최종출력