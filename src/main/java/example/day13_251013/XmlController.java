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
        // 제네릭 ? : 모든 타입 (와일드카드)6
        xmlMapper.save(studentDto);
        return ResponseEntity.ok(true);
    } // func end

    // [2] 전체조회
    @GetMapping
    public ResponseEntity< ? > findAll( ){
        List<StudentDto> result = xmlMapper.findAll();
        return ResponseEntity.ok(result);
    }

} // class end
