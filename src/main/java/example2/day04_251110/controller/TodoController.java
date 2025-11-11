package example2.day04_251110.controller;

import example2.day04_251110.dto.TodoDto;
import example2.day04_251110.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
@CrossOrigin(value = "*")
public class TodoController {

    private final TodoService todoService;

    // [1] Repository의 2.1 / 3.1 테스트
    @GetMapping("/query1")
    public ResponseEntity<?> query1(@RequestParam String title) {
        return ResponseEntity.ok(todoService.query1(title));
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content) {
        return ResponseEntity.ok(todoService.query2(title, content));
    }// func end

    // [3] Repository의 2.3 / 3.3 테스트
    @GetMapping("/query3")
    public ResponseEntity<?> query3(String keyword) {
        return ResponseEntity.ok(todoService.query3(keyword));
    } // func end

    // [4] 페이징 처리
    @GetMapping("/page")
    public ResponseEntity<?> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok(todoService.page(page, size));
    } // func end

    // [5] 제목 포함 + 페이지 처리
    @GetMapping("/page2")
    public ResponseEntity<?> page2(
            String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return ResponseEntity.ok(todoService.page2(keyword, page, size));
    } // func end

    // 2025.11.11 Flutter 통신 메소드 추가 ============================================================

    // [1] 전체조회
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    } // func end

    // [2] 개별삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id) {
        return ResponseEntity.ok(todoService.delete(id));
    } // func end

    // [3] 개별조회
    @GetMapping("/detail")
    public ResponseEntity<?> findById(@RequestParam int id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    // [4] 개별 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestBody TodoDto todoDto){
        return ResponseEntity.ok(todoService.update(todoDto));
    }
} // class end