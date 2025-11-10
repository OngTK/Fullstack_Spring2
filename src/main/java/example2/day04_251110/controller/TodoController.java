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


} // class end
