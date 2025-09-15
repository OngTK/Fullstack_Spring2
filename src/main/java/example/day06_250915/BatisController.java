package example.day06_250915;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/day06/batis")
public class BatisController {

    // [0] mapper 객체 DI
    private final BatisMapper batisMapper;

    // [1] create
    public void create(){} // func end

    // [2] readAll
    @GetMapping
    public ResponseEntity<List<StudentDto>> readAll(){
        // batisMapper interFace의 readAll SQL문을 실행한 결과를 반환
        List<StudentDto> result = batisMapper.readAll();

        // ResponseEntity 응답객체로 반환
        return ResponseEntity.status(200).body(result);
    } // func end

    // [3] read
    public void read(){} // func end

    // [4] update
    public void update(){} // func end

    // [5] delete
    public void delete(){} // func end


} // class end
