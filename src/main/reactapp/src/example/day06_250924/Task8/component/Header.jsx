import { Link } from "react-router-dom"

export default function Header (props) {

    return(<>
    <div> 
        <ul style={{ listStyle: "none" }} className="d-flex justify-content-between p-0 m-2 ms-5 me-5">
            <li>
                <Link to="/" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                 홈페이지 </Link>
            </li>
            <li>
                <Link to="/menu" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">
                 메뉴 </Link>
            </li>
            <li>
                <Link to="/cart" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 
                장바구니 </Link>
            </li>
        </ul>
    </div>
    
    </>) // return end
} // Comp end