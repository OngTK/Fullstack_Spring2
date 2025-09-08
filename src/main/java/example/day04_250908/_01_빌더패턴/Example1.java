package example.day04_250908._01_빌더패턴;

import lombok.Builder;
import lombok.ToString;

import java.lang.reflect.Member;

/**
 * DTO 생성자의 규칙 <p>
 *
 * <p> - 생성자는 미리 정해진 매개변수 대해서만 사용 가능
 * <p> 즉, 유연성이 떨어짐
 * <p>
 * <p> Builder Pattern
 *
 * @author OngTK
 * @since 2025-09-08
 */

// [1] 실행 클래스
public class Example1 {
    public static void main(String[] args) {

        // [1.1] 생성자 : 객체 생성 메소드
        MemberDto m1 = new MemberDto();      // 기본 생성자

        MemberDto m2 = new MemberDto("배두훈", 40);        // 풀 생성자

        // [3] 생성자의 규칙 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
        // [3.1] 존재하지 않는 생성자는 사용 불가
        // MemberDto m3 = new MemberDto("강형호");
        // [3.2] 매개변수의 순서를 바꾸면 안됨
        // MemberDto m4 = new MemberDto(35, "조민규");
        
        // 즉, 생성자는 미리 정해진 매개변수에 대해서만 사용 가능 >> 유연성이 떨어짐

        // [4] Builder Pattern ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
        // 생성자 유뮤와 상관없이 메소드를 통해 객체를 초기화·생성 하는 방법
        // 생성자를 유연하게 사용할 수 있음
        MemberDto m5 = MemberDto.builder().build();
        System.out.println("m5 = " + m5);

        MemberDto m6 = MemberDto.builder().name("고우림").build();         // .builder().속성명(속성값).build();
        System.out.println("m6 = " + m6);

        MemberDto m7 = MemberDto.builder().name("박강현").age(36).build();
        System.out.println("m7 = " + m7);


    } // main end
} // class end

// [2] 설계 클래스
@ToString
@Builder            // Builder Pattern ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
class MemberDto {
    // [2.1] 멤버변수
    private String name;
    private int age;

    // [2.2] 생성자                >> Lombok 으로 대체해서 사용 @NoArgsConstructor / @AllArgsConstructor
    // [2.2.1] 기본 생성자
    public MemberDto() { }

    // [2.2.2] 풀 생성자
    public MemberDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // [2.3] 메소드

} // class end