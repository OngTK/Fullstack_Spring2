package example.day01_250901._01_springThread;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/day01")
@RequiredArgsConstructor        // final 변수에 대한 자동 생성자 지원
public class ThreadController {

    private final ThreadService threadService;

    // 스프링 매핑 컨트롤러는 자동 [ 동기화 ] 처리
    // 먼저 요청이 들어온 HTTP의 처리 및 결과 반환 후 다음 요청을 처리 == 순차처리
    @GetMapping
    public int thread1(){
        System.out.println("ThreadController.thread1");
        return threadService.thread1();
    } // func end

    // service에서 @Async로 비동기화 처리
    @DeleteMapping
    public void thread2(){
        System.out.println("ThreadController.thread2");
    } // func end
    
} // class end
