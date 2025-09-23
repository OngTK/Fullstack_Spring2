import { useDispatch, useSelector } from "react-redux"
import { logout } from "../store/userSlice"
import { useNavigate } from "react-router-dom";


export default function ProfilePage(props) {

    // [1] 로그인 정보 가져오기 
    const { isAuthenticated } = useSelector((state) => state.user);

    // [2] dispatch 선언
    const dispatch = useDispatch();

    const navigate = useNavigate();

    const logoutHandle = () => {
        // axios 생략


        // 로그아웃 성공
        alert("로그아웃되었습니다.")
        // [3] store logout action 실행
        dispatch(logout())
        // [4] ghadmfh dlehd
        navigate("/")
    }

    return (<>
        <div>
            <h3 className="mb-3">ProfilePage</h3>
            
        </div>
    </>)
}