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

        {/*다른 컴포넌트 포함시키기, props 속성 할당 */}
        <SubCom1 key1="강형호" key2="38"/>
    </>)
} // func end


// [ props ] 알아보기
function SubCom1( props ){
 
    const obj = {name : "배두훈", age : 40};
    console.log(obj)

    // [1] propes 확인하기
    console.log(props)
    // 결과 : {key1: 'value1', key2: '40'}
    // props이 Object! 하나의 객체로 들어옴!

    // [2] 구조 분해
    const {key1, key2} = props

    return(<> 
        <h4>{obj.name}님의 나이는 {obj.age}입니다.</h4>
        {/* props 속성 출력 */}
        <h4>{props.key1}님의 나이는 {props.key2}입니다.</h4>
        {/* 구조분해 결과 출력 */}
        <h4>{key1}님의 나이는 {key2}입니다.</h4>
    </>)
} // func end