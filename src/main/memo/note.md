# 🗃️ MyBatis 개념 및 사용법

---

## ✅ DAO (Data Access Object)

### 📌 정의
- Java에서 **데이터베이스(DB)**와 직접 연결하고 **SQL 실행을 담당하는 클래스**
- DB 접근 로직을 분리하여 **비즈니스 로직과의 분리 및 재사용성**을 높임

### 📌 주요 인터페이스

| 인터페이스        | 설명 |
|-------------------|------|
| `Connection`      | DB 연결 객체 |
| `PreparedStatement` | SQL 실행 객체 |
| `ResultSet`       | SQL 결과를 저장하는 객체 |

---

## ✅ MyBatis

### 📌 정의
- 개발자가 작성한 **SQL 문을 Java 객체로 자동 매핑**해주는 프레임워크
- JDBC의 반복적인 코드 작성 없이 SQL 중심의 개발 가능

### 📌 특징
- SQL 매핑: XML 또는 어노테이션 기반으로 SQL 작성
- 결과 매핑: SQL 결과를 DTO/VO 객체로 자동 변환
- 유연성: SQL 직접 작성 가능 → 복잡한 쿼리 처리에 유리
- Spring 공식 지원

---

## ✅ 설치 방법

### 📌 1. Spring 프로젝트 생성
- [https//start.spring.io 에서 프로젝트 생성
- Dependencies:
    - `MyBatis Framework`
    - `Spring Web`
    - `DBMS 라이브러리` (예: MySQL Driver, Oracle Driver 등)

### 📌 2. application.properties 설정

```properties
# DB 연결 정보
spring.datasource.url=jdbc:mysql://localhost:3306/db명
spring.datasource.username=아이디
spring.datasource.password=비밀번호

# MyBatis 설정
mybatis.mapper-locations=classpath:/mapper/**/*.xml
mybatis.type-aliases-package=com.example.dto
```

> 💡 `mapper-locations`는 XML 기반 SQL 파일 경로  
> 💡 `type-aliases-package`는 DTO/VO 클래스 경로

---

## ✅ 사용법

### 📌 1. Mapper Interface 정의 (DAO 대체)

```java
@Mapper
public interface MemberMapper {

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
    
}
```

> 📌 `#{}`는 SQL에 매개변수를 삽입하는 방식이며, DTO의 멤버 변수명과 일치해야 함

---

### 📌 2. Service 계층에서 사용
(단, 아래에서는 편의상 Controller 에서 InterFace를 직접 DI 하였음)

```java
@Service
public class MemberController {

    // [0] mapper 객체 DI
    private final BatisMapper batisMapper;


    // [1] create
    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody StudentDto studentDto){
        System.out.println("BatisController.create");
        System.out.println("studentDto = " + studentDto);

        int result = batisMapper.create(studentDto);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [2] readAll
    @GetMapping
    public ResponseEntity<List<StudentDto>> readAll(){
        System.out.println("BatisController.readAll");

        // batisMapper interFace의 readAll SQL문을 실행한 결과를 반환
        List<StudentDto> result = batisMapper.readAll();
        // ResponseEntity 응답객체로 반환
        return ResponseEntity.status(200).body(result);
    } // func end


    // [3] read
    @GetMapping("/read")
    public ResponseEntity< Map<String, Object> > read(@RequestParam int sno){
        System.out.println("BatisController.read");
        System.out.println("sno = " + sno);

        Map<String, Object> result = batisMapper.read(sno);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [4] update
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody StudentDto studentDto){
        System.out.println("BatisController.update");
        System.out.println("studentDto = " + studentDto);

        int result = batisMapper.update(studentDto);
        return ResponseEntity.status(200).body(result);
    } // func end


    // [5] delete
    @DeleteMapping
    public ResponseEntity<Integer> delete(@RequestParam int sno){
        System.out.println("BatisController.delete");
        System.out.println("sno = " + sno);

        int result = batisMapper.delete(sno);
        return ResponseEntity.status(200).body(result);
    } // func end
    
}
```

---

## ✅ 추가 팁

### 📌 XML 기반 Mapper 사용 예시

```xml
<!-- resources/mapper/MemberMapper.xml -->
<mapper namespace="com.example.mapper.MemberMapper">
    <select id="findById" resultType="MemberDto">
        SELECT * FROM member WHERE id = #{id}
    </select>
</mapper>
```

### 📌 DTO 예시

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private int id;
    private String name;
    private String email;
}
```

---

## ✅ 장점 요약

- SQL을 직접 제어 가능 → 복잡한 쿼리 처리에 유리
- 객체 매핑 자동화 → 생산성 향상
- Spring과의 통합이 쉬움 → `@Mapper`, `@Autowired`로 간편 DI

---
