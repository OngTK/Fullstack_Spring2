import Header from "./component/Header"
import MenuPage from "./pages/MenuPage"
import HomePage from "./pages/HomePage"
import CartPage from "./pages/CartPage"
import { BrowserRouter, Routes, Route } from "react-router-dom"

export default function App(props) {

    return (<>
        <BrowserRouter>
            <div className="container border rounded mt-3 p-3">
                {/* 헤더 영역 */}
                <div className="border rounded mb-3">
                    <Header />
                </div>

                {/* 본문 영역 */}
                <div className="border rounded p-3">
                    <Routes>
                        <Route path="/" element={<HomePage />}></Route>
                        <Route path="/menu" element={<MenuPage />}></Route>
                        <Route path="/cart" element={<CartPage />}></Route>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>

    </>) // return end
} // Comp end