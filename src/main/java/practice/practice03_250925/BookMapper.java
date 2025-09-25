package practice.practice03_250925;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface BookMapper {

    // [1] 도서 대출
    // [1.1] books table 에서 stock 을 -1
    @Update("update books set stock = stock-1 where id = #{id} and stock > 0 ")
    boolean decreaseBookStock(int id);

    // [1.2] rentals table 에서 신규 이력 작성
    @Insert("insert into rentals(book_id, member) values (#{bookId},#{member})")
    boolean createRental(int bookId, String member);

    // [2] 도서 반납
    // [2.1] rentals table에서 기존 이력에서 retrun_date를 갱신
    @Update("update rentals set return_date = #{return_date} where book_id=#{book_id} and member=#{member} and return_date is null")
    boolean returnRental(LocalDateTime return_date, int book_id, String member );
    
    // [2.2] books table 에서 stock을 +1
    @Update("update books set stock = stock+1 where id = #{id}")
    boolean increaseBookStock(int id);

} // interface end
