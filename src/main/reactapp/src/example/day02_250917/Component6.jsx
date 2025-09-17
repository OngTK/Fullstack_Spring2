// [1.1] useState import 필수!!
import { useState } from "react"

export default function Component6(props) {

    // [1] React 상태관리 라이브러리 
    // ☆★☆★☆★☆★☆★☆★ useState

    // [1.2] useState( 초기값 );
    const state = useState("data");
    console.log(state)
    // 결과 : [0, ƒ]

    // state[0] = 0 = 데이터 = 초기입력값
    console.log(state[0])
    // state[1] = ƒ = 데이터 변화 함수 = 재랜더링 함수
    console.log(state[1])   // 값이 변경되면 실행되는 함수가 포함되어 있음

    // [1.3] 상태변경 함수 
    const onStateChange = () => {
        // data는 변경이 되지만 html return은 변하지 않음
        state[0] = "배두훈";
        // state[1]("변경데이터") 로 함수 index에 데이터 변경을 알려주면, 컴포넌트를 재실행
        state[1]("배두훈");
        console.log(state[0])
    }

    return (<>
        <h3>useState 상태 관리</h3>
        <h4>useState의 초기값 : {state[0]}</h4>
        <h4>useState의 값을 변경 <button onClick={onStateChange}>변경</button></h4>
    </>)
}