package example.day12_250930;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.annotation.Documented;

/**
 * [ CorsConfig ]
 *
 * 프로젝트 전체에 대한 Cors 설정
 *
 * 1) CORS 관련 매핑 설정
 * */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    /**
     * [1] CORS 관련 매핑 설정
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping( "/허용할 컨트롤러 URL")                    // "/**"   모든 Controller
        //         .allowedOrigins("허용할 출처·React 도메인")             // "*"     모든 출처
        //         .allowedMethods("허용할 메소드")                       // "*"      모든 메소드
        //         .allowCredentials( true or false )                   // HTTP 인증(세션 유지) 허용
        //          .allowedHeaders("*");                               // HTTP 헤더 정보
        // 안전성을 위하여 allowedMethod를 get만 하고 필요한 곳 별로 POST PUT DELETE를 삽입
        registry.addMapping("/axios/**")                // axios + axios 하위 url 통신이 가능
                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
                .allowedMethods("GET" , "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("*");
    } // func end

} // class end

// 기본적으로 axios 통신에서 서로 다른 통신 도메인에 대하여 session 정보가 유지되지 않음