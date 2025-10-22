package web2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    // 개발자가 만든 토큰을 세큐리티 토큰에 통합한 class
    private final JwtAuthFilter jwtAuthFilter;

    /**
     * HTTP 관련 필터에 대한 커스텀
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // [1] HTTP 요청에 따른 권한 커스텀
        // .authorizeHttpRequests( auth -> auth.requestMatchers("경로").권한
        // "/**" 모든 경로
        // .permitAll() : 모두 허가
        // .hasRole("권한") : 해당 권한만 가능
        // .hasAnyRole("권한", "권한") : 복수 권한
        // 참고 : 권한명은 대문자를 권고
        http.authorizeHttpRequests( auth -> auth
                .requestMatchers("/api/user/info").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/admin/**").hasRole("ADMIN") //해당 api는 ADMIN 권한만 사용 가능
                .requestMatchers("/**").permitAll() );  // 모든 권한 허용은 최하단에 정의
        
        // [2] HTTP 요청 중 scrf (요청 간의 해킹 공격) => POST / PUT이 차단됨
        // http.csrf( csrf -> csrf.ignoringResuestMatchers("csrf제외경로"); // 운영단계 권장
        http.csrf(csrf -> csrf.disable() ); // 개발단계에서 권장 //csrf를 사용하지 않음
        
        // [3] security 내부에서 사용되는 세션기반 토큰
        // UsernamePasswordAuthenticationToken
        // 현재 web2에서는 쿠키 기반의 토큰을 구현
        // >> 시큐리티 내부 토큰은 사용하지 않음
        // [3-1] 시큐리티 세션 끄기
        http.sessionManagement(
                session
                        -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS) );
        // [3-2] 개발자가 만든 토큰 대체
        // http.addFilterBefore( 내가만든토큰객체필터, UsernamePasswordAuthenticationToken.class )
        http.addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build(); // 커스텀 완료 객체
    };

} // class end
