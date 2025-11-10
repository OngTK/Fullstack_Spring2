package example2.day04_251110.service;

import example2.day04_251110.dto.TodoDto;
import example2.day04_251110.entity.TodoEntity;
import example2.day04_251110.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("[1. result1] " + result1);

        // R-3.1. 네이티브 쿼리 메소드
        List<TodoEntity> result2 = todoRepository.query1(title);
        System.out.println("[1. result2] "+result2);

        return result2.stream().map(TodoEntity :: toDto).toList();
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    public List<TodoDto> query2(String title, String content){

        // R-2.2
        List<TodoEntity> result1 = todoRepository.findByTitleAndContent(title, content);
        System.out.println("[2. result1] " + result1);

        // R-3.2
        List<TodoEntity> result2 = todoRepository.query2(title, content);
        System.out.println("[2. result2] " + result2);

        return result2.stream().map(TodoEntity :: toDto).toList();
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    public List<TodoDto> query3(String keyword){

        // R-2.3
        List<TodoEntity> result1 = todoRepository.findByTitleContaining(keyword);
        System.out.println("[3. result1]" + result1);

        // R-3.3
        List<TodoEntity> result2 = todoRepository.query3(keyword);
        System.out.println("[3. result2]" + result2);

        return result2.stream().map(TodoEntity :: toDto).toList();
    } // func end

    // [4] 페이징 처리
    public Page<TodoDto> page(int page, int size){

        // [4.1] 페이징 처리 옵션 설정
        PageRequest.of(page, size);

        // [4.2] 조회

        // [4.3] 결과 반환

    } // func end

} // class end
