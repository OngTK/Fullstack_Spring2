package example.day05_250909._01_HTTP응답객체;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> HTTP 응답객체
 * <p> ResponseEntity< T >
 * @since 20250909
 * */

@RestController
@RequestMapping("/task/day05")
public class ResponseController {

    // [1]
    @GetMapping("/bool")
    // Asis : public boolean task1(){ return false; }
    public ResponseEntity<Boolean> task1(){
        System.out.println("ResponseController.task1");

        return ResponseEntity.status(401).body(false);
        // 401 : 인증 실패
    } // class end

    /*
    * boolean : 기본타입
    * Boolean : 참조타입
    *   * Wrapper Class : 기본타입을 참조타입으로 사용하는 클래스
    *       ex) int > Integer
    * */

    // [2]
    @GetMapping("/int")
    public ResponseEntity<Integer> task2(){
        System.out.println("ResponseController.task2");

        return ResponseEntity.status(202).body(3);
        // 202 : 요청 성공 + server 처리중(비동기 멀티 스레드)
    } // func end

    // [3]
    @GetMapping("/String")
    public ResponseEntity<String> task3(){
        System.out.println("ResponseController.task3");

        return ResponseEntity.status(201).body("test중");
        // 201 : 요청성공 + 저장 성공 + String을 반환
    } // func

    // [4]
    @GetMapping("/void")
    public ResponseEntity<Void> task4(){
        System.out.println("ResponseController.task4");
        return ResponseEntity.status(403).build();
        // 403 : 권한 없음
        // build() : void 는 다른 타입과 다르게 build로 종료 = 반환 값 없음
    } // func end

    // [5]
    @GetMapping("/object")
    public ResponseEntity<Map<String,String>> task5(){
        System.out.println("ResponseController.task5");
        try{
            Integer.parseInt("a"); //강제로 예외발생 >> catch 로 500 이 출력됨
            Map<String, String > map = new HashMap<>();
            map.put("keykey","valuevalue");
            return ResponseEntity.status(200).body(map);
            // 200 : 성공
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
            // 500 : 서버오류
        }
        
        
    } // func end

} // class end

