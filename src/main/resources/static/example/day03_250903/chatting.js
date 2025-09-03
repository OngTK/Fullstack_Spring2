console.log("chatting js exe")

// [0.1] concept : 익명/비회원제 채팅
// Math.floor(Math.random() * 끝 값) + 시작값
const randomId = Math.floor(Math.random() * 1000) + 1 // 3자리에 난수 발생
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
    let msg = { type: "join", room: room, nickName: nickName }
    clinet.send(JSON.stringify(msg));
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

    if (message.type == "alarm") {
        html += `<div class="alarm"> <span>${message.message}</span></div>`
    }
    // [2.3.5] type msg 수신 시
    else if (message.type == "msg") {
        // [2.3.6] msg 발송자가 자신이면
        if (message.from == nickName) {
            html += `<div class="secontent">
                        <div class="date"> ${message.date} </div>
                        <div class="content"> ${message.message} </div>
                    </div>`;
        }
        // [2.3.7] msg 발송자가 내가 아니라면
        else {
            html += `<div class="receiveBox">
                        <div class="profileImg">
                            <img  src="default.jpg"/>
                        </div>
                        <div>
                            <div class="recontent">
                                <div class="memberNic"> ${message.from} </div>
                                <div class="subcontent">
                                    <div class="content"> ${message.message} </div>
                                    <div class="date"> ${message.date} </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
        }
    }
    // [2.3.4] 구성한 html 반영
    msgbox.innerHTML += html;

    // [2.3.8] div msgbox 가 고정 사이즈보다 커지면 자동 스크롤 내리기
    msgbox.scrollTop = msgbox.scrollHeight 
    // scrollTop : 현재 dom의 스크롤 상단 꼭지점 위치
    // scrollHeight : 스크롤 전체 길이
    // == 스크롤 상단 꼭지점위치를 위에서 부터 전체길이 만큼 내린 곳에 위치시켜라

} // func end


// [2.4] 에러 / error
clinet.onerror = (event) => {
    console.log("[Client] 에러 / Error")
    console.log(event)
} // func end


// [3] 메세지 전송하기
const onMsgSend = () => {

    // [3.1] input에서 값(msg txt) 가져오기
    const msginput = document.querySelector(".msginput")
    const message = msginput.value;
    // [3.2] 입력값이 없으면 종료
    if (message == '') return

    // [3.3] 메시지 구성
    const msg = { type: "msg", message: message, from: nickName, date : new Date().toLocaleString() }

    // [3.4] 메시지를 server socket 으로 송신
    clinet.send(JSON.stringify(msg))

    // [3.5] input 초기화
    msginput.value = '';

} // func end


// [4] 엔터키를 눌렀을 때, 메세지 발송
const enterKey = () => {
    // [4.1] input에서 enter을 눌렀다 땠을 때!(onkeyup) 정보를 가져옴
    // 참고 : JS의 Keycode(ACSII Code)
    // enter : 13
    if (window.event.keyCode == 13) {
        // [4.2] 메시지 전송 함수
        onMsgSend()
    }
} // func end