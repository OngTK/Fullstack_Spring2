// [2025.10.24]

import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Header(props) {

    // [1] 로그인된 유저 정보 저장
    const [ user , setUser ] = useState( null ); 

    // [2] 최초 컴포넌트 실행 시 유저 정보 요청하기
    const getMyInfo = async () => {
        try {
            const r = await axios.get("http://localhost:8080/api/user/info", { withCredentials: true })
            setUser(r.data); // 반환된 유저 정보를 저장
        } catch (e) {
            setUser(null)
        }
    } // func end

    useEffect(() => { getMyInfo() }, [])

    // [3] 로그아웃 요청하기
    const getLogout = async () => {
        try {
            const r = await axios.get("http://localhost:8080/api/user/logout", { withCredentials: true })
            alert("로그아웃 되었습니다.")
            location.href = "/login"  // 라우터 Navi 대신 서버 사이드 렌더링을 사용
        } catch (e) {

        }
    } // func end

    return (<>
        <div>
            <nav>
                {/* 로그인 상태와 비로그인 상태에 따른 헤더 구분
                    user ? 로그인상태 : 비로그인 상태
                */}
                {user ? <>
                    <span>{user.uname}</span>
                    <button onClick={getLogout}>로그아웃</button>
                    <Link to="/user/info">마이페이지</Link>
                    {/* 로그인 상태면서 관리자인 경우 */}
                    { user.urole == "ADMIN" ? 
                    <><Link to="/admin/dashboard">관리자페이지</Link></> : <></>}
                    
                </> : <>
                    <Link to="/">홈</Link>
                    <Link to="/login">로그인</Link>
                    <Link to="/signup">회원가입</Link>
                </>}
            </nav>
        </div>
    </>)
}