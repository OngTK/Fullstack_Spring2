package example.day08_250924;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
        System.out.println("[코로나] 열 체크 [4.1] AOP check1");
    }

    // [4.2]
    @After("execution( * AopService.*(..) )")
    public void check2(){
        System.out.println("[퇴장] [4.2] AOP check2");
    }

    // [4.3]
    @After("execution( * example.day08_250924.AopService.enter1(..))")
    public void check3(){
        System.out.println("[4.3] AOP check 3");
    }

    // [4.4]
    // && args(name)  매개변수 인자값을 바인딩할 이름 정의
    @Before("execution( boolean example.day08_250924.AopService.enter3(String) ) && args(name) ")
    public void check4( String name ){
        System.out.println("[4.4] AOP check 4 " + name);
    }

    // [4.5]
    // @AfterReturning( value = " " ,returning = "result") : service의 return 값을 반환 받을 수 있음
    // result에 결과값으로 바인딩할 이름을 정의
    @AfterReturning(value = "execution( boolean example.day08_250924.AopService.enter3(..))", returning = "result")
    public void check5( boolean result ){
        System.out.println("[4.5] AOP returning " + result);
    }
    
    // [4.6]
    // @Around : 직접 서비스 메소드 실행 시점을 지정할 수 있음
    // return 타입이 * 이므로 이를 담을 수 있는 object
    @Around("execution ( * example.day08_250924.AopService.enter3(..))")
    public Object check6(ProceedingJoinPoint joinPoint) throws Throwable {
        // [4.6.1] AOP 객체확인
        System.out.println("joinPoint = " + joinPoint);
        // [4.6.2] AOP 메소드명
        System.out.println(joinPoint.getSignature());
        // [4.6.3] 실행 메소드의 매개변수 확인 > 배열로 반환
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        // [4.6.4] 실행할 메소드를 직접 실행 = 실행 시점 커스텀
        // + 실행결과 반환 받기
        Object result = joinPoint.proceed();    // 예외처리 필수
        System.out.println(result);

        return result; // 실행결과 return 값을 그대로 return
    }

}

// [3] service
@Service
class AopService {
    public void enter1(){
        System.out.println("AopService.enter1");
        System.out.println("학원 입장");
    }

    public void enter2(){
        System.out.println("AopService.enter2");
        System.out.println("식당 입장");
    }

    public boolean enter3( String name ){
        System.out.println("AopService.enter3");
        System.out.println("[3.3] 헬스장 입장");
        return true;
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
        aopService.enter3("유재석");
    }
} // class end

// [1] main class
@SpringBootApplication
public class Example2 {
    public static void main(String[] args) {
        SpringApplication.run(Example2.class);
    }
}

