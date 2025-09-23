import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom"
import { login } from "../store/userSlice";
import { useRef } from "react";

export default function LoginPage(props) {

    // [1] store에 저장된 상태 가져오기
    // [1.1]
    const { isAuthenticated } = useSelector((state) => state.user)

    // [1.2] dispatch 선언
    const dispatch = useDispatch();

    const navigate = useNavigate();

    // [3] ID input에 ref 삽입
    const idRef = useRef(null);

    // [2] 로그인
    const loginHandle = () => {
        const id = idRef.current.value;

        // axios 생략

        const obj = { id: id, age: 40, phone: "010-0000-0000" }
        console.log(id)

        // 로그인 성공
        // [2.1] store 로그인 상태 변경
        dispatch(login(obj))
        // [2.2] 로그인 성공 안내
        alert("로그인하였습니다.")
        // [2.3] Homepage로 이동
        navigate("/")
    }

    return (<>
        <div>

            <h3 className="mb-3">LoginPage</h3>

            <form>
                <input type="text" placeholder="아이디" className="form-control mb-2" style={{ width: "40%" }} name="inputId" ref={idRef} />
                <input type="password" placeholder="비밀번호" className="form-control mb-2" style={{ width: "40%" }} name="inputPwd" />
                <button type="button" className="btn btn-outline-primary" onClick={loginHandle} >로그인</button>
            </form>


        </div>
    </>)
}