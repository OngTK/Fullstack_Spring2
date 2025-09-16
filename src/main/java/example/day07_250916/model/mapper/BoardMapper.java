package example.day07_250916.model.mapper;


import example.day07_250916.model.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    // [1] create
    @Insert("insert into board(bcontent, bwriter) values (#{bcontent},#{bwriter})")
    boolean create(BoardDto boardDto);


    // [2] readAll
    @Select("select * from board")
    List<BoardDto> readAll();


    // [3] read
    @Select("select * from board where bno=#{bno}")
    BoardDto read(int bno);


    // [4] delete
    @Delete("delete from board where bno=#{bno}")
    boolean delete(int bno);


    // [5] update
    @Update("update board set bcontent=#{bcontent}, bwriter=#{bwriter} where bno=#{bno}")
    boolean update(BoardDto boardDto);


} // interface end
