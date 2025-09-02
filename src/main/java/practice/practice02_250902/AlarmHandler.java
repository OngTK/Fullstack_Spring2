package practice.practice02_250902;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component  // Spring Container Bean 등록
public class AlarmHandler extends TextWebSocketHandler {

    // [0] 접속 client 리스트
    private static final List<WebSocketSession> list = new Vector<>();

    // [1] client 의 ** 서버 접속 성공 **, 실행 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("AlarmHandler.afterConnectionEstablished");
        System.out.println("[Server] 클라이언트 연결 성공");
        System.out.println("session = " + session);

        // [1.1] 접속한 client 의 session 정보를 list 에 저장
        list.add(session);

        // [1.2] 접속한 client 에게 메세지 전송
        for(WebSocketSession client : list){
            client.sendMessage(new TextMessage("익명의 유저가 접속했습니다."));
        }

    } // func end

    // [2] client 의 ** 서버 접속 종료 **, 실행 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("AlarmHandler.afterConnectionClosed");
        System.out.println("[Server] 클라이언트 연결 종료");
        System.out.println("session = " + session + ", status = " + status);

        // [2.1] 연결 종료 클라이언트 session정보를 list에서 삭제
        list.remove(session);

        // [2.2] list의 client에게 메세지 전송
        for(WebSocketSession client : list){
            client.sendMessage(new TextMessage("익명의 유저가 퇴장했습니다."));
        }
    }
} // func end
