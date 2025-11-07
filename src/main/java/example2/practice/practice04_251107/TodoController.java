package example2.practice.practice04_251107;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    /**
     * [1] POST : 새로운 TO DO 등록
     */
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto todoDto){
        return ResponseEntity.ok(todoService.createTodo(todoDto));
    } // func end


    /**
     * [2] GET : 전체 TO DO 목록 조회
     */
    @GetMapping
    public ResponseEntity<?> getTodoList(){
        return ResponseEntity.ok(todoService.getTodoList());
    } // func end

} // class end
