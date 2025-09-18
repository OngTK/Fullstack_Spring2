/**
 * 1. axios
 *      React.js에서 주로 사용되는 REST API 비동기 통신 함수
 *   1) 설치 · 라이브러리 추가
 *          - 터미널에서 react 종료
 *          - react 최상위 폴더에서 터미널 실행
 *          - https://www.npmjs.com/ 에서 [ axios ] 검색
 *              : react/node page 관련 js 
 *          - 터미널에서 [ npm i axios ] 또는 [ npm install axios ] 입력
 *          - 터미널 재실행
 *   2) 사용법
 *          - axios import
 *              ` import axios from 'axios'; `
 *  
 *   3) axios GET test
 *      참고 : 테스트 환경 https://jsonplaceholder.typicode.com/ (백이 없을 때 임의로 test 데이터 환경 제공)
 * 
 *      (1) 주요 구조
 *          - resoponse.data : 실제 데이터 
 *          - status : http 상태 코드 
 */

import axios from 'axios';

export default function Component9(props) {

    // JS ==========================================================================

    const onAxios1 = async () => {
        // [3.1] axios GET test
        const response1 = await axios.get("https://jsonplaceholder.typicode.com/posts")
        console.log(response1)

        // [3.1.1] QueryString
        const response11 = await axios.get("https://jsonplaceholder.typicode.com/comments?postId=1")
        console.log(response11)

    }

    // [3.2] POST
    const onAxios2 = async () => {
        const obj = { title: "test", body: "testContent", userId: 1 }
        const response2 = await axios.post("https://jsonplaceholder.typicode.com/posts", obj)
        console.log(response2)
    }

    // [3.3] Pust
    const onAxios3 = async () => {
        const obj = { id: 1, title: "test1111111", body: "testContent1111111", userId: 1 }
        const response3 = await axios.put("https://jsonplaceholder.typicode.com/posts/1", obj)
        console.log(response3)
    }

    // [3.4] Delete
    const onAxios4 = async () => {
        const response4 = await axios.delete("https://jsonplaceholder.typicode.com/posts/1")
        console.log(response4)
    }


    // return ======================================================================
    return (<>
        <div className='m-3'>
            <div className='fs-4 fw-bold mb-3'> axious 예제  </div>
            <button className='btn btn-outline-success btn-sm mb-2' onClick={onAxios1}> axios GET</button>
            <br />
            <button className='btn btn-outline-success btn-sm mb-2' onClick={onAxios2}> axios POST</button>
            <br />
            <button className='btn btn-outline-success btn-sm mb-2' onClick={onAxios3}> axios PUT</button>
                        <br />
            <button className='btn btn-outline-success btn-sm mb-2' onClick={onAxios4}> axios DELETE</button>
        </div>
    </>) // return end
} // Comp end