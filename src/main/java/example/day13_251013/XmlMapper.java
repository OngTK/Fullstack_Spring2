package example.day13_251013;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XmlMapper {

    // [1] 등록
    // AsIs - @Insert("SQL")
    // ToBe - 추상메소드를 매핑하는 XML 파일에서 SQL 작성
    int save(StudentDto dto);

    // [2] 전체 조회
    List<StudentDto> findAll();

} // interface end
