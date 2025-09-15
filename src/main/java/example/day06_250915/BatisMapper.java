package example.day06_250915;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p> myBatis를 활용한 Mapping interFace
 * @since 250915
 * */

// 해당 mybatis interface를 Spring bean에 등록하는 어노테이션 = DAO 역할
@Mapper
public interface BatisMapper {

    // [1] create
    @Insert("insert into student(name, kor, math) VALUES(#{name},#{kor},#{math})")
    int create(StudentDto studentDto);
    // 성공한 레코드 수를 반환 : 1


    // [2] readAll
    @Select ("select * from student")
    List< StudentDto > readAll();


    // [3] read
    @Select("select * from student where sno=#{sno}")
    Map<String, Object> read(int sno); // func end


    // [4] update
    @Update("update student set kor=#{kor}, math=#{math} where sno=#{sno}")
    int update(StudentDto studentDto); // func end
    // 성공한 레코드 수를 반환 : 1


    // [5] delete
    @Delete("delete from student where sno=#{sno}")
    int delete(int sno); // func end
    // 성공한 레코드 수를 반환 : 1


} // interface end
