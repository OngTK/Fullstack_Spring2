package example.day02_250902;

// ====================[ 서버소켓·핸들러 클래스 ]====================

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // Spring container Bean 등록
public class ChatHandler extends TextWebSocketHandler {

    // WebSocketSession : SW 기반 클라이언트 정보를 저장하는 class
    // HttpSession : HTTP 기반의 클라이언트 정보가 저장하는 class

    // [1] afterConnectionEstablished
    // 클라이언트 소켓이 서버 소켓으로부터 연결을 성공하였을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ChatHandler.afterConnectionEstablished");
        System.out.println("[Server] 클라이언트 연결 성공");

        System.out.println("[Server] 클라이언트 WebSocketSession = " + session);
    }

    // [2] afterConnectionClosed
    // 클라이언트 소켓과 서버소켓의 연결이 종료되었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("ChatHandler.afterConnectionClosed");
        System.out.println("[Server] 클라이언트 연결 종료");
    }

    // [3] handleTextMessage
    // 클라이언트 소켓이 서버 소켓으로 메세지를 보냈을 때 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("ChatHandler.handleTextMessage");
        System.out.println("[Server] 클라이언트 요청 유입");
    }

} // func end
