package web2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 라이브러리 커스텀
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    /*
    * 시큐리티(보안) 필터(검증/확인) 체인(연결고리)
    * - 미리 만들어저 있는 필터가 다수임
    * - 따라서 필요없는 필터에 대한 종료 필요
    */

    /**
     * HTTP 관련 필터에 대한 커스텀
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // [1] HTTP 요청에 따른 권한 커스텀
        // .authorizeHttpRequests( auth -> auth.requestMatchers("경로").권한
        // "/**" 모든 경로
        // .permitAll() : 모두 허가
        http.authorizeHttpRequests( auth -> auth.requestMatchers("/**").permitAll() );
        
        // [2] HTTP 요청 중 scrf (요청 간의 해킹 공격) => POST / PUT이 차단됨
        // http.csrf( csrf -> csrf.ignoringResuestMatchers("csrf제외경로"); // 운영단계 권장
        http.csrf(csrf -> csrf.disable() ); // 개발단계에서 궈장 //csrf를 사용하지 않음
        
        return http.build(); // 커스텀 완료 객체
    };

} // class end
