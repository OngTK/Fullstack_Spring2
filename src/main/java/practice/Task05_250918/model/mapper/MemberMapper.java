package practice.Task05_250918.model.mapper;

import org.apache.ibatis.annotations.*;
import practice.Task05_250918.model.dto.MemberDto;

import java.util.List;

@Mapper
public interface MemberMapper {

    // [1] create
    @Insert("insert into members(name, phone, age) values (#{name},#{phone},#{age})")
    int create(MemberDto memberDto);

    // [2] read
    @Select("select * from members where mno=#{mno}")
    MemberDto read(int mno);

    // [3] readAll
    @Select("select * from members")
    List<MemberDto> readAll();

    // [4] update
    @Update("update members set name=#{name},phone=#{phone}, age=#{age} where mno=#{mno}")
    int update(MemberDto memberDto);

    // [5] delete
    @Delete("delete from members where mno=#{mno}")
    int delete(int mno);

} // interface end
