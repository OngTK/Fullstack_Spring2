package example.day03_250903;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

/// <p>ChatSocketHandler </p>
/// <p>TextWebSocketHandler를 상속받아 클라이언트 소켓과 서버 소켓 사이의 이벤트를 관리</p>

@Component      // Spring container Bean 등록
public class ChatSocketHandler extends TextWebSocketHandler {

    // [0] 접속된 ClientSocket List
    private static final Map<String, List<WebSocketSession>> connectingMap = new Hashtable<>();
    /**
     * <p>ex) ConnectingMap</p>
     * <p>Key : String : ChatRoomNo</p>
     * <p>value : List<WebSocketSession> : Clients</p>
     * <p>{ 0 : [ A , B ] }, { 1 : [ C , D ] }</p>
     */

    // ※ ObjectMapper : JSON <-> Map 변환 라이브러리
    private final ObjectMapper objectMapper = new ObjectMapper();
    /// 주요 메소드
    /// - objectMapper.readValue(JSON 문자열, 변환할 클래스명.class) : Json 객체 > 클래스 타입으로 변환
    /// - objectMapper.writeValueAsString( Map객체 ) : Map 객체 > JSON 으로 변환

    // [1] 클라이언트 소켓 - 서버 소켓 연동 시작 이벤트
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println("ChatSocketHandler.afterConnectionEstablished");
//        System.out.println("session = " + session);
        System.out.println("[Server] 연결 성공 / Successful connection with client socket");

    } // func end

    // [2] 클라이언트 소켓 - 서버 소켓 연동 종료 이벤트
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        System.out.println("ChatSocketHandler.afterConnectionClosed");
//        System.out.println("session = " + session + ", status = " + status);
        System.out.println("[Server] 연결 종료 / Shutting down the connection with the client socket");

        // [2.1] 접속 종료된 session 정보 확인
        String room = (String) session.getAttributes().get("room");         // 기본타입 Object
        String nickName = (String) session.getAttributes().get("nickName");

        // [2.2] room 과 nickName이 일치하는 데이터가 접속명단에 존재하면 >> session 제거
        if ( room != null && nickName != null){
            List<WebSocketSession> list = connectingMap.get(room);  // 해당 방번호의 접속명단 꺼내기
            list.remove(session);                                   // 명단에서 session 제거

            // [2.3] 퇴장한 nickName으로 알림메시지 보내기 (4번 func) - 입장 알림
            alarmMessage(room, nickName+"이 퇴장했습니다.");
        }
    } // func end

    // [3] 클라이언트 소켓 - 서버 소켓 메시지 인입 이벤트
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("ChatSocketHandler.handleMessage");
//        System.out.println("session = " + session + ", message = " + message);
        System.out.println("[Server] 메세지 / message from client socket");

        // [3.1] Client Message
        System.out.println(message.getPayload());

        // [3.2] Message의 JSON 형식을 Map 타입으로 변환
        // ※ RESTFul API 는 @ResponseBody를 통해 JSON <-> Map 변환을 제공
        // Socket 은 지원하지 않으니 직접 해야함

        Map<String, String> msg = objectMapper.readValue(
                message.getPayload(),
                new com.fasterxml.jackson.core.type.TypeReference<Map<String, String>>() {}
        );
        /// As-is : Map<String, String> msg = objectMapper.readValue(message.getPayload(), Map.class);
        /// 아래와 같은 경고 발생
        /// uses unchecked or unsafe operations.
        /// Note: Recompile with -Xlint:unchecked for details.
        /// 요약 : 제네릭 타입이 명시되지 않아 발생하는 오류
        /// ObjectMapper가 Map<String, String> 타입으로 안전하게 변환할 수 있는 코드로 변경

        // [3.3] if 메세지 타입이 join 이면
        if (msg.get("type").equals("join")) {
            String room = msg.get("room");          // 방번호
            String nickName = msg.get("nickName");  // 접속 닉네임

            // [3.4] Client Socket의 부가정보를 추가
            session.getAttributes().put("room", room);
            session.getAttributes().put("nickName", nickName);

            // [3.5] 접속명단 등록
            // IF 방이 존재하면 해당 방에 접속한 session을 저장
            if (connectingMap.containsKey(room)) {
                connectingMap.get(room).add(session); // 해당 방번호에 session 정보를 추가
            } else { // 방이 존재하지 않으면
                List<WebSocketSession> list = new Vector<>();
                list.add(session);
                connectingMap.put(room, list);
            }

            // [3.6] 접속한 nickName으로 알림메시지 보내기 (4번 func) - 입장 알림
            alarmMessage(room, nickName+"이 입장했습니다.");
        }
        System.out.println(connectingMap);
    } // func end

    // [4] session IO 여부에 따른 메세지 전달
    /// @param room : 방번호
    /// @param message : 메시지 내용
    public void alarmMessage (String room, String message) throws Exception {
        // [4.1] Message 정보를 Map 타입으로 구성
        Map<String, String> msg = new HashMap<>();
        msg.put("type", "alarm");
        msg.put("message", message);

        // [4.2] Map 타입을 Json 형식 문자열로 변환
        String sendMsg = objectMapper.writeValueAsString(msg);

        // [4.3] 현재 같은 방에 위치한 모든 세션에게 메시지·알람 전달
        for(WebSocketSession session : connectingMap.get(room)){
            session.sendMessage(new TextMessage(sendMsg));
        }
        
    } // func end


} // class end