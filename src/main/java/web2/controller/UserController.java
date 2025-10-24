package web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.model.dto.UserDto;
import web2.service.JwtService;
import web2.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    /**
     * [1] 회원가입
     *
     * @since 2025.10.20
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        int result = userService.signUp(userDto);
        return ResponseEntity.ok(result);       // 성공 : 200 / return Uno
    } // func end

//    /**
//     * [2.1] 로그인 + 세션
//     * @since 2025.10.20
//     */
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session){
//        UserDto result = userService.login(userDto);
//        
//        if(result != null ){            // 로그인 성공 시
//            session.setAttribute("loginUser", result.getUid() ); // userId를 세션에 저장
//        }
//        return ResponseEntity.ok(result); // 성공 시 not null / 실패 시 null
//    } // func end

    /**
     * [2.2] 로그인 + 쿠키
     * <p>쿠키 : 클라이언트 브라우저의 임시 저장소</p>
     * @since 2025.10.20
     * @param response HttpServletResponse : 클라이언트 브라우저에 접근
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        UserDto result = userService.login(userDto);

        if (result != null) {            // 로그인 성공 시
            // session.setAttribute("loginUser", result.getUid()); // userId를 세션에 저장 >> 쿠키로 변환
            // 로그인 정보를 세션에 저장하면 서버이므로 정보가 안전하게 보호됨
            // 쿠키(클라이언트)에 저장하면 세션 대비 위험성이 높음
            // 이에 대한 안전장치 필요

            // Cookie cookie = new Cookie("쿠키명", 값);
            // [2.2.1] Cookie 객체 생성
            // [ 20025.10.22 ] >> cookie에 저장되는 result.getUid()을 토큰 처리함
            // Cookie cookie = new Cookie("loginUser", result.getUid() );
            Cookie cookie = new Cookie("loginUser", jwtService.createToken(result.getUid(), result.getUrole()));

            // [2.2.2] client 측에서 cookie 노출·탈취 방지 = 민감정보 보호
            // 민감정보가 아니라면 setHttpOnly를 굳이 하지 않아도 ㄱㅊ
            cookie.setHttpOnly( true ); // 무조건 HTTP 내에서만 쿠키 조회 가능, JS 에서는 쿠키에 접근 불가
            // [2.2.3] HTTPS 탈취 방지를 위함 암호화 >> HTTP 에서는 사용 불가 (false)
            cookie.setSecure( false );
            // [2.2.0] 기타 등등
            cookie.setPath("/");    // 쿠키에 접근할 수 있는 경로
            cookie.setMaxAge(60 * 60);   // 쿠키에 유효기간, 단위 sec

            // [2.2.4] cookie 객체를 클라이언트에게 반환
            response.addCookie(cookie);
        }
        return ResponseEntity.ok(result); // 성공 시 not null / 실패 시 null
    } // func end

    /**
     * [3] 현재 로인된 계정의 정보 호출
     * 쿠키를 활용한 로그인 상태 확인
     * @since 2025.10.20
     */
    @GetMapping("/info")
    public ResponseEntity<?> myInfo(HttpServletRequest request){
        // [3.1] 클라이언트 브라우저에 저장된 모든 쿠키 가저오기
        Cookie[] cookies = request.getCookies();

        // [3.2] 반복문을 이용한 특정 쿠키 찾기
        if (cookies != null){
            for(Cookie cookie : cookies){
                // 현재의 cookie의 이름이 loginUser 라면
                if(cookie.getName().equals("loginUser") ) {
                    // [ 20025.10.22 ] >> 쿠키가 아닌 token에서 uid와 urole을 반환
                    // String uid = cookie.getValue(); // 쿠키의 저장된 값 반환 : uid
                    // 쿠키에서 토큰 꺼내기
                    String token = cookie.getValue();
                    // 토근 검사
                    boolean checked = jwtService.checkToken(token);
                    if(checked){
                        String uid = jwtService.getUid(token);

                        // [3.3] service의 내 정보 조회 메소드 실행
                        UserDto result = userService.myInfo(uid);
                        return ResponseEntity.ok(result);
                    }
                    return ResponseEntity.ok(null); // 쿠키는 있지만 토큰은 없음
                }
            }
        }
        return ResponseEntity.ok(null); // [3.4] 비로그인 상태
    } // func end

    /**
     * [4] 로그아웃
     * @since 2025.10.20
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logOut(HttpServletResponse response){
        // [4.1] 삭제할 쿠키명을 null 값으로 변경
        Cookie cookie = new Cookie("loginUser", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 실행 = 즉시 null 처리 = 즉시 삭제

        response.addCookie(cookie); // 동일한 쿠키명이 있다면 덮어쓰기!
        return ResponseEntity.ok(true);
    }// func end

    /**
     * [5] 권한을 반환
     * @since 2025.10.24
     */
    @GetMapping("/check")
    public ResponseEntity<?> checkToken(@CookieValue(value="loginUser", required = false) String token) {
        Map<String, Object> map = new HashMap<>();
        if( token != null & jwtService.checkToken(token) ){
            String urole =  jwtService.getUrole(token);
            map.put("isAuth", true);
            map.put("urole", urole);
            return ResponseEntity.ok(map);
        } else {
            map.put("isAuth", false);
            return ResponseEntity.status(403).body(map);
        }
    } // func end

} // class end
