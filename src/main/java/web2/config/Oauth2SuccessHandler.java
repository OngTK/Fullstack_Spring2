package web2.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 타사 로그인 성공 이후 로직 커스텀
 * <p>
 * 로그인 실패는 타사 로그인 페이지에서 관리하므로, 로그인 성공만 다룸
 * @since 2025.10.23
 */
@Configuration
@RequiredArgsConstructor
// [0.1] AuthenticationSuccessHandler 구현체 연결
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    // [0.2] onAuthenticationSuccess 구현
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication);

        // [0.3] 로그인 성공한 회원의 타사 발급 토큰 확인
        // Oauth2 관련 라이브러리 설치 필요

        // 로그인 성공한 토큰 확인 : 로그인한 회사명 확인 가능
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        System.out.println("authToken = " + authToken);

        // .getPrincipal() : 로그인 성공한 회원의 동의항목(정보)를 반환
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("oauth2User = " + oauth2User);

    } // func end



    // [1] 어느 사에서 로그인 성공한 것인지 확인
    
    // [2] 성공한 동의항목(정보) 가져오기
    // 개인정보(아이디, 회원명, 주소, 전화번호 etc)
    
    // [3] 자사 서버와 타사 서버 통합 로그인
    // 토큰, 쿠키 생성
    
    // [4] 자사 서버와 타사 서버의 통합 DB
    // 최초로그인이면 DB저장 or DB처리 없이 로그인 완료


} // class end
