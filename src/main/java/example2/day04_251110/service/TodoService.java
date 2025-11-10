package example2.day04_251110.service;

import example2.day04_251110.dto.TodoDto;
import example2.day04_251110.entity.TodoEntity;
import example2.day04_251110.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    // [1] Repository의 2.1 / 3.1 테스트
    public List<TodoDto> query1(String title){

        // R-2.1. 쿼리 메소드
        List<TodoEntity> result1 = todoRepository.findByTitle(title);
        System.out.println("[result1] " + result1);

        // R-3.1. 네이티브 쿼리 메소드
        List<TodoEntity> result2 = todoRepository.query1(title);
        System.out.println("[result2] "+result2);

        return result2.stream().map(TodoEntity :: toDto).toList();
    } // func end

} // class end
