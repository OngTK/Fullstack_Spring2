package example.day01_250901._02_SpringScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    // [1] 3초마다 자동 실행하는 서비스 메소드
    @Scheduled( fixedRate = 3000 )
    // (unit : milli-sec)
    // value 자리에 변수 사용 x , 상수(final)은 사용 가능
    public void task1(){
        System.out.println("[ 3초 ] ScheduleService.task1");
    } // func end
    // 비동기방식, 멀티스레드 사용 >> background에서 작동

    // [2] 5초마다 자동 실행하는 서비스 메소드
    @Scheduled(fixedRate = 5000)
    public void task2(){
        System.out.println("[ 5초 ] ScheduleService.task2");
    } // func end

    // [3] 시스템 날짜/시간 기준으로 자동 실행 스케쥴링
    // 시스템의 매 시간 (X:X:05)가 될 때마다 자동 실행되는 메소드
    // unit : sec min hr day month week
    @Scheduled( cron = "0/5 * * * * *")
    // */5 : 5초 마다 실행
    // 0/5 : *분 5초가 될때 마다 실행
    public void task3(){
        System.out.println(" [ 5초 ] ScheduleService.task3");
    }

    @Scheduled( cron = "0 */1 * * * *")
    // 매 1분 0초 마다 실행
    public void task4(){
        System.out.println(" [ 1분 ] ScheduleService.task3");
    }

} // class end
