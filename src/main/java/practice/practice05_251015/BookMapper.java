package practice.practice05_251015;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    // 2025.10.16 =======================================

    // [1] 대출 기록 상세 뷰 생성
    int createDetailView();

    // [2] 대출 기록 상세 뷰 조회
    List<Map<String, Object>> readDetailRental();

    // [3] 평균 재고보다 많은 재고를 가진 도서 뷰 생성
    int createOverAvgStock();

    // [4] 평균 재고보다 많은 도서를 가진 도서 조회
    List<Map<String, Object>> readOverAvgStock();

} // interface end
