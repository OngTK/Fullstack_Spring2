package practice.practice03_250925;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Log4j2     // 로그처리 어노테이션 제공
public class BookController {

    // [250926 Log4j2 사용해보기]
    @GetMapping("/log")
    public void log(){
        // log : 출력함수 처럼 메세지 출력을 하며 파일처리·제약조건 등 부가기능을 가지고 있음
        log.debug( "테스트 과정에서 사용" ); // Test 과정에서 메시지
        log.info("서비스의 흐름/상태에 사용");
        log.warn("잠재적인 문제에 대해 사용");
        log.error("예외//실패 상황에 사용");
    }

    // DI =========================================================
    private final BookService bookService;

    /**
     * [1] 도서 대출
     *
     * @param rentalInfo Map<String, String> = {book_id : , member:  }
     *
     */
    @PostMapping("/rent")
    public ResponseEntity<Boolean> rentBook(@RequestBody Map<String, String> rentalInfo) {
//        System.out.println("rentalInfo = " + rentalInfo); // 실행 확인용
//        System.out.println("BookController.rentBook"); // 실행 확인용

        boolean result = false;
        try {
            log.debug("[대여 성공] {}", rentalInfo);
            result = bookService.rentBook(rentalInfo);
            return ResponseEntity.ok().body(result);
        } catch (RuntimeException e) {
            log.debug("[대여 실패] {}", rentalInfo);
            return ResponseEntity.status(405).body(result);
        }
    } // func end

    /**
     * [2] 도서 반납
     *
     * @param rentalInfo Map<String, String> = {book_id : , member:  }
     *
     */
    @PostMapping("/return")
    public ResponseEntity<Boolean> returnBook(@RequestBody Map<String, String> rentalInfo) {
//        System.out.println("rentalInfo = " + rentalInfo); // 실행 확인용
//        System.out.println("BookController.returnBook"); // 실행 확인용

        boolean result = false;

        try {
            result = bookService.returnBook(rentalInfo);
            log.debug("[반납 성공] {}", rentalInfo);
            return ResponseEntity.ok().body(result);
        } catch (RuntimeException e) {
            log.debug("[반납 실패] {}", rentalInfo);
            return ResponseEntity.status(405).body(result);
        }
    } // func end

} // class end
