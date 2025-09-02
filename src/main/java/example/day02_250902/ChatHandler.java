package example.day02_250902;

// ====================[ 서버소켓·핸들러 클래스 ]====================

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // Spring container Bean 등록
public class ChatHandler extends TextWebSocketHandler {

    // [1] afterConnectionEstablished
    // 클라이언트 소켓이 서버 소켓으로부터 연결을 성공하였을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    // [2] afterConnectionClosed
    // 클라이언트 소켓과 서버소켓의 연결이 종료되었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

    }

    // [3] handleTextMessage
    // 클라이언트 소켓이 서버 소켓으로 메세지를 보냈을 때 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

} // func end
