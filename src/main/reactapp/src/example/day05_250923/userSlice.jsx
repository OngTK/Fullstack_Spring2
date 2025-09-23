import { createSlice } from '@reduxjs/toolkit'

// [Redux]
// [1] Redux 설치
// npm i @reduxjs/toolkit
// npm i react-redux


// [2] 전역변수의 초기값
// usAuthenticated : 로그인 상태 / true : 로그인 / false : 비로그인
const initialState = { isAuthenticated: false }


// [3] 상태를 변경하는 리듀서 함수들을 정의
// const userSlice = createSlice({name : slice이름, 초기값, reducers : { 액션함수명 : (state)=>{} } } );
const userSlice = createSlice({
    name: "user",  // slice 이름, 하나의 store에 저장되는 일부분
    initialState,   // [2]에서 정의한 초기값을 객체로 설정, 단, 노출이 위험한 정보는 제외
    reducers: {    // slice가 변경되는 함수 정의
        login: (state) => { state.isAuthenticated = true; },     // 로그인 시 실행되는 코드
        logout: (state) => { state.isAuthenticated = false; }    // 로그아웃 시 실행되는 코드
    }
});


// [4] 내보내기

// [4.1] export default>> 파일 내 1개만 존재
// store에 reducer를 import할 수 있음
export default userSlice.reducer 

// [4.2] export >> 파일 내 여러 개 가능
// actions = reducers
export const {login, logout} = userSlice.actions;
// login, logout func을 다른 컴포넌트에서 import 할 수 있도록 export 함
