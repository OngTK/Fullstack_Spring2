package example2.practice.practice01_251103;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * [1] 도서 등록
     * <p>
     * 새로운 도서 정보를 입력받아 DB에 저장
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookEntity bookEntity){
        System.out.println("BookController.create");
        System.out.println("bookEntity = " + bookEntity);
        return ResponseEntity.ok(bookService.create(bookEntity));
    } // func end

    /**
     * [2] 도서 전체 조회
     * <p>
     * 모든 도서 목록을 조회
     */
    @GetMapping
    public ResponseEntity<?> readAll(){
        System.out.println("BookController.readAll");
        return ResponseEntity.ok(bookService.readAll());
    } // func end


    /**
     * [3] 특정 도서 수정
     * <p>
     * 도서ID를 기준으로 해당 도서 모든 정보 수정, @Transactional의 역할을 서술한다.
     */
    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookEntity bookEntity){
        System.out.println("BookController.update");
        System.out.println("bookEntity = " + bookEntity);
        return ResponseEntity.ok(bookService.update(bookEntity));
    } // func end


    /**
     * [4] 특정 도서 삭제
     * <p>
     * 도서ID를 기준으로 해당 도서 삭제
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int bookId){
        System.out.println("BookController.delete");
        System.out.println("bookId = " + bookId);
        return ResponseEntity.ok(bookService.delete(bookId));
    } // func end

} // class end
