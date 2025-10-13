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

    // [6-1] 한국어가 특정한 점수 이상의 학생 조회
    // Annotation
    @GetMapping("/query1")
    public ResponseEntity<?> query1 (@RequestParam int kor){
        List<StudentDto> list = xmlMapper.query1(kor);
        return ResponseEntity.ok(list);
    }

    // [6-2] XML
    @GetMapping("/query2")
    public ResponseEntity<?> query2 (@RequestParam int kor){
        List<StudentDto> list = xmlMapper.query2(kor);
        return ResponseEntity.ok(list);
    }

    // [7] 이름 포함 or 수학 점수 검색
    @GetMapping("/query3")
    public ResponseEntity<?> query3 (@RequestParam(required = false) String name,
                                     @RequestParam(required = false) int math ){
        System.out.println("name = " + name + ", math = " + math);
        List<StudentDto> list = xmlMapper.query3(name,math);
        return ResponseEntity.ok(list);
    }

    // [8] 복수의 학생을 등록
    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<StudentDto> list){
        xmlMapper.saveAll(list);
        return ResponseEntity.ok(true);
    }

} // class end
