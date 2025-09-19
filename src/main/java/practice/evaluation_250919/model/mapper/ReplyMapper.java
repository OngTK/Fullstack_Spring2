package practice.evaluation_250919.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import practice.evaluation_250919.model.dto.ReplyDto;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // [1] create
    @Insert("insert into replies (rContent, rPassword, mNo) values (#{rContent},#{rPassword},#{mNo})")
    int create(ReplyDto replyDto);

    // [2] read
    @Select("select * from replies where mno=#{mno} order by rno desc")
    List<ReplyDto> read(int mno);

    // [3] readAll
    @Select("select * from replies order by rno desc")
    List<ReplyDto> readAll();

    // [4] update - 생략

    // [5] delete
    @Delete("delete from replies where rno=#{rno} and rPassword=#{rPassword}")
    int delete(int rno, String rPassword);

} // Interface end
