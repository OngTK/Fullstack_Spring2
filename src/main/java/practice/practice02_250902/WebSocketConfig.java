package practice.practice02_250902;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration          // Spring Container Bean 등록
@EnableWebSocket        // WebSocket 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private AlarmHandler alarmHandler;

    // [1] Server - WebSocket 객체 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(alarmHandler, "/alarm");
    } // func end

} // class end
