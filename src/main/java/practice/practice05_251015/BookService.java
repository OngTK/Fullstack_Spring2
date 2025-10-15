package practice.practice05_251015;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookMapper bookMapper;

    // [1] 책 테이블에 가격 필드 추가
    public int createColumn() {
        return bookMapper.createColumn();
    }

    // [2] 책 테이블에 책이름 필드 수정
    public int updateTitleAtt(){
        return bookMapper.updateTitleAtt();
    }

    // [3] 평균 재고보다 많은 재고를 가진 도서 조회
    public List<BookDto> overAvg(){
        return bookMapper.overAvg();
    }

    // [4] 가장 많이 대출한 도서 조회
    public BookDto bestRentalBook(){
        return bookMapper.bestRentalBook();
    }

} // class end
