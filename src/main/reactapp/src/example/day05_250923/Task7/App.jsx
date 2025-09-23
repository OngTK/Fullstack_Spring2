import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import Header from "./component/Header";

//[3] store
import store from "./store/store.jsx";
import { Provider } from 'react-redux';

export default function App(props) {

    return (<>
        <Provider store={store}>
            <BrowserRouter>
                <div className="container border rounded mt-3 p-3">
                    {/* [2] 헤더 */}
                    <Header />
                    <div className="mt-2 mb-2">
                        <h2> 루트 페이지 </h2>
                        <div>(연결을 위한 root 이므로 실제로는 별도의 내용을 포함하지 않음)</div>
                    </div>

                    {/* [1] Routes URL 매핑 및 route 화면 출력 위치 */}
                    <div className="mt-2 mb-2">
                        <Routes>
                            <Route path="/" element={<HomePage />} />
                            <Route path="/login" element={<LoginPage />} />
                            <Route path="/profile" element={<ProfilePage />} />
                        </Routes>
                    </div>
                </div>
            </BrowserRouter>
        </Provider>
    </>)
}