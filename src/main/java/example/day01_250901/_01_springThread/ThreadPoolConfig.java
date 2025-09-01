package example.day01_250901._01_springThread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration      // Spring Container 내에 bean 객체 생성 // IOC
public class ThreadPoolConfig {

    // [1] 스프링 멀티스레드 Setting
    @Bean // 메소드를 컨테이너 빈에 등록하는 어노테이션
    public Executor taskExcutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);    // 기본·최소 실행 스레드 수 설정
        executor.setMaxPoolSize(3);    // 최대 실행 스레드 수 설정
        executor.setQueueCapacity(20);  // 최대 대기 스레드 수 // 대기 20 초과 시, 503 오류 발생
        executor.initialize();          // 스레드 풀 초기화 = 서버 재실행 마다 초기화
        return executor;
    } // func end

} // class end
