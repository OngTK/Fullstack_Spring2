package example2.day01_251103;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // [1] Create
    @PostMapping
    public ResponseEntity<?> post(@RequestBody ExamEntity examEntity){
        System.out.println("ExamController.post");
        System.out.println("examEntity = " + examEntity);
        ExamEntity savedEntity = examService.post(examEntity);
        return ResponseEntity.ok(savedEntity);
    } // func end

    // [2] ReadAll
    @GetMapping
    public ResponseEntity<?> get(){
        System.out.println("ExamController.get");
        List<ExamEntity> list = examService.get();
        return ResponseEntity.ok(list);
    } // func end

    // [3.2] Update
    @PutMapping
    public ResponseEntity<?> put(@RequestBody ExamEntity examEntity){
        ExamEntity entity = examService.put2(examEntity);
        return ResponseEntity.ok(entity);
    } // func end

    // [4] delete
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int col1){
        System.out.println("ExamController.delete");
        System.out.println("col1 = " + col1);
        examService.delete(col1);
        return ResponseEntity.ok(true);
    } // func end

} // class end
