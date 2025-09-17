# ⚛️ React `useState` 기초 정리

---

## ✅ 1. 정의

- React에서 **컴포넌트의 상태(state)를 관리**하기 위한 **Hooks 함수**
- 함수형 컴포넌트에서 **동적인 값**을 저장하고 변경할 수 있도록 도와줌

---

## ✅ 2. import 방식

```jsx
import { useState } from 'react';
```

---

## ✅ 3. 기본 사용법

```jsx
const [state, setState] = useState(초기값);
```

- `state`: 현재 상태 값
- `setState`: 상태를 변경하는 함수
- `초기값`: 상태의 초기값 (숫자, 문자열, 객체, 배열 등 가능)

---

## ✅ 4. 예시 코드

### 📌 숫자 상태 관리
```jsx
import { useState } from 'react';

export default function Counter() {
    const [count, setCount] = useState(0);

    return (
        <>
            <h1>Count: {count}</h1>
            <button onClick={() => setCount(count + 1)}>증가</button>
        </>
    );
}
```

### 📌 문자열 상태 관리
```jsx
const [name, setName] = useState("홍길동");
// "홍길동"으로 초기화

return(<>
<input value={name} onChange={(e) => setName(e.target.value)} />
{/* onChange={(e) => setName(e.target.value)}
 e : onChange·변경이 발생할 때, 변경과 관련된 정보를 매개변수로 가져옴
 e.target : onChange가 발생한 대상인 마크업의 정보
 e.target.value : onChange가 발생한 대상인 마크업의 value
  */}
</>)
```

---

## ✅ 5. 특징

- `useState`는 **컴포넌트 내부에서만 사용 가능**
- 상태가 변경되면 해당 컴포넌트는 **자동으로 리렌더링**
- 여러 개의 `useState`를 한 컴포넌트에서 사용할 수 있음

---

## ✅ 6. 주의사항

- `useState`는 **조건문이나 반복문 안에서 사용하면 안됨**
  → 항상 **컴포넌트 최상단에서 호출**해야 함
- 상태 변경 함수(`setState`)를 호출하면 **비동기적으로 작동**
  → 즉시 변경된 값이 반영되지 않을 수 있음

---

## ✅ 7. 고급 사용 예시

### 📌 객체 상태 관리
```jsx
const [user, setUser] = useState({ name: "홍길동", age: 30 });

setUser({ ...user, age: 31 }); // 스프레드 연산자로 일부 속성만 변경
```

### 📌 배열 상태 관리
```jsx
const [items, setItems] = useState([]);

setItems([...items, "새 항목"]); // 기존 배열에 새 항목 추가
```

---

이 마크다운은 **Notion에 그대로 붙여넣기** 하시면 구조와 서식이 잘 유지됩니다.  
원하시면 `.md` 파일로 저장해드릴게요. 저장하시겠어요?
