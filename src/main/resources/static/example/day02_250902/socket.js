console.log("socket js exe")

// ==========================[ 클라이언트 Web Socket 구현 ]==========================

// [1] JS가 Spring(서버)에게 WebSocket 접속·연결을 요청
// [1.1] client web socket 객체 생성
// new WebSocket("WS서버주소") = Java config class에서 매핑한 주소
const client = new WebSocket("ws://localhost:8080/chat");  
//              └> == new WebSocket("/chat"); 

// [2] JS·client socket이 제공하는 주요 메소드
// [2.1] onopen()
// 서버 소켓과 연결이 성공되었을 때 실행
client.onopen = (event) => {
    console.log("[client] 서버소켓과 연동 성공")
}

// [2.2] onclose()
// 서버 소켓과 연결이 중단되었을 때 실행
client.onclose = (event) => {
    console.log("[client] 서버소켓과 연동 종료")
}

// [2.3] onerror()
// 서버 소켓과 연결 중 에러가 발생되었을 때 실행
client.onerror = (event) => {
    console.log("[client] 서버소켓 연결 중 에러 발생")
}

// [2.4] onmessage()
// 서버소켓으로부터 메세지를 수신하였을 때 실행
client.onmessage = ( event ) => {
    console.log("[client]서버소켓으로부터 메세지 수신")
    
    // [2.4.1] server로부터 인입된 메세지를 출력
    // 인입된 event 객체
    console.log(event)
    // 인입된 event 내에 메시지가 저장된 위치 data
    console.log(event.data)

    // [2.4.2] sever로 부터 인입된 메세지를 HTML에 출력
    const msgBox = document.querySelector('.msgBox')
    let html = `<div>${event.data}</div>`
    msgBox.innerHTML += html; 
}

// [2.5] 메세지 송신 함수 만들기 onSend()
const onSend = async () => {
    console.log("onSend func exe")
    // [2.5.1] 입력받은 값 가져오기
    const msg = document.querySelector(".msg").value;

    // [2.5.2] client Socket 으로 msg 보내기
    client.send(msg);
}