/**
 * web1
 *      백틱 템플릿 : 아포스트로피( ` )를 이용한 문자열 내 JS코드 연결방법 
 * web2
 *      JSX 템플릿 : react내 자동 포함
 *          let name = "배두훈"
 *          return <div> { name } </div>
 */

export default function Component3(props) {
    // JS === JS 문법으로 작성
    let name = "배두훈"
    let group = "forestella"

    // HTML === JSX 문법으로 작성
    // 함수의 반환값은 무조건 1개 이므로 
    // 빈 마크업 <></>으로 감싼다.
    return (<>
        <div>{name} 입니다.</div>
        <div>{group}</div>
    </>)
} // func end