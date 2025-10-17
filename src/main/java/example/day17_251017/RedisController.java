package example.day17_251017;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    // [4] 개별 삭제

    // [5] 개별 수정


} // class end
