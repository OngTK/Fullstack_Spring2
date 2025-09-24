// [store jsx]
// 여러 개의 상태를 보관하는 저장소로, 오직 1개만 존재할 수 있음

import { configureStore } from "@reduxjs/toolkit";
import userSlice from './userSlice';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';                // 로컬스토리지 사용 import
import sessionStorage from "redux-persist/lib/storage/session"; // 세션 사용 import

// [4] 퍼시스턴스 설정_2025.09.24
// const persistConfig = {key : 'Storage에 저장할 키 이름' , storage or session} 
const persistConfig = { key : 'user' , storage }

// [5] reducer에 persist 설정_2025.09.24
// const persistedReducer = persistReducer( 퍼시스턴스 설정, 설정할 리듀서 )
const persistedReducer = persistReducer( persistConfig , userSlice )

// [1] store 선언
const store = configureStore({
    // reducer : { name명 : import slice 명 }
    reducer : {
        // user : userSlice // 250923 퍼시스턴스 적용전
        
        // [6]
        user : persistedReducer    // 250924 퍼시스턴스가 적용된 reducer
    }
})

// [2] store export
export default store;

// [7] persist store 내보내기
export const persistor = persistStore( store );