package practice.practice05_251015;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    
    // [1] 책 테이블에 가격 필드 추가
    @GetMapping("/createColumn")
    public ResponseEntity<?> createColumn(){
        int result = bookService.createColumn();
        if(result == 0){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(460).body(result);
        }
    } // func end
    
    // [2] 책 테이블에 책이름 필드 수정
    @GetMapping("/updateTitleAtt")
    public ResponseEntity<?> updateTitleAtt(){
        int result = bookService.updateTitleAtt();
        if(result >= 0){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.status(460).body(result);
        }
    } // func end
    
    // [3] 평균 재고보다 많은 재고를 가진 도서 조회
    @GetMapping("/overAvg")
    public ResponseEntity<?> overAvg(){
        List<BookDto> result = bookService.overAvg();
        return ResponseEntity.ok(result);
    } // func end
    
    // [4] 가장 많이 대출한 도서 조회
    @GetMapping("/bestRentalBook")
    public ResponseEntity<?> bestRentalBook(){
        BookDto result = bookService.bestRentalBook();
        return ResponseEntity.ok(result);
    } // func end
    
} // class end
