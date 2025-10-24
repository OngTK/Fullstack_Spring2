// [2025.10.24]
// 최초로 렌더링되는 컴포넌트로 "라우터" 등의 기본 셋팅 필요

import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import RoleRoute from './components/RoleRoute'
import Header from './components/Header'
import Login from './pages/user/login'

export default function App(props) {

    return (<>
        <Router>
            <Header/>
            <Routes>
                {/* [1] 로그인 회원의 권한에 따른 조건 추가 =============================== */}
                {/* [1.1] 모든 회원  */}
                <Route path='/' element={<h1>메인페이지</h1>} />
                <Route path='/signup' element={<h1>회원가입</h1>} />
                <Route path='/login' element={<Login/>} />

                {/* [1.2] USER + 그 외 */}
                {/* RoleRoute에 roles 리스트를 props에 전달  */}
                <Route element={<RoleRoute roles={["USER", "ADMIN"]} />}>
                    <Route path='/user/info' element={<h1> 마이페이지 </h1>} />
                </Route>
                {/* [1.3] Only Admin */}
                <Route element={<RoleRoute roles={["ADMIN"]} />}>
                    <Route path='/admin/dashboard' element={<h1> 관리자 페이지 </h1>} />
                </Route>

                {/* [1.4] 403, 404 에러 페이지*/}
                <Route path='/forbidden' element={<h1>접근 권한 없음</h1>} />
            </Routes>



        </Router >
    </>)
}