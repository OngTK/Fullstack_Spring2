package example.day17_251017;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // 레디스 관련 설정 메소드
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        // [1.1] Redis 템플릿 객체 생성
        // Redis 형식을 Map 타입으로 사용하기 위한 설정
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // [1.2] template 를 저장소 factory 에 저장
        template.setConnectionFactory( connectionFactory );

        // 직렬화 : 레디스에 저장된 데이터를 자바 타입으로 변환하는 과정
        // [1.3] template 의 key 값을 String 타입으로 직렬화
        template.setKeySerializer(new StringRedisSerializer());

        // [1.4] template 의 value 값을 JSON/DTO 타입으로 직렬화
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    } // func end

} // class end
