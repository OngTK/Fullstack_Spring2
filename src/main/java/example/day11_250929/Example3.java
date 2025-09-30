package example.day11_250929;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example3 {

    public static void main(String[] args) {

        // [ 메소드 레퍼런스 API ]
        // 형태 : ` 클래스명 :: 메소드명 `
        // 이미 정의된 메소드를 참조하여 사용 (== 추상메소드는 불가능)
        // 메소드의 주소값을 참조하여 사용하는 방식으로 람다 표현식을 활용
        // 정적 메소드·static 메소드에만 가능
        //      정적 메소드 : 객체(인슽넌스)없이 사용 가능한 메소드
        //      <-> 멤버·일반 메소드 : 객체를 통해 사용 가능한 메소드
        // [ 정석 : 객체명.메소드명() ] vs [ 메소드 레퍼런스 : 객체명 :: 메소드명 ]

        // [1] static 정적 메소드
        // String 타입을 parseInt로 int 타입으로 바꾸기
        System.out.println(Integer.parseInt("123"));

        Function<String, Integer> func = Integer::parseInt;
        System.out.println( func.apply("1234") );

        // [2] 일반 메소드
        List<String> names = List.of("배두훈","강형호","조민규","배두훈");

        // [2.1] 기존 for 문 (향상 for)
        System.out.println("[2.1]==============");
        for (String name : names){
            System.out.println(name);
        }

        // [2.2] 람다식 forEach
        System.out.println("[2.2]==============");
        names.stream().forEach( name -> System.out.println(name) );

        // [2.3] method reference
        System.out.println("[2.3]==============");
        names.stream().forEach( System.out :: println );    // 매개변수는 자동으로 인식
    
        // [3] 생성자 메소드 레퍼런스
        // 클래스명::new
        // new : 생성자
        names.stream().forEach(name -> new Member(name) );
        names.stream().forEach( Member::new );

        List<Member> newMember = names.stream().map(Member::new).collect(Collectors.toList());
        System.out.println(newMember);
    
    } // main end

} // class end

class Member {
    String name;

    // 생성자
    Member(String name){this.name = name; } 
} // class end