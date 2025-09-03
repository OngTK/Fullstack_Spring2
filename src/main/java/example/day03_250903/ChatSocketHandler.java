package example.day03_250903;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/// ChatSocketHandler
/// TextWebSocketHandler를 상속받아 클라이언트 소켓과 서버 소켓 사이의 이벤트를 관리

@Component      // Spring container Bean 등록
public class ChatSocketHandler extends TextWebSocketHandler {

    // [1] 클라이언트 소켓 - 서버 소켓 연동 시작 이벤트
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ChatSocketHandler.afterConnectionEstablished");
        System.out.println("session = " + session);
        System.out.println("[Server] 연결 성공 / Successful connection with client socket");

    } // func end

    // [2] 클라이언트 소켓 - 서버 소켓 연동 종료 이벤트
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("ChatSocketHandler.afterConnectionClosed");
        System.out.println("session = " + session + ", status = " + status);
        System.out.println("[Server] 연결 종료 / Shutting down the connection with the client socket");

    } // func end

    // [3] 클라이언트 소켓 - 서버 소켓 메시지 인입 이벤트
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("ChatSocketHandler.handleMessage");
        System.out.println("session = " + session + ", message = " + message);
        System.out.println("[Server] 메세지 / message from client socket");

    } // func end

} // class end