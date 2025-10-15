package practice.practice04_251014;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    /**
     * 1. 단일 도서 등록
     */
    int saveBookInfo(BookDto bookDto);

    /**
     * 2. 대출 이력 검색·조회
     */
    List<RentalDto> readAllRental(RentalDto dto);



} // interface end
