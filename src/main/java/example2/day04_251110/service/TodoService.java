package example2.day04_251110.service;

import example2.day04_251110.dto.TodoDto;
import example2.day04_251110.entity.TodoEntity;
import example2.day04_251110.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    // [1] Repository의 2.1 / 3.1 테스트
    public List<TodoDto> query1(String title) {

        // R-2.1. 쿼리 메소드
        List<TodoEntity> result1 = todoRepository.findByTitle(title);
        System.out.println("[1. result1] " + result1);

        // R-3.1. 네이티브 쿼리 메소드
        List<TodoEntity> result2 = todoRepository.query1(title);
        System.out.println("[1. result2] " + result2);

        return result2.stream().map(TodoEntity::toDto).toList();
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    public List<TodoDto> query2(String title, String content) {

        // R-2.2
        List<TodoEntity> result1 = todoRepository.findByTitleAndContent(title, content);
        System.out.println("[2. result1] " + result1);

        // R-3.2
        List<TodoEntity> result2 = todoRepository.query2(title, content);
        System.out.println("[2. result2] " + result2);

        return result2.stream().map(TodoEntity::toDto).toList();
    } // func end

    // [2] Repository의 2.2 / 3.2 테스트
    public List<TodoDto> query3(String keyword) {

        // R-2.3
        List<TodoEntity> result1 = todoRepository.findByTitleContaining(keyword);
        System.out.println("[3. result1]" + result1);

        // R-3.3
        List<TodoEntity> result2 = todoRepository.query3(keyword);
        System.out.println("[3. result2]" + result2);

        return result2.stream().map(TodoEntity::toDto).toList();
    } // func end

    // [4] 페이징 처리
    public Page<TodoDto> page(int page, int size) {

        // [4.1] 페이징 처리 옵션 설정
        // PageRequest.of(조회할 페이지 번호, 페이지 크기, Sort.by(Sort.Direction.DESC, "정렬 속성명"));
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "id"));

        // [4.2] 조회
        Page<TodoEntity> result = todoRepository.findAll(pageRequest);

        // [4.3] 결과 반환
        // Page<>는 기본적으로 stream을 제공
        return result.map(TodoEntity::toDto);

    } // func end

    // [5] 제목 포함 + 페이지 처리
    public Page<TodoDto> page2(String keyword, int page, int size) {
        // 페이지 옵션 객체
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "id");
        // 처리 결과
        Page<TodoEntity> result;
        if (keyword.isBlank() || keyword == null) {
            // 전체 조회
            result = todoRepository.findAll(pageRequest);
        } else {
            // 검색 조회
            result = todoRepository.findByTitleContaining(keyword, pageRequest);
        }
        return result.map(TodoEntity::toDto);
    } // func end

    // 2025.11.11 Flutter 통신 메소드 추가 ============================================================
    // [1] 전체조회
    public List<TodoDto> findAll() {
        return todoRepository.findAll().stream().map(TodoEntity::toDto).toList();
    }// func end

    // [2] 개별삭제
    public boolean delete(int id) {
        // id 가 존재하면
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    } // func end
} // class end
