package example.day03_250903;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component
@EnableWebSocket    // webSocket 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatSocketHandler chatSocketHandler;    // 의존성 주입(Dependency Injection)

    // [1] SocketHandler, 매핑 주소를 등록(registry)
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatSocketHandler, "/chat");
    } // func end

} // class end