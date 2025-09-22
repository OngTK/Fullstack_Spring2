import { useEffect, useRef, useState } from "react"

export default function Conponent11(props) {
    // JS ===================================================

    // [1] useRef(초기값); ========================================================
    // 렌더링을 하지 않고 데이터를 참조하는 Hook
    // ref : reference
    const inputRef = useRef(null);

    const submitBtn = () => {
        console.log(inputRef);
        // 현재 참조중인 객체 정보를 반환
        // 결과 : {current: input}
        console.log(inputRef.current);
        // 현재 참조중인 객체를 반환
        // 결과 : input
        inputRef.current.focus();
        // 마우스 커서를 해당 객체로 이동
        // 결과 : 해당 input이 깜빡거림

        console.log(inputRef.current.value);
        // inputRef의 값을 가져옴
        // 결과 : "안녕하세요."

        // 단순 입력이라면 useState 가 아닌 ☆★ useRef ☆★로 value를 가져올 수 있음
    } // func end

    // [2] useState vs useRef ========================================================

    // [2.1] useState 선언
    const [count, setCount] = useState(0);

    // [2.2] count를 초기값으로 하는 useRef 선언
    const countRef = useRef(count);

    // [2.3] count 변경 시, 실행될 useEffect 선언
    useEffect(() => {
        console.log(countRef)
        console.log(countRef.current)
        countRef.current = count;
    }, [count])

    // 검색, 뒤로가기 등 이전 렌더링 값을 기억하게 할 때 주로 사용


    // [3] 입력 Form ========================================================
    const formRef = useRef();
    const submitBtn2 = () => {
        console.log(formRef.current)
        console.log(formRef.current.elements['textInput'].value)            //elements[name속성값]
        console.log(formRef.current.querySelector('.textInput').value)
    }

    // return ===============================================
    return (<>
        <div className="container border rounded p-3 mt-3">
            <div className="mb-3">
                <div className="fs-3 fw-bold mb-2">useRef 예제 1 : 입력</div>
                {/* inputRef는 초기값이 null 이고, input에 ref를 연결하므로서 input 마크업을 참조하도록 함 */}
                <input ref={inputRef} />
                <button type="button" onClick={submitBtn}
                    className="btn btn-outline-success btn-sm ms-2">등록</button>
            </div>

            <div className="mb-3">
                <div className="fs-3 fw-bold mb-2">useRef 예제 2 : 이전 값 기억 </div>
                <p>현재 count : {count} / 이전 count : {countRef.current}</p>
                <button onClick={(e) => { setCount(count + 1) }}
                    className="btn btn-outline-primary btn-sm ms-2"> 증가</button>
            </div>

            <div className="mb-3">
                <div className="fs-3 fw-bold mb-2">useRef 예제 3 : 입력 Form </div>
                <form ref={formRef}>
                    <input type="text" className="form-control textInput" name="textInput" />
                    <select name="selectData" className="form-select">
                        <option> 바나나</option>
                    </select>
                    <textarea className="form-control" name="text2Data"></textarea>
                    <button type="button" onClick={submitBtn2}
                        className="btn btn-outline-success btn-sm ms-2">폼 전송</button>
                </form>
            </div>


        </div>
    </>) // return end
} // Comp end