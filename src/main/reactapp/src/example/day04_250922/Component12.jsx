import { BrowserRouter, Link, Route, Routes, useSearchParams } from 'react-router-dom'

/**
 *      1. 라우터 라이브러리 설치
 *          npmjs.com
 *      2. 터미널 ` npm i react-router-dom `
 */

// [-] 메인페이지
function Home(prpos) {
    return (<> 메인페이지 </>)
}

// [-] 소개 페이지
function About(props) {
    return (<> 소개 페이지 </>)
}

// [3] 마이페이지 컴포넌트
// QueryString 매개변수 전달
function MyPage(props) {
    const [searchParams] = useSearchParams();           // const [searchParams] = useSearchParams();    queryString구조분해
    const name = searchParams.get('name')               // const 매개변수명 = searchParams.get('매개변수')
    const age = searchParams.get('age')

    return (<>
        <div>마이페이지
            <p>이름 : {name}</p>
            <p>나이 : {age}</p>
        </div>
    </>)
}

// [1] 라우터
// 하나의 컴포넌트에 여러 컴포넌트를 연결하여 사용하는 구조
// 가상의 URL을 만들어서 서로 연결

export default function Component12(prpos) {

    return (<>
        <BrowserRouter>
            <ul>
                <a href="/"> 메인페이지(home / html 방식)</a>

                {/* [1.2] 가상의 URL로 이동할 때는 <a> 이 아닌 <Link>을 사용 = 화면 깜빡임 없이 이동이 가능 */}
                <Link to="/"> 메인페이지(home / 라우터 방식) </Link>
                <Link to='/about'>소개페이지</Link>
                <Link to='/mypage'>마이페이지</Link>
                <Link to='/mypage?name=배두훈&age=40'>마이페이지</Link>
            </ul>

            {/* [1.1] 가상 URL 생성 후 매핑할 컴포넌트를 정의 
            <Route path='가상URL' element={<연결 컴포넌트/>}>
            */}
            <Routes>
                <Route path='/' element={<Home />}> </Route>
                <Route path='/about' element={<About />}></Route>
                <Route path='/mypage' element={<MyPage/>}/>
            </Routes>

        </BrowserRouter>
    </>)
}