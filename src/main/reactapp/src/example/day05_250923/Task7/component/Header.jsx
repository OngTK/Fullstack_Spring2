import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../store/userSlice";
import { useNavigate } from "react-router-dom";

export default function Header(props) {

    //[1] store에 저장된 로그인 상태에 따라 header 메뉴 설정 하기
    const { isAuthenticated, userInfo } = useSelector((state) => state.user)
    console.log(isAuthenticated)
    console.log(userInfo)

    // [2] dispatch 선언
    const dispatch = useDispatch();

    const navigate = useNavigate();

    const logoutHandle = () => {
        // axios 생략

        // 로그아웃 성공
        alert("로그아웃되었습니다.");
        // [3] store logout action 실행
        dispatch(logout());
        // [4] HomePage로 이동
        navigate("/login");
    } // func end

    return (<>
        <div className="d-flex justify-content-between">
            <div>
                <li style={{ listStyle: "none" }} className="d-flex ">
                    <ul><Link to="/" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 홈페이지 </Link></ul>
                    {/* [1.2 삼항연산자로 로그인 or 프로필 버튼을 출력] */}
                    {isAuthenticated ?
                        <ul><Link to="/profile" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 프로필 </Link></ul> :
                        <ul><Link to="/login" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 로그인 </Link></ul>}
                </li>
            </div>
            <div>

                {isAuthenticated ?
                    <div>
                        <span>안녕하세요. {userInfo.id} 님</span>
                        <button type="button" className="btn btn-outline-secondary btn-sm" onClick={logoutHandle} >로그아웃</button>
                    </div> : ""}
            </div>
        </div>
    </>)
}
