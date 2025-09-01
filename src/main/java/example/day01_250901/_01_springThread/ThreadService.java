package example.day01_250901._01_springThread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    public int thread1() {
        int result = 0 ;
        for(int i = 1 ; i <= 10 ; i ++ ){
            result += i;
            // ☆★ If 서비스 처리가 늦어진다면? ☆★☆★
            try{
                System.out.println("ThreadService.thread1"+ result);
                Thread.sleep(1000);         // thread 1초 정지
                // 1초 * 10번 >> 10초 후 응답처리됨
            } catch (Exception e){
                System.out.println("ThreadService.thread1 " +e);
            }

        } // for end
        return result;
    } // func end

    @Async      // 비동기화 Annotation
    // AppStart의 @EnableAsync 와 세트
    // 단, Thread.sleep으로 결과 처리 전, 비동기로 결과를 선반환 후 service 내에서 연산은 진행
    public void thread2(){
        int result = 0 ;
        for(int i = 1 ; i <= 10 ; i ++ ){
            try{
                result += i;
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("ThreadService.thread2 " + e);
            }
        }
    } // func end

} // class end
