import { useRef } from "react"
import { BrowserRouter, Link, Route, Routes, useNavigate } from "react-router-dom"

// [1] 홈페이지
function Home(props) {

    return (<><div>
        <div className="fs-2 fw-bold mb-4">홈 페이지</div>
        <div>좌측 메뉴에서 회원가입 또는 로그인으로 이동해보세요.</div>
    </div></>)

} // Comp end

// [2] 회원가입
function SignUp(props) {

    const idRef = useRef(null);
    const pwdRef = useRef(null);

    const navigate = useNavigate();

    const signUp = () => {
        console.log(idRef.current.value)
        console.log(pwdRef.current.value)
        const id = idRef.current.value;
        const pwd = pwdRef.current.value;

        // axios 서버 통신 생략

        // 회원가입 성공
        alert("회원가입 성공")
        navigate('/signin')
    }

    return (<><div>
        <div className="fs-2 fw-bold mb-4">회원가입 페이지</div>
        <form >
            <input type="text" placeholder="아이디" className="form-control mb-2" style={{ width: "25%" }}
                name="signupId" ref={idRef} />
            <input type="password" placeholder="비밀번호" className="form-control mb-2" style={{ width: "25%" }}
                name="signupPwd" ref={pwdRef} />
            <button type="button" className="btn btn-outline-primary" onClick={signUp}>회원가입</button>
        </form>
    </div></>)
} // Comp end

// [3] 로그인
function SignIn(props) {

    const signinFormRef = useRef();
    const navigate = useNavigate();

    const signIn = () => {
        console.log(signinFormRef.current.elements['signinId'].value)
        console.log(signinFormRef.current.elements['signupPwd'].value)

        const id = signinFormRef.current.elements['signinId'].value;
        const pwd = signinFormRef.current.elements['signupPwd'].value;

        // axios 서버 통신

        // 로그인 성공
        alert("로그인 성공")
        navigate("/")

    }

    return (<><div>
        <div className="fs-2 fw-bold mb-4">로그인 페이지</div>
        <form ref={signinFormRef}>
            <input type="text" placeholder="아이디" className="form-control mb-2" style={{ width: "25%" }}
                name="signinId" />
            <input type="password" placeholder="비밀번호" className="form-control mb-2" style={{ width: "25%" }}
                name="signupPwd" />
            <button type="button" className="btn btn-outline-primary" onClick={signIn} >로그인</button>
        </form>
    </div></>)

} // Comp end

// [0] 메인 컴포넌트
export default function Task6() {

    return (<>
        <BrowserRouter>
            <div className="container border rounded p-3 mt-3">
                <div className="row">
                    <div className="col-2" style={{ borderRight: "0.25rem solid #eeeeee" }}>
                        <li style={{ listStyle: "none" }}>
                            <ul><Link to="/">홈</Link></ul>
                            <ul><Link to="/signup">회원가입</Link></ul>
                            <ul><Link to="/signin">로그인</Link></ul>
                        </li>
                    </div>
                    <div className="col ms-3">
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/signup" element={<SignUp />} />
                            <Route path="/signin" element={<SignIn />} />
                        </Routes>
                    </div>
                </div>
            </div>

        </BrowserRouter>
    </>)
} // Comp end