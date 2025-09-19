package practice.evaluation_250919.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import practice.evaluation_250919.model.dto.MovieDto;

import java.util.List;

@Mapper
public interface MovieMapper {

    // [1] create
    @Insert("insert into movies (mName, mDirector, mIntro, mGenre, mPassword) values(#{mName},#{mDirector},#{mIntro},#{mGenre},#{mPassword})")
    int create(MovieDto movieDto);

    // [2] read - 생략

    // [3] readAll
    @Select("select * from movies order by mno desc")
    List<MovieDto> readAll();

    // [4] update - 생략

    // [5] delete
    @Delete("delete from movies where mno=#{mno} and mPassword=#{mPassword}")
    int delete(int mno, String mPassword);

} // Interface end
