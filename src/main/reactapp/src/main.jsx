// import { StrictMode } from 'react'
// import { createRoot } from 'react-dom/client'
// import './index.css'
// import App from './App.jsx'

// createRoot(document.getElementById('root')).render(
//   <StrictMode>
//     <App />
//   </StrictMode>,
// )

// main.jsx에서 index.html 의 id가 root인 마크업에 최초의 컴포넌트(=화면함수)를 렌더링하는 곳

// 1. react root 함수 호출
import { createRoot } from 'react-dom/client';
// 2. index.html에서 root 마크업 가져오기
const root = document.querySelector('#root');
// 3. 가져온 root 마크업에 rendering 할 객체 삽입
const create = createRoot(root);

// 4. 렌더링 하기
// 4.1. 렌더링 할 컴포넌트 가져오기
import App from './App.jsx'
// 4.2. 렌더링
create.render( <App />);

// 위를 요약하면
// createRoot(document.querySelector('#root')).render( <App /> )