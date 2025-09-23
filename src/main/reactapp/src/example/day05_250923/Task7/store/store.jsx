// [store jsx]
// 여러 개의 상태를 보관하는 저장소로, 오직 1개만 존재할 수 있음

import { configureStore } from "@reduxjs/toolkit";
import userSlice from './userSlice';

// [1] store 선언
const store = configureStore({
    // reducer : { name명 : import slice 명 }
    reducer : { user : userSlice }
})

// [2] store export
export default store;