package example.day17_251017;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    // [0] redis 접근 객체
    private final RedisTemplate redisTemplate;
    private final RedisTemplate<String,Object> dtoRedisTemplate;


    // [1] 간단한 텍스트를 레디스 서버에 저장·호출
    // 원래는 서비스 단에서 레디스와 통신
    @GetMapping("/test")
    public ResponseEntity<?> test1(){
        System.out.println("RedisController.test1");

        // [1.1] Redis에 저장하기
        // redisTemplate.opsForValue().set(key, value);
        // map과 같이 key value 타입으로 set

        redisTemplate.opsForValue().set("유재석", "90"); // 임의 데이터
        redisTemplate.opsForValue().set("강호동", "80"); // 임의 데이터

        // [1.2] Redis에서 호출하기
        Set<String> keys = redisTemplate.keys("*");    // 모든 키 반환

        List<Object> list = new ArrayList<>();

        for(String key : keys){
            System.out.println(key);
            System.out.println(redisTemplate.opsForValue().get( key ));
            list.add(redisTemplate.opsForValue().get( key ));     // 특정 키에 대한 value 반환
        }

        return ResponseEntity.ok(list);
    }// func end

    // example-day13 / practice-day06 를 DB 없이 레디스로 실습 변환

    private final RedisTemplate<String,Object> studentTemplate;

    // [1] 등록
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto){
        System.out.println("studentDto = " + studentDto);
        // [1.1] 임의의 key
        String key = "student:"+studentDto.getSno();    // student:1 / student:2

        // [1.2] Redis에 전달 받은 값을 저장
        studentTemplate.opsForValue().set(key, studentDto);
        // { "student:1" : { ..dto... } }
        return ResponseEntity.ok().body("[저장성공]");
    } // func end

    // [2] 전체 조회
    @GetMapping
    public ResponseEntity<?> findAll(){
        // [2.1] 조회할 키를 모두 가져옴
        Set<String> keys = studentTemplate.keys("student:*");

        // [2.2] 반복문을 통해 value 추출
        List<Object> result = new ArrayList<>();
        for(String key : keys){
            result.add(studentTemplate.opsForValue().get(key));
        }
        return ResponseEntity.ok(result);
    } // func end

    // [3] 개별 조회
    @GetMapping("/find")
    public ResponseEntity< ? > find(int sno) {

        // [3.1] 호출할 키 생성
        String key = "student:"+sno;
        // [3.2] value 호출
        Object result = studentTemplate.opsForValue().get(key);

        return ResponseEntity.ok(result);
    }

    // [4] 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(int sno){

        // [4.1] 삭제할 키 생성
        String key = "student:"+sno;

        // [4.2] 특정 key를 이용한 엔트리 삭제
        // 템플릿객체명.delete(key)
        boolean result = studentTemplate.delete(key);

        return ResponseEntity.ok(result);
    }

    // [5] 개별 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto){
        // [5.1] 키 생성
        String key = "student:"+studentDto.getSno();    // student:1 / student:2

        // [5.2] value 삽입
        studentTemplate.opsForValue().set(key, studentDto);

        return ResponseEntity.ok("[수정성공]");
    }

    //==========================================
    // * 인증코드 발급해서 레디스 유호기간 정하기
    // TTL : 레디스에 저장된 엔트리를 특정 기간/시간이 되면 자동 삭제하는 기능

    // [1] 인증코드 발급
    @GetMapping("/auth/send")
    public ResponseEntity<?> authSend(@RequestParam String phone){
        // [1.1] key 구성 "auth:연락처"
        String key = "auth:" + phone;
        // [1.2] 인증을 위한 난수 6자리
        String code = String.format("%06d", new Random().nextInt(999999));

        // [1.3] redis에 인증코드 저장 + TTL 유효 시간 설정
        // redisTemplate.opsForValue().set( key, value, Duration.of단위(숫자));
        redisTemplate.opsForValue().set(key, code, Duration.ofSeconds(10));

        // [1.4] API를 통해 연락처로 인증번호 송신
        // 생략
        return ResponseEntity.ok().body("[인증코드 발급 완료] : " + code);
    }

    // [2] 인증하기
    @GetMapping("/auth/confirm")
    public ResponseEntity<?> authConfrim(@RequestParam String phone, @RequestParam String code){
        // [2.1] key 구성 "auth:연락처"
        String key = "auth:" + phone;

        // [2.2] 인증번호 확인하기
        Object authNum = redisTemplate.opsForValue().get(key);

        if( authNum == null ){
            return ResponseEntity.ok().body("[인증 실패] 코드를 발급받지 않았거나, 코드가 완료됨");
        } else if( authNum.equals(code) ){
            // 인증 성공시 인증번호가 담긴 엔트리 삭제
            redisTemplate.delete(key);
            return ResponseEntity.ok().body("[인증 성공]");
        } else {
            return ResponseEntity.ok().body("[인증 실패]");
        }
    }

} // class end
