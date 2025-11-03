package example2.practice.practice01_251103;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * [1] 도서 등록
     * <p>
     * 새로운 도서 정보를 입력받아 DB에 저장
     */
    public BookEntity create(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    } // func end

    /**
     * [2] 도서 전체 조회
     * <p>
     * 모든 도서 목록을 조회
     */
    public List<BookEntity> readAll(){
        return bookRepository.findAll();
    } // func end

    /**
     * [3] 특정 도서 수정
     * <p>
     * 도서ID를 기준으로 해당 도서 모든 정보 수정, @Transactional의 역할을 서술한다.
     */
    public BookEntity update(BookEntity bookEntity){
        // [3.1] 수정할 entity 조회
        Optional<BookEntity> optional = bookRepository.findById(bookEntity.getBookId());

        // [3.2] optional이 존재하는 지 확인
        if(optional.isPresent()){
            BookEntity entity = optional.get();

            // [3.3] 엔티티 수정
            // 주의! setter을 활용하여 entity를 수정할 경우, DB도 함께 수정처리 됨!
            entity.setBookTitle(bookEntity.getBookTitle());
            entity.setBookAuthor(bookEntity.getBookAuthor());
            entity.setBookPublisher(bookEntity.getBookPublisher());

            return entity;
        }
        return bookEntity;
    } // func end

    /**
     * [4] 특정 도서 삭제
     * <p>
     * 도서ID를 기준으로 해당 도서 삭제
     */
    public boolean delete(int bookId){
        bookRepository.deleteById(bookId);
        return true;
    } // func end

} // class end
