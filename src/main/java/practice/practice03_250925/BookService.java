package practice.practice03_250925;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    // DI
    private final BookMapper bookMapper;

    // [1] 도서 대출
    @Transactional      // 트랜잭션 어노테이션 주입
    public boolean rentBook(Map<String, String> rentalInfo){

        int book_id = Integer.parseInt(rentalInfo.get("book_id"));
        String member = String.valueOf(rentalInfo.get("member"));

        // [1.1] books table 에서 stock 을 감소
        boolean result1 = bookMapper.decreaseBookStock(book_id);

        // [1.2] rentals table 에서 신규 이력 작성
        boolean result2 = bookMapper.createRental(book_id, member);

        if( !result1 ) {
            throw new RuntimeException(" [강제 예외 발생] - 재고 없음 ");
        } else if ( !result2 ){
            throw new RuntimeException(" [강제 예외 발생] - 대출 이력 등록 실패 ");
        }
        return true;
    } // func end

    // [2] 도서 반납
    @Transactional      // 트랜잭션 어노테이션 주입
    public boolean returnBook(Map<String, String> rentalInfo){

        int book_id = Integer.parseInt(rentalInfo.get("book_id"));
        String member = String.valueOf(rentalInfo.get("member"));
        LocalDateTime now = LocalDateTime.now();

        // [2.1] rentals table에서 기존 이력에서 retrun_date를 갱신
        boolean result1 = bookMapper.returnRental(now, book_id, member);

        // [2.2] books table 에서 stock을 증가
        boolean result2 = bookMapper.increaseBookStock(book_id);

        if(!result1) {
            throw new RuntimeException(" [강제 예외 발생] - 반납 실패 ");
        } else if (!result2){
            throw new RuntimeException(" [강제 예외 발생] - 반납 책 수량 증가 실패");
        }
        return true;
    } // func end


} // class end
