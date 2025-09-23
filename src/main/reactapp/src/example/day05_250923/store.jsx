import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice.jsx'

// 여러 개의 slice 들을 하나의 store에서 관리하는 코드

// [1] store 생성
// 모든 컴포넌트에서 store를 참조하여 store에 저장된 슬라이스를 사용할 수 있음
// const store = configureStore({reducer : { 상태명1 : 슬라이스명1 , 상태명2 : 슬라이스명2 ... } })
const store = configureStore({
    reducer : {
        // [2] 슬라이스 등록
        // user 상태에, 개발자가 만든 슬라이스를 대입
        user : userReducer 
    }
})

// [3] 스토어를 다른 컴포넌트가 사용할 수 있게 export default

export default store;