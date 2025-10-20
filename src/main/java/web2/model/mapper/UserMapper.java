package web2.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import web2.model.dto.UserDto;

@Mapper
public interface UserMapper {

    /**
     * [1] 회원가입
     *
     * @since 2025.10.20
     */
    @Insert("""
            insert into users( uid, upwd, uname, uphone, urole )
            values (#{uid}, #{upwd}, #{uname}, #{uphone}, #{urole});
            """)
    @Options(useGeneratedKeys = true, keyProperty = "uno")
    // insert 성공시 매개변수 dto의 "uno" 자리에 PK 반환
    int signUp(UserDto userDto);

    /**
     * [2-1] 로그인
     *
     * @since 2025.10.20
     */
//    @Select("""
//            select * from users where uid= #{uid} and upwd = #{upwd};
//            """)
//    UserDto login(UserDto userDto);
//    Bcrypt 사용으로 upwd에 비교가 불가능

    /**
     * [2-2] 아이디로 회원 정보를 조회
     */
    @Select("""
            select * from users where uid = #{uid};
            """)
    UserDto login(String uid);

    /**
     * [3] 내 정보 조회
     * 단, pwd 제외
     */
    @Select("""
            select uno, uid, uname, uphone, urole, udate from users where uid = #{uid};
            """)
    UserDto myInfo(String uid);


} // interface End
