// [userSlice jsx]
// user의 로그인 상태를 관리하는 slice

import { createSlice } from "@reduxjs/toolkit";

// [1] 전역변수 초기화
// isAuthenticated 로그인 상태 / userInfo 로그인 정보
const initialState = { isAuthenticated: false, userInfo: null }

// [2] 상태 변경 reducer 함수 정의
const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        login: (state, action) => {
            state.isAuthenticated = true;
            state.userInfo = action.payload;
        },
        logout: (state) => {
            state.isAuthenticated = false;
            state.userInfo = null;
        }
    }
});

// [3] Slice export
export default userSlice.reducer

// [4] action export
export const { login, logout } = userSlice.actions;