package practice.practice05_251015;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    // [1] 책 테이블에 가격 필드 추가
    int createColumn();

    // [2] 책 테이블에 책이름 필드 수정
    int updateTitleAtt();

    // [3] 평균 재고보다 많은 재고를 가진 도서 조회
    List<BookDto> overAvg();

    // [4] 가장 많이 대출한 도서 조회
    BookDto bestRentalBook();


} // interface end
