package example.day13_251013;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {

    private final XmlMapper xmlMapper;

    // [1] 등록
    @PostMapping
    public ResponseEntity< ? > save(@RequestBody StudentDto studentDto){
        // 제네릭 ? : 모든 타입 (와일드카드)

        System.out.println("studentDto = " + studentDto);

        int result = xmlMapper.save(studentDto);

        System.out.println("studentDto = " + studentDto);

        if(result == 1 ){
            return ResponseEntity.ok(studentDto.getSno());
        } else{
            return ResponseEntity.status(500).body(false);
        }

    } // func end

    // [2] 전체조회
    @GetMapping
    public ResponseEntity< ? > findAll( ){
        List<StudentDto> result = xmlMapper.findAll();
        return ResponseEntity.ok(result);
    }

    // [3] 개별조회
    @GetMapping("/find")
    public ResponseEntity< ? > find(int sno) {
        StudentDto result = xmlMapper.find(sno);
        return ResponseEntity.ok(result);
    }

    // [4] 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(int sno){
        xmlMapper.delete(sno);
        return ResponseEntity.ok(true);
    }

    // [5] 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto){
        System.out.println("XmlController.update");
        System.out.println("studentDto = " + studentDto);
        xmlMapper.update(studentDto);
        return ResponseEntity.ok(true);
    }

} // class end
