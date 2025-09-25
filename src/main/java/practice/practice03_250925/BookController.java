package practice.practice03_250925;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    // DI
    private final BookService bookService;

    /** [1] 도서 대출
     * @param rentalInfo Map<String, String> = {book_id : , member:  }
     * */
    @PostMapping("/rent")
    public boolean rentBook(@RequestBody Map<String, String> rentalInfo){
        System.out.println("rentalInfo = " + rentalInfo); // 실행 확인용
        System.out.println("BookController.rentBook"); // 실행 확인용

        return bookService.rentBook(rentalInfo);
    } // func end

    /** [2] 도서 반납
     * @param rentalInfo Map<String, String> = {book_id : , member:  }
     * */
    @PostMapping("/return")
    public boolean returnBook(@RequestBody Map<String, String> rentalInfo){
        System.out.println("rentalInfo = " + rentalInfo); // 실행 확인용
        System.out.println("BookController.returnBook"); // 실행 확인용

        return bookService.returnBook(rentalInfo);
    } // func end
    
} // class end
