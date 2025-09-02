console.log("alarm js exe")

// [1] JS > Spring 연결
const client = new WebSocket("/alarm")

// [2] server socket 연결 메소드
client.onopen = (event) => {
    console.log("[client] 서버 소켓과 연동 성공")
} 

// [3] server socket 연결 종료 메소드
client.onclose = (event) =>{
    console.log("[client] 서버 소켓과 연동 종료")
}

// [4] socket 메세지 수신
client.onmessage = (event) => {
    console.log(event)
    
    // [4.1] alert에 메시지 표시
    alert(event.data)
}