// 2025.10.24

import axios from "axios";
import { useState } from "react"

export default function Login() {

    // [1] 입력받은 아이디/ 패스워드 상태
    const [uid, setUid] = useState("");
    const [upwd, setUpwd] = useState("");

    // [2] 로그인 요청
    const postLogin = async () => {
        try {
            const obj = { uid, upwd }
            const r = await axios.post("http://localhost:8080/api/user/login", obj, { withCredentials: true });
            if (r.data != '') { // 로그인 성공
                location.href = "/"; // 서버사이드로 메인페이지 이동 및 전체 페이지 렌더링
            } else {
                alert('로그인 실패')
            }
        } catch (e) {
        }
    } // func end

    return (<>
        <h3>로그인 페이지</h3>
        <form>
            아이디 :
            <input value={uid} onChange={(e) => setUid(e.target.value)} />
            <br />
            비밀번호 :
            <input value={upwd} onChange={(e) => setUpwd(e.target.value)} type="password" />
            <br />
            <button type="button" onClick={postLogin}>로그인</button>

            <br />
            <a href="http://localhost:8080/oauth2/authorization/google">구글 로그인</a>
            <br />
            <a href="http://localhost:8080/oauth2/authorization/kakao">카카오 로그인</a>

        </form>



    </>)
}