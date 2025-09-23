# 🗂️ React Redux 기초 정리

---

## ✅ 1. Redux란?

- **전역 상태 관리 라이브러리**로, 여러 컴포넌트에서 상태를 일관성 있게 공유할 수 있게 해줍니다.
- 복잡한 컴포넌트 구조에서 **props 전달의 복잡성**을 해결하고, 상태를 중앙에서 관리합니다.

---

## ✅ 2. Redux의 목적

- **store(저장소)**를 만들어 여러 컴포넌트가 **일관된 상태**를 공유하도록 함
- 예시: 로그인 상태, 테마, 자동 로그인 등 **전역 상태**가 필요한 경우

---

## ✅ 3. Redux가 필요한 상황

- 여러 컴포넌트에서 동일한 변수를 공유해야 할 때
- 컴포넌트 간 props 전달이 복잡해질 때
- 전역 상태가 필요한 경우

---

## ✅ 4. 설치 방법

```bash
npm install @reduxjs/toolkit
npm install react-redux
```

---

## ✅ 5. 주요 용어

용어설명Store모든 상태(전역 변수, slice 등)를 저장하는 중앙 저장소| **Slice**     | 상태와 reducer(상태 변경 함수)를 정의하는 단위 |
| **Reducer**   | 상태를 변경하는 함수 (action에 따라 동작) |
| **Action**    | 상태를 변경하기 위해 store에 보내는 신호/함수 |

---

## ✅ 6. Redux 실행 흐름

```
일반 컴포넌트 --(dispatch action)--> Reducer --(state 변경)--> Store --(상태 전달)--> 컴포넌트
```

---

## ✅ 7. 코드 예시

### 📌 1) Slice 정의 (userSlice.jsx)


import { createSlice } from '@reduxjs/toolkit';

// [1] 전역 상태의 초기값
const initialState = { isAuthenticated: false };

// [2] Slice 생성
const userSlice = createSlice({
name: "user",
initialState,
reducers: {
login: (state) => { state.isAuthenticated = true; },
logout: (state) => { state.isAuthenticated = false; }
}
});

// [3] Reducer와 Action 내보내기
export default userSlice.reducer;
export const { login, logout } = userSlice.actions;
```

---

### 📌 2) Store 생성 (store.jsx)

```js
import { configureStore } from "@reduxjs/toolkit";
import userReducer from './userSlice.jsx';

// 여러 slice를 하나의 store에서 관리
const store = configureStore({
    reducer: {
        user: userReducer
    }
});

export default store;
```

---

### 📌 3) Provider로 Store 공급 (main.jsx 등)

```js
import { Provider } from 'react-redux';
import store from './store';
import Component13 from './Component13';

createRoot(document.getElementById('root')).render(
    <Provider store={store}>
        <Component13 />
    </Provider>
);
```

---

### 📌 4) 컴포넌트에서 Redux 상태 사용 (Component13.jsx)


import { useDispatch, useSelector } from 'react-redux';
import { login, logout } from './userSlice.jsx';

export default function Component13(props) {
// [1] store에 저장된 상태 가져오기
const { isAuthenticated } = useSelector(state => state.user);

    // [2] dispatch를 이용한 상태 변경
    const dispatch = useDispatch();

    const loginHandle = () => {
        dispatch(login());
    };

    const logoutHandle = () => {
        dispatch(logout());
    };

    return (
        <>
            <div>
                <h3>Redux 예제</h3>
                {isAuthenticated ?
                    <div>
                        <p>환영합니다.</p>
                        <button type='button' onClick={logoutHandle}>로그아웃 버튼</button>
                    </div>
                    :
                    <div>
                        <p>로그인 상태가 아닙니다.</p>
                        <button type='button' onClick={loginHandle}>로그인 버튼</button>
                    </div>
                }
            </div>
        </>
    );
}

---

## ✅ 8. Redux의 장점

- **상태 일관성**: 여러 컴포넌트에서 동일한 상태를 공유
- **예측 가능한 상태 관리**: 상태 변경이 reducer를 통해서만 이루어짐
- **디버깅 용이**: 상태 변화 추적이 쉬움

---
