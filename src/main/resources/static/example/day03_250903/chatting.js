console.log("chatting js exe")

// [1] ClinetSocket - ServerSocket Connectrion
const clinet = new WebSocket("/chat")

// [2] Func

// [2.1] ClientSocket and ServerSocket Connection Successful
clinet.onopen = (event) => {
    console.log("[Client] 연결 성공 /  Successful Connection with Server")
    console.log(event)

}

// [2.2] Shutting down the connection between ClientSocket and ServerSocket
clinet.onclose = (event) => {
    console.log("[Client] 연결 종료 / Shutting down the connection with Server")
    console.log(event)
}

// [2.3] message from ServerSocket
clinet.onmessage = (event) => {
    console.log("[Client] 메세지 / Incomming Message from Server")
    console.log(event)
}

// [2.4] error
clinet.onerror = (event) => {
    console.log("[Client] 에러 / Error")
    console.log(event)
}