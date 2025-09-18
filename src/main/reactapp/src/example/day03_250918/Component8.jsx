/**
 * React Hook
 * @since 2025.09.18
 * 
 * 1. useState
 * 
 * 2. useEffect
 *      1) 정의
 *          - 특정한 시점에 함수가 실행되는 구조
 *          - 특정 시점 = 현 컴포넌트의 LifeCycle 중 실행/종료 시점
 *          - 함수형 컴포넌트에서 side effect(부수 효과)를 처리하기 위한 Hook 
 *      2) 시점  
 *          - 컴포넌트 생성 / mount
 *          - 컴포넌트 인생 / update = 재실행·재랜더링
 *          - 컴포넌트 죽음·종료 / unmount >> 컴포넌트가 화면에서 없어짐
 *      2) 사용법    
 *          ` import {useEffect} for "react" `
 *          ` useEffect( 정의함수명 ) · useEffect( ( )=> { } ) `
 *              : 최초 실행과 재랜더링 시마다 실행 = 컴포넌트 실행
 *          ` useEffect( ( ) => { } , [ ] ) `
 *              [ ] : 의존성 배열
 *                  : 의존성 배열이 비어있으면 컴포넌트 최초 실행 1회만 실행 
 *                  : 상태 변수를 의존성 배열 내에 대입하여, 상태변수 재랜더링되면 useEffect가 실행
 * 
 *      4) 사용 예
 *           - 데이터 가져오기(fetch), DOM 직접 조작, 타이머 설정, 구독(subscribe) 등의 작업을 할 때 사용
 */

import { useEffect, useState } from "react"

export default function Component8(props) {

    // JS ==================================================================================

    // [3.1]
    // 최초 실행 · 재실행(rerendering) 시에 실행
    useEffect(() => { console.log('[3.1] 컴포넌트 생성(최초실행) + update') })

    // [3.2] update
    // 최초 실행 1회 · 특정 상태 변경(count 변경) 시에만 재실행
    const [count, setCount] = useState(0);

    useEffect(() => { console.log('[3.2] 컴포넌트 업데이트 = update·재실행') }, [count])
    // 의존성 배열에 대입한 count가 setCount 될 때, 현재 useEffect가 자동 실행

    // [3.3] 최초 실행만
    useEffect(() => { console.log('[3.3] 최초 1회만 실행') }, [])
    // 빈 의존성 배열을 대입 

    // ***
    // 버튼 2를 이용하여 useEffect 실행 시점을 살펴보자
    const [count2, setCount2] = useState(0);

    // return ===============================================================================
    return (<>
        <div className="m-4">
            <div className="mb-3">
                <div className="fs-5 fw-bold"> useEffect 예제 : {count}</div>
                <button className="btn btn-primary btn-sm" onClick={(e) => { setCount(count + 1) }}> 버튼 1</button>
                {/* 버튼 클릭 시 : 
                    [3.1] 매번 실행
                    [3.2] 매번 실행
                    [3.3] 버튼과 상관없이 최초 1회 실행 */}
            </div>
            <div>
                <div className="fs-5 fw-bold"> 버튼 2 : {count2}</div>
                <button className="btn btn-primary btn-sm" onClick={(e) => { setCount2(count2 + 1) }}> 버튼 2</button>
                {/* 버튼 클릭 시 : 
                    [3.1] 만 매번 실행 
                    [3.2] 의존성 배열을 삽입하지 않았으므로 영향을 주지 않음
                    [3.3] 버튼과 상관없이 최초 1회 실행 */}
            </div>
        </div>
    </>)
} // func end