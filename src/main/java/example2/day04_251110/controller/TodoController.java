package example2.day04_251110.controller;

import example2.day04_251110.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    // [1] Repository의 2.1 / 3.1 테스트
    @GetMapping("/query1")
    public ResponseEntity<?> query1(@RequestParam String title){
        return ResponseEntity.ok(todoService.query1(title));
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content){
        return ResponseEntity.ok(todoService.query2(title, content));
    }// func end

    // [3] Repository의 2.3 / 3.3 테스트
    @GetMapping("/query3")
    public ResponseEntity<?> query3(String keyword){
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
} // class end