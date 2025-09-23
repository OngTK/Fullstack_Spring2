import { Link } from "react-router-dom"

export default function Header(props) {

    return (<>
        <div>
            <li style={{ listStyle: "none" }} className="d-flex ">
                <ul><Link to="/" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 홈페이지 </Link></ul>
                <ul><Link to="/login" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 로그인 </Link></ul>
                <ul><Link to="/profile" className="link-dark link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"> 프로필 </Link></ul>
            </li>
        </div>
    </>)
}