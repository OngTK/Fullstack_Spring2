package web2.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web2.model.dto.UserDto;
import web2.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /*
     * 비밀번호 암호화 - 2025.10.20
     * 평문 : 본래의 데이터 / 암호문 : 암호화된 데이터
     * 암호화 : 사람이 이해할 수 없는 데이터로 변경하는 것
     * 복호화 : 암호화된 데이터를 다시 평문으로 변경하는 것
     * 암호화 알고리즘 : 블록체인, Bcrypt
     *
     * [50.1] Bcrypt
     * - 복호화 불가능
     * - 동일한 평문을 encode 하여도 서로 다른 암호문이 발생됨
     */
    // [50.1] 비크립트 라이브러리 호출
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    /**
     * [1] 회원가입
     *
     * @since 2025.10.20
     */
    public int signUp(UserDto userDto) {
        // [50.2] 비밀번호 암호화 처리
        // String 암호화된Data = encoder.encode(암호화할Data);
        userDto.setUpwd(bcrypt.encode(userDto.getUpwd()));

        userMapper.signUp(userDto);
        if (userDto.getUno() > 0) {  // 회원가입 성공
            return userDto.getUno();
        } else {                    // 회원가입 실패
            return 0;
        }
    } // func end

    /**
     * [2] 로그인
     *
     * @since 2025.10.20
     */
    public UserDto login(UserDto userDto) {
        // [50.3] Bcrypt
        // 저장된 암호문을 해독하여 평문을 비교하는 형식이 아닌
        // 비교할 평문을 암호화하여 암호문끼리 비교

        // [2-1] 현재 로그인에서 입력받은 아이디의 계정이 있는지 확인
        UserDto result = userMapper.login(userDto.getUid());
        if (result == null) {
            return null;                // id로 조회된 결과가 없으면 null을 반환
        }
        // [2-2] 계정이 존재할 경우, 입력받은 비밀번호와 암호화된 비밀번호를 비교
        // Bcrypt의 matches를 이용한 암호문 비교 방식을 활용
        // ` .matches(비교할평문비밀번호, 암호문) `
        boolean result2 = bcrypt.matches(userDto.getUpwd(), result.getUpwd());

        if (result2) {                // 평문과 암호문이 일치하면
            result.setUpwd(null);       // 비밀번호 null 처리 후 
            return result;              // userDto 결과 반환
        } else {                    // 평문과 암호문이 일치하지 않으면
            return null;                // null을 반환
        }
    } // func end

    /**
     * [3] 현재 로그인된 계정의 정보 호출
     *
     * @since 2025.10.20
     */
    public UserDto myInfo(String uid) {
        UserDto result = userMapper.myInfo(uid);
        return result;
    } // func end

    /**
     * [4] Oauth 회원에 대한 회원가입
     * @since 2025.10.23
     */
    public UserDto oauth2UserSignup(String uid, String name){
        // [4.1] 기존 회원인지 확인
        UserDto userDto = userMapper.login(uid);

        if( userDto == null ){ // 기존 회원정보 없음
            // DTO 구성
            UserDto oauthUser = UserDto
                    .builder()
                    .uid(uid)
                    .uname(name)
                    .upwd("oauth")     //password 가 없는 회원 = oauth 회원 // 타사의 비밀번호를 확인할수도 처리할 수도 없음
                    .urole("USER")
                    .build();
            userMapper.signUp(oauthUser);
            return oauthUser;
        }
        return null;
    } // func end

} // class end
