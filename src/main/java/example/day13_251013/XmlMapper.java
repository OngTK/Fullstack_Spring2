package example.day13_251013;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface XmlMapper {

    // [1] 등록
    // AsIs - @Insert("SQL")
    // ToBe - 추상메소드를 매핑하는 XML 파일에서 SQL 작성
//    @Insert("INSERT INTO student(name, kor, math) values( #{name}, #{kor}, #{math});")
//    @Options(useGeneratedKeys = true, keyProperty = "sno") //Auto_Increament PK를 반환
    int save(StudentDto dto);

    // [2] 전체 조회
    List<StudentDto> findAll();

    // [3] 개별 조회
    StudentDto find (int sno);

    // [4] 개별 삭제
    int delete (int sno);

    // [5] 수정
    int update(StudentDto studentDto);

} // interface end
