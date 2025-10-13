package example.day13_251013;

import org.apache.ibatis.annotations.*;

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
    StudentDto find(int sno);

    // [4] 개별 삭제
    int delete(int sno);

    // [5] 수정
    int update(StudentDto studentDto);

    // 동적 쿼리 =====================================    // 일반 SQL 코드를 프로그램 SQL로 변환할 때 사용
    // """  """ : JAVA15 이상 부터 지원
    // @어노테이션(""" <script> xml 형식의 sql </script>""")    // 1=1 : 무조건 True, 생략 시 [ where and kor= ] 과 같이 문법에 맞지 않음

    // <if test=" 조건식 "> 참일때 SQL </if>
    // [6] 특정한 점수 이상의 학생 조회
    @Select("""
            <script>
                select * from student where 1=1
                <if test="kor != null">
                    and kor >= #{ kor }
                </if>

            </script>
            """)
    List<StudentDto> query1(int kor);

    //XML 동적 쿼리
    List<StudentDto> query2(int kor);


    // [7] 이름이 포함된 or 수학 점수가 이상인 검색
//    @Select("""
//            <script>
//                select * from student where 1=1
//            </script>
//            """)
    List<StudentDto> query3(String name,int math);

    // [8] 복수의 학생을 등록
    int saveAll(List<StudentDto> list);

} // interface end
