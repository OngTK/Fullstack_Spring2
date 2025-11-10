package example2.day04_251110.repository;

import example2.day04_251110.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

    // JPA Repository
    // 1. save(), findById(), findAll(), deleteById() 등 미리 만들어진 CRUD를 제공


    // 2. 쿼리 메소드
    // SQL 문장을 직접 작성하지 않고 메소드 이름으로 쿼리를 생성
    // 반드시 카멜표기법

    // [2.1] findBy필드명(String 매개변수)
    List<TodoEntity> findByTitle(String title);
    // == `select * from todo where title='매개변수' `

    // [2.2] findBy필드명And필드명(String 매개변수1, String 매개변수2);
    List<TodoEntity> fintByTitleAndContent(String title, String content);
    // == `select * from todo where title='매개변수' and content='매개변수' `

    // [2.3] findBy필드명Containing
    List<TodoEntity> findByTitleContaining(String title);
    // == `select * from todo where title like "%매개변수%"`


    // 3. 네이티브 쿼리 메소드
    // SQL 문장을 직접 작성하여 실행

    // @Query(value = "SQL작성", nativeQuery = true)
    // SQL문 안에 매개변수는 `:매개변수`로 표기

    // [3.1]
    @Query(value = "select * from todo where title = :title ", nativeQuery = true)
    List<TodoEntity> query1(String title);

    // [3.2]
    @Query(value = "select * from todo where title = :title and content = :content",nativeQuery = true)
    List<TodoEntity> query2(String title, String content);

} // class end
