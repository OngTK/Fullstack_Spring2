package example2.practice.practice04_251107;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * [1] POST : 새로운 TO DO 등록
     */
    public TodoDto createTodo(TodoDto todoDto) {
        return todoRepository.save(todoDto.toEntity()).toDto();
    } // func end

    /**
     * [2] GET : 전체 TO DO 목록 조회
     */
    public List<TodoDto> getTodoList() {
        List<TodoEntity> entityList = todoRepository.findAll();
        return entityList.stream().map( TodoEntity :: toDto ).toList();
    } // func end

} // class end
