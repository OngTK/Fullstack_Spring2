/**
 * # 함수 안에서 함수 호출
 * 
 * [1] function
 * [2] 컴포넌트명 : defualt 컴포넌트 파일명과 일치
 * [3] ( props ) : property 속성으로 <컴포넌트 속성=값 속성=값 /> 
 * [4] 중괄호안에서 JS와 HTML을 작성
 * [5] return 뒤로 HTML 작성
 *      - JSX : HTML + JS
 *      - JPS : HTML + Java
 *      - 함수의 반환값은 무조건 1개 이므로, return뒤 반환은 빈 마크업 <> </>으로 감싼 후 HTML 작성
 * [6] JSX 파일 내 exprot defualt를 단 1개만 정의

 */

// [1] 메인 페이지
function Component2(props) {
    // JS 작성
    const name = "배두훈";

    // return 뒤로 HTML 작성
    return (<div> <Header/> 메인페이지 <Footer/></div>)


} // func end
export default Component2;

// [2] 헤더 페이지
function Header ( props ) {

    return(
        <div>헤더 메뉴</div>
    )
}

// [3] 푸터 페이지
function Footer ( props ){
    return(
        <div>푸터 메뉴 </div>
    )
} 

