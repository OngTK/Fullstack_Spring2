package example.day06_250915;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 해당 mybatis interface를 Spring bean에 등록하는 어노테이션 = DAO 역할
@Mapper
public interface BatisMapper {

    // [1] create

    // [2] readAll
    @Select ("select * from student")
    List< StudentDto > readAll();

    // [3] read

    // [4] update

    // [5] delete

} // interface end
