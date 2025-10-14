package practice.practice04_250926;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    /**
     * 1. 단일 도서 등록
     */
    public int saveBookInfo(BookDto bookDto){
        if(bookDto.getTitle() == null || bookDto.getTitle().isEmpty()) return 0;

        bookMapper.saveBookInfo(bookDto);
        if((bookDto.getId() >= 0)){
            return bookDto.getId();
        } else {
            return 0;
        }
    } //

    /**
     * 2. 대출 이력 검색·조회
     */
    public List<RentalDto> readAllRental(String member, String title){
        RentalDto dto = new RentalDto();
        dto.setMember(member);
        dto.setTitle(title);
        return bookMapper.readAllRental(dto);
    } // func end

    /**
     * 3. 책 일괄 등록
     */
    @Transactional
    public boolean saveBooks(List<BookDto> list){
        for(BookDto dto : list){
            bookMapper.saveBookInfo(dto);
        }

        boolean check = true;
        for(BookDto dto : list){
            if(dto.getId() == 0 ){
                check = false;
                break;
            }
        }

        return check;

    } // func end


} // class end
