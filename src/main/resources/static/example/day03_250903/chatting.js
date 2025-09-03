console.log("chatting js exe")

// [0.1] concept : 익명/비회원제 채팅
// Math.floor(Math.random() * 끝 값) + 시작값
const randomId = Math.floor( Math.random() * 1000 ) + 1 // 3자리에 난수 발생
const nickName = `익명${randomId}` // 익명+난수


// [0.2] RoomNo : 상단 메뉴에서 선택
// 전체 채팅 : X / 1번방 room = 1 / 2번방 room = 2
const params = new URL(location.href).searchParams;
const room = params.get("room") || "0";         // QueryString 가져오기, room이 없으면 0


// [1] ClinetSocket - ServerSocket Connectrion ==================================
const clinet = new WebSocket("/chat")


// [2] Func =====================================================================
// [2.1] 연결 성공 / ClientSocket and ServerSocket Connection Successful
clinet.onopen = (event) => {
    console.log("[Client] 연결 성공 / Successful Connection with Server")
    console.log(event)

    // [2.1.1] 방번호에 특정한 닉네임을 등록하는 메시지 보내기 
    // JSON 형식
    let msg = {type : "join", room:room, nickName:nickName }
    clinet.send( JSON.stringify(msg) );
} // func end


// [2.2] 연결 종료 / Shutting down the connection between ClientSocket and ServerSocket
clinet.onclose = (event) => {
    console.log("[Client] 연결 종료 / Shutting down the connection with Server")
    console.log(event)
} // func end


// [2.3] 메시지 / message from ServerSocket
clinet.onmessage = (event) => {
    console.log("[Client] 메세지 / Incomming Message from Server")
    
    // [2.3.1] 메시지 정보 확인
    console.log(event)
    // console.log(event.data)

    // [2.3.2] 받은 메세지를 JSON 타입으로 변환
    const message = JSON.parse(event.data);
    
    // [2.3.3] 받은 메세지의 타입을 확인 및 반영할 html 준비
    const msgbox = document.querySelector(".msgbox")
    let html = '';

    if(message.type == "alarm") {
        html += `<div class="alarm"> <span>${message.message}</span></div>`
    }

    // [2.3.4] 구성한 html 반영
    msgbox.innerHTML += html;

} // func end


// [2.4] 에러 / error
clinet.onerror = (event) => {
    console.log("[Client] 에러 / Error")
    console.log(event)
} // func end