package example.day11_250929;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Example2 {
    public static void main(String[] args) {

        // 임의 List
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        // [1] stream() + forEach()
        // forEach() : 결과 반환 X
        // 중간 연산 X, 최종 출력 O
        numbers.stream().forEach( x -> System.out.println("[1] forEach : " + x ) );

        // [2] stream() + map() + collect()
        // 매개변수를 하나씩 대입하고 결과를 return하는 반복문
        // map() : 중간연상 O, 결과 반환 O
        // collect( Collectors.to타입 ) : 타입변환 + 최종 출력
        List< Integer > newNumbers = numbers.stream().map(x -> x*2 ).collect( Collectors.toList() );
        System.out.println("[2] map + collect" + newNumbers);

        // [3] stream() + map() + forEach()
        numbers.stream()                            // 스트림 시작
                .map( x -> x * 2 )          // 중간 연산
                .forEach(x -> System.out.println( "[3] "+ x )); // 최종 출력

        // [4] stream() + filter() + 최종출력
        numbers.stream()
                .filter(x -> x % 2 == 0 )
                .forEach( x -> System.out.println("[4] " + x )); // 최종 출력

        // [5] stream() + sorted() + 최종출력
        // 정렬
        numbers.stream()
                .sorted()                           // default 오름차순
                .forEach(x -> System.out.println("[5.1] " +  x));

        numbers.stream()
                .sorted(Comparator.reverseOrder())           // 내림차순
                .forEach(x -> System.out.println("[5.2] " +  x));

        // [6] stream() + distinct() + 최종 출력
        // 중복제거
        List<Integer> newList2 = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("[6] stream() + distinct() " + newList2);

        // [7] stream() + limit( 개 수 ) + 최종출력
        // 처음부터 N개의 데이터를 제한
        numbers.stream().limit(5).forEach(x -> System.out.println("[7] " + x));

        // [8.1] stream() + reduce( 초기값, (누적값, 현재값) -> 연산 )
//        int sum = numbers.stream().reduce(0, (누적값, 현재값) -> 누적값 + 현재값 )
        int sum = numbers.stream().reduce(0, (x, y) -> x + y );
        System.out.println("[8.1] " + sum);

        // [8.2] 누적곱
        int product = numbers.stream().reduce(1, (x,y) -> x*y);
        System.out.println("[8.2] " + product);
        
        // [8.3] 최소값
        // 초기값 : Integer.MAX_VALUE : 최대값
        // x : 이전 값
        // y : 현재값
        int min = numbers.stream().reduce(Integer.MAX_VALUE, (x, y ) -> x < y ? x : y);
        System.out.println("[8.3] "+min);

        // [8.4] 최대값
        int max = numbers.stream().reduce(0 , (x,y) -> x>y ? x : y);
        System.out.println("[8.4] " + max);
    } // main end
} // func end

// [Stream]
//  데이터가 다니는 통로
//  스트림 API : 데이터(매개변수) --> 중간연산(sort/max/min) --> 최종출력