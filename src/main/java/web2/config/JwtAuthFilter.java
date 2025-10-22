package web2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import web2.service.JwtService;

import java.io.IOException;
import java.util.List;

/**
 * 개발자가 만든 토큰의 인증 방법을
 * 시큐리티 방식인 UsernamePasswordAuthenticationToken 과 통합
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * [1] 개발자가 만든 토큰 방식
     */
    private final JwtService jwtService;

    /**
     * [2] 기존 시큐리티 방식의 필터 커스텀
     * extends OncePerRequestFilter
     * doFilterInternal 오버라이딩
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // [2.1] 쿠키에서 토큰 추출
        String token = null;
        if (request.getCookies() != null) {    // 쿠키가 존재하면
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginUser")) { // 로그인한 쿠키가 존재하면
                    token = cookie.getValue();               // 쿠키값 꺼내기
                }
            }
        }

        // [2.2] UsernamePasswordAuthenticationToken 재정의
        if(token != null && jwtService.checkToken(token)){ // 토큰이 유효하면
            // [2.2.1] 아이디 꺼내기
            String uid = jwtService.getUid(token);
            // [2.2.1] 권한 꺼내기
            String urole = jwtService.getUrole(token);

            // [2.2.2] 시큐리티가 원하는 토큰 만들기 = UsernamePasswordAuthenticationToken
            // new UsernamePasswordAuthenticationToken( "id", "password", "role" );
            // 복수 권한 부여시 role 자리에 : List.of( new SimpleGrantedAuthority("ROLE_XXX"), new SimpleGrantedAuthority("ROLE_XXX"), ... )
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(uid , null ,
                            List.of( new SimpleGrantedAuthority("ROLE_"+urole) ));

            // [2.2.3] 시큐리티가 사용할 수 있도록 토큰을 저장
            // SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

    } // func end
} // class end
