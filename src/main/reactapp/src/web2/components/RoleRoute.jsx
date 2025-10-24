// 2025.10.24 
// 서버로부터 권한을 확인하여 해당 권한에 따른 컴포넌트를 제약

import { useEffect, useState } from "react";
import axios from "axios";
import { Navigate, Outlet } from "react-router-dom";

export default function RoleRoute( props ){

    console.log(props);

    // useState : 현재 컴포넌트 내 상태변수
    // + 렌더링 지원
    const [auth, setAuth] = useState( {isAuth : null, urole : null})

    // [1] 서버에 권한 확인 요청
    const checkAuth = async () => {
        try{
            // httpOnly 쿠키 자동 포함을 위한 옵션 필수 >> { withCredentials : true } // 세션도 마찬가지임 
            const r = await axios.get("http://localhost:8080/api/user/check", { withCredentials : true })
            setAuth(r.data);
            console.log(r.data);
        }catch(e){
            setAuth({isAuth : false, urole : null})
        }
    } // func end

    // [2] 최초 렌더링 시 권한 검증
    // useEffect : 컴포넌트의 생명주기에 따른 특정 작업 실행
    useEffect( ( ) => { checkAuth() }, [ ] );

    // [3] 만약 서버로 부터 권한을 받지 못하였다면
    if(auth.isAuth == null ) return <div> 권한 확인 중..</div>;

    // [4] 로그인을 안했다면 로그인 페이지로 이동
    if(auth.isAuth == false ) return <Navigate to="/login"/>;

    // [5] 상위 컴포넌트(App.jsx)로부터 전달받은 권한 중에 포함되지 않으면, "권한 없음"
    if( !props.roles.includes( auth.urole) ) return <Navigate to="/foridden" />;

    // [6] 3,4,5를 통과하였다면 자식 컴포넌트 보여주기
    // Outlet : 자식 컴포넌트 렌더링
    return(<>
        <Outlet />
    </>);
} // comp end