// [userSlice jsx]

import { createSlice } from "@reduxjs/toolkit";

// [1] 전역변수 초기화
const initialState = {isAuthenticated : false}

// [2] 상태 변경 reducer 함수 정의
const userSlice = createSlice({
    name : "user",
    initialState,
    reducers : {
        login : (state) => { state.isAuthenticated = true },
        logout : (state) => { state.isAuthenticated = true }
    }
});

// [3] 내보내기
export default userSlice.reducer

export const{login, logout} = userSlice.actions;