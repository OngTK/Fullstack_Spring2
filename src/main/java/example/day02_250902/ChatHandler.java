package example.day02_250902;

// ====================[ 서버소켓·핸들러 클래스 ]====================

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component // Spring container Bean 등록
public class ChatHandler extends TextWebSocketHandler {

    // WebSocketSession : SW 기반 클라이언트 정보를 저장하는 class
    // HttpSession : HTTP 기반의 클라이언트 정보가 저장하는 class

    // [0] 서버에 접속한 Client Socket 명단
    // ArrayList : 동기화 지원 안함
    // Vector : 동기화 지원 : 채팅은 동시 다발적으로 요청·응답이 발생하므로 동기화 필요
    private static final List<WebSocketSession> list = new Vector<>();

    // [1] afterConnectionEstablished
    // 클라이언트 소켓이 서버 소켓으로부터 연결을 성공하였을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ChatHandler.afterConnectionEstablished");
        System.out.println("[Server] 클라이언트 연결 성공");

        // [1.1] 접속 client의 session 정보
        System.out.println("[Server] 클라이언트 WebSocketSession = " + session);

        // [1.2] 접속 client socket을 목록에 저장
        // 받은 메세지를 채팅방에 접속한 소켓들에게 재전송 하기 위해
        list.add(session);

    } // func end

    // [2] afterConnectionClosed
    // 클라이언트 소켓과 서버소켓의 연결이 종료되었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("ChatHandler.afterConnectionClosed");
        System.out.println("[Server] 클라이언트 연결 종료");

        // [2.1] 서버 연결 종료 시 list 에서 session 제거
        list.remove(session);

    } // func end

    // [3] handleTextMessage
    // 클라이언트 소켓이 서버 소켓으로 메세지를 보냈을 때 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("ChatHandler.handleTextMessage");
        System.out.println("[Server] 클라이언트 요청 인입");

        // [3.1] client > server 메세지 확인
        System.out.println("[Server] 메세지 확인" + message);

        // [3.2] server > client 메세지 발송
        // session.sendMessage() : 세션에게 메세지를 보내는 메소드
        // new TextMessage("") : TextMessage 타입의 메시지 객체를 만듬
        // session.sendMessage( new TextMessage("Hello world"));

        // [3.3] client 로부터 유입된 message를
        // server에 접속한 모든 client 에게 전달하기
        for( WebSocketSession clientSocket : list ){
            clientSocket.sendMessage(message);
        }

    } // func end

} // func end
