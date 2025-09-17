import { useState } from "react"

export default function Component7 (props) {

    // [1] useState 변수 선언 : 구문 분해를 이용한 useState 반환값 변수화
    // [1.1] import
    // [1.2] const [ 변수명, set변수명 ] = useState( 초기값 );
    const [ count , setCount ] = useState( 0 );

    // [1.3] useState 변경 함수
    const countAdd = () => { 
        const newCount = count + 1 ;
        setCount( newCount ); // setCount(변경값) : 새로운 값이 들어오면 컴포넌트가 재랜더링됨
        // 주의 사항 
        //  값이 변경되는 전제조건 
        //      - 값의 변경 : 주소값의 변경 >> 2번 예제
        console.log(count)
        console.log(setCount)
    }

    // [2] useState의 변경 조건 이해하기
    const [array, setArray] = useState(["수박"]);
    // 배열에 대한 useState를 선언
    // 따라서 배열 자체에 대한 변경은 useState에 영향
    // 배열 내 요소들의 변경은 useState에 영향X
    const arrayAdd = () => {
        array.push("사과") ;
        // 배열에 "사과" 추가
        // 배열 자체에 주소는 변경되지 않음 
        // setArray(array); // >> JSX return에 영향을 주지 않음 why? 배열에 요소가 추가된 것이지 자체에 주소는 변경되지 않았음

        // 객체 복사
        // : 새로운 객체를 만들어서 새로운 주소값을 확보하고, 데이터를 복사하므로 동일한 데이터를 갖는 방법 
        setArray( [...array] )
        // ...          : 복사 한다.
        // array        : 배열 객체를
        // [ ]          : 새로운 배열에
        // setArray()   : 새로운 배열로 useState를 변경한다.
    }

    return(<>
        <h3>useState 예제 : {count}</h3>
        <button onClick={countAdd}> count 증가 </button>
        <h3>useState 예제 : {array}</h3>
        <button onClick={arrayAdd}>과일추가</button>
    </>)
}

/**
 * 1. 데이터/자료 : 값
 * 
 * 2. 자료 타입 : 값의 분류
 *  - 기본타입 : 리터럴
 *  - 참조타입 : 객체/주소값
 * 
 * 3. 자료의 주소값 변경 기준
 *  ex1) 1 -> 2 : 리터럴 변경 = 주소 변경
 *  ex2) a -> b : 상기 동일 
 *  ex3) {name : "유재석"} -> {name : "유재석", age : 40 } : 주소 변경X
 * 
 * **리터럴은 static/공유해서 사용**
 * let a = "text"
 * let b = "text"
 * 위의 리터럴은 text로 같으므로 동일한 주소를 갖음
 * 새로운 객체를 선언할 것이면
 * let c = new String("text") : 새로운 객체를 선언하였으므로, 새로운 주소값이 생성
 * when? 리터럴 일 때!!
 * 리터럴이 {} [] 등에 포함되어 있다면, 이는 리터럴에 상관없이 가장 밖의 {} []이 새로운 것이기에
 * 매번 새로운 주소값이 발급
 * 
 */