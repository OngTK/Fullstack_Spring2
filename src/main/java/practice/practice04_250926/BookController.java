package practice.practice04_250926;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 1. 도서 단일 등록
     */
    @PostMapping
    public ResponseEntity<?> saveBookInfo(@RequestBody BookDto bookDto){
        int result = bookService.saveBookInfo(bookDto);
        if( result == 0 ){
            return  ResponseEntity.status(460).body(0);
        } else {
            return  ResponseEntity.ok(result);
        }
    } // class end

    /**
     * 2. 대출 기록 검색
     */
    @GetMapping("/rental")
    public ResponseEntity<?> readAllRental(@RequestParam(required = false, defaultValue = "") String member,
                                           @RequestParam(required = false, defaultValue = "") String title){
        List<RentalDto> result = bookService.readAllRental(member, title);
        return ResponseEntity.ok(result);
    } // class end

    /**
     * 3. 책 일괄 등록
     */
    @PostMapping("/sever")
    public ResponseEntity<?> saveBooks(@RequestBody  List<BookDto> list){
        System.out.println("list = " + list);
        boolean result = bookService.saveBooks(list);
        if(result){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(460).body(result);
        }
    } // class end



} // class end
