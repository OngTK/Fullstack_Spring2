/**
 * import { StrictMode } from 'react'
 * import { createRoot } from 'react-dom/client'
 * import './index.css'
 * import App from './App.jsx'
 * 
 * createRoot(document.getElementById('root')).render(
 * <StrictMode>
 * <App />
 * </StrictMode>, )
 */

// main.jsx에서 index.html 의 id가 root인 마크업에 최초의 컴포넌트(=화면함수)를 렌더링하는 곳

// =================================== 기본 세팅 ================================== //
// 1. react root 함수 호출
import { createRoot } from 'react-dom/client';
// 2. index.html에서 root 마크업 가져오기
const root = document.querySelector('#root');
// 3. 가져온 root 마크업에 rendering 할 객체 삽입
const create = createRoot(root);

// ================================== 컴포넌트 렌더링 ================================== //

// 4. 렌더링 하기
// 4.1. 렌더링 할 컴포넌트 가져오기
// import App from './App.jsx'
// 4.2. 렌더링
// create.render( <App> </App>);
// 위를 요약하면
// createRoot(document.querySelector('#root')).render( <App /> )


// 2025.09.16 Component 렌더링 실습 ===================================================
// 참고! render은 무조건 1번만 가능

// import Component1 from './example/day01_250916/Component1.jsx';
// create.render(<Component1></Component1>);

// import Component2 from './example/day01_250916/Component2.jsx';
// create.render(<Component2></Component2>)

import Component3 from './example/day01_250916/Component3.jsx';
create.render(<Component3/>)