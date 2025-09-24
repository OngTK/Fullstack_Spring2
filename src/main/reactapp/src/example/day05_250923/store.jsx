import { configureStore } from "@reduxjs/toolkit";
import userSlice from './userSlice.jsx'
import storage from 'redux-persist/lib/storage'

// [ Store ]
// 여러 개의 slice 들을 하나의 store에서 관리하는 코드


// [4] 퍼시스턴스 설정
// const persistConfig = {key : 'Storage에 저장할 키 이름' , storage or session} 
const persistConfig = {key : 'user' , storage}

// [5] reducer에 persist 설정
// const persistedReducer = persistedReducer( 퍼시스턴스 설정, 설정할 리듀서 )
const persistedReducer = persistedReducer( persistConfig , userSlice )


// [1] store 생성
// 모든 컴포넌트에서 store를 참조하여 store에 저장된 슬라이스를 사용할 수 있음
// const store = configureStore({reducer : { 상태명1 : 슬라이스명1 , 상태명2 : 슬라이스명2 ... } })
const store = configureStore({
    reducer : {
        // [2] 슬라이스 등록
        // user 상태에, 개발자가 만든 슬라이스를 대입
        user : userSlice 
    }
})



// [3] 스토어를 다른 컴포넌트가 사용할 수 있게 export default

export default store;

/**
 * @since 2025.09.24
 * 
 * [ Persistence · 퍼시스턴스 ]
 * - 로컬/세션 스토리지에 상태를 저장·유지하는 방법
 * 1) 설치
 *      ` npm i redux-persist `
 * 2) 설정
 * 
 */
