package example.day02_250902;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.net.http.WebSocket;

/**
 * we 프로토콜 통신이 왔을 때,
 * 특정한 핸들러(클래스)로 매핑/연결
 */

@Configuration      // SpringContainer Bean 등록
@EnableWebSocket    // webSocket 활성화
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandler chatHandler;    // DI - Server SebSocket

    // [1] Server WebSocket 객체 등록
    // 개발자가 만든 서버 웹 소켓(핸들러) 객체들을 스프링이 인식할 수 있도록 경로·주소를 연결하는 것
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // registry.addHandler( 서버웹소켓 객체, "경로")
        registry.addHandler( chatHandler, "/chat");
    } // func end

} // class end
