package web2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * [ CorsConfig ]
 * <p>
 * example12 참고
 * */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    /**
     * [1] CORS 관련 매핑 설정
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")                // axios + axios 하위 url 통신이 가능
                .allowedOrigins("http://localhost:5173", "http://localhost:5174")
                .allowedMethods("GET" , "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("*");
    } // func end

} // class end