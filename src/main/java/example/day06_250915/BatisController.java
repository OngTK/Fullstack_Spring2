package example.day06_250915;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p> myBatis를 활용한 DB 관리
 * @since 2025.09.15
 * */

@RestController
@RequiredArgsConstructor
@RequestMapping("/day06/batis")
public class BatisController {

    // [0] mapper 객체 DI
    private final BatisMapper batisMapper;


    // [1] create
    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody StudentDto studentDto){
        System.out.println("BatisController.create");
        System.out.println("studentDto = " + studentDto);

        int result = batisMapper.create(studentDto);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [2] readAll
    @GetMapping
    public ResponseEntity<List<StudentDto>> readAll(){
        System.out.println("BatisController.readAll");

        // batisMapper interFace의 readAll SQL문을 실행한 결과를 반환
        List<StudentDto> result = batisMapper.readAll();
        // ResponseEntity 응답객체로 반환
        return ResponseEntity.status(200).body(result);
    } // func end


    // [3] read
    @GetMapping("/read")
    public ResponseEntity< Map<String, Object> > read(@RequestParam int sno){
        System.out.println("BatisController.read");
        System.out.println("sno = " + sno);

        Map<String, Object> result = batisMapper.read(sno);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [4] update
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody StudentDto studentDto){
        System.out.println("BatisController.update");
        System.out.println("studentDto = " + studentDto);

        int result = batisMapper.update(studentDto);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [5] delete
    @DeleteMapping
    public ResponseEntity<Integer> delete(@RequestParam int sno){
        System.out.println("BatisController.delete");
        System.out.println("sno = " + sno);

        int result = batisMapper.delete(sno);
        return ResponseEntity.status(200).body(result);
    } // func end

} // class end
