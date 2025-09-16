# ⚛️ React Component / JSX / Props 정리

---

## ✅ Component

### 📌 정의
- React에서 **독립적이고 재사용 가능한 UI 단위 함수**
- 하나의 컴포넌트는 HTML, JS, CSS를 **통합적으로 구성**할 수 있음

### 📌 특징
- 화면을 **함수 기반으로 구성**
- **재사용성**이 높고 유지보수가 쉬움
- 컴포넌트 내부에서 **JSX 문법**을 사용하여 HTML과 JS를 함께 작성

---

## ✅ Component 만들기

### 📌 기본 구조
```jsx
// 파일명과 컴포넌트명은 일치하는 것이 좋음
export default function Component1(props) {
    // JS 작성 영역

    // HTML 작성 영역 (JSX)
    return (
        <>
            {/* JSX는 반드시 하나의 최상위 요소로 감싸야 함 */}
            <h1>Hello, {props.name}</h1>
        </>
    );
}
```

### 📌 작성 규칙
1. `function`으로 선언
2. 컴포넌트명은 파일명과 일치시키는 것이 권장
3. `props`를 통해 외부 속성 전달
4. `return` 뒤에는 반드시 하나의 최상위 요소로 JSX 작성
5. JSX 파일 내 `export default`는 단 하나만 존재해야 함

---

## ✅ Component 불러오기

### 📌 다른 파일에서 불러오기
```jsx
import Component1 from './Component1';

function App() {
    return (
        <Component1 name="React" />
    );
}
```

### 📌 같은 파일 내에서 사용
```jsx
function App() {
    return (
        <>
            <Component1 name="React" />
        </>
    );
}
```

---

## ✅ JSX

### 📌 정의
- JavaScript XML의 약자로, **HTML과 유사한 문법을 JS 안에서 사용**할 수 있도록 확장한 문법

### 📌 특징
1. **HTML처럼 보이지만 실제로는 JS 객체**
2. **가상 DOM**을 기반으로 작동
3. 브라우저는 JSX를 직접 해석하지 못하므로 **React의 컴파일 과정이 필요**

### 📌 문법 규칙
- 모든 태그는 반드시 **닫혀야 함** (`<br />`, `<img />`)
- JSX 전체는 반드시 **하나의 최상위 요소로 감싸야 함**
- 요소가 여러 줄일 경우, `()`로 감싸야 함
- JSX 내에서 JS 표현식은 `{}`로 감싸서 사용
- JSX 내에서는 HTML 주석이 불가능 → `{/* 주석 */}` 형태로 작성

---

## ✅ Props

### 📌 정의
- 컴포넌트에 **속성값을 전달**하는 방법
- 부모 컴포넌트 → 자식 컴포넌트로 데이터 전달 시 사용

### 📌 사용 예시
```jsx
function App() {
    return <Greeting name="홍길동" />;
}

function Greeting(props) {
    return <h1>안녕하세요, {props.name}님!</h1>;
}

```

> 💡 `props.name`은 `<Greeting name="홍길동" />`에서 전달된 값

---

## Component 에 CSS 호출
import './Task2.css'

```jsx
import './Task2.css'

const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
];

export default function Task2(props) {
    // JS

    // retun
    return (<>
    <div class="products">
        <Product item={products[0]} />
        <Product item={products[1]} />
        <Product item={products[2]} />
    </div>
    </>)

} // func end

function Product(props) {
    // js
    const { title, price, inStock } = props.item

    // return
    return (<>
            <ul>
                <li>{title}</li>
                <li>가격 : {price.toLocaleString()}</li>
                <li>{inStock ? "재고 있음" : "재고 없음"}</li>
            </ul>
    </>)
} // func end
```