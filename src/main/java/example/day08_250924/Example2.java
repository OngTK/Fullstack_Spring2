package example.day08_250924;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 [AOP, 관점지향 프로그래밍]
 1) 정의
 부가 기능을 하나로 모듈화하여 핵심 비즈니스 로직을 분리
 2) 사용처
 로그(기록), 트랜잭션, 보안(인증/권한)

*/

// [4] AOP 커스텀
@Aspect         // 스프링 AOP container에 등록
@Component      // 스프링 bean container 등록
class AopClass{
    // [4.1] App > AopService 내 모든 메소드가 실행되면 같이 실행
    // @Before("execution( 경로 · 조건) ")
    // @Before("execution( * AopService.*(..) ) ")
    // 해석
    // * : 모든 리턴 타입의 메소드
    // java 이하의 경로 표시
    //      동일 패키지 : 클래스명만 작성
    //      다른 패키지 : java 이하의 경로 표시
    // .메소드명
    //      해당 class 내에 모든 메소드
    // ( 매개변수 )
    // (..) : 해당 메소드의 모든 매개변수에 적용
    @Before("execution( * AopService.*(..) ) ")
    public void check1(){
        System.out.println("[코로나] 열 체크");
    }

    // [4.2]
    @After("execution( * AopService.*(..) )")
    public void check2(){
        System.out.println("[퇴장]");
    }
}

// [3] service
@Service
class AopService {
    public void enter1(){
        System.out.println("학원 입장");
    }

    public void enter2(){
        System.out.println("식당 입장");
    }
}

// [2] controller
@RestController
class AopController {

    @Autowired
    AopService aopService;
    @GetMapping("day08/aop")
    public void method(){
        System.out.println("AopController.method");
        aopService.enter1();
        aopService.enter2();
    }
} // class end

// [1] main class
@SpringBootApplication
public class Example2 {
    public static void main(String[] args) {
        SpringApplication.run(Example2.class);
    }
}

