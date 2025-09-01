package example.day01_250901._02_SpringScheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling       // 스케쥴링 활성화 어노테이션
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    } // main end
} // class end
