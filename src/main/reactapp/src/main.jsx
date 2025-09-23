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


// 2025.09.16 day1 ===================================================
// 참고! render은 무조건 1번만 가능

// import Component1 from './example/day01_250916/Component1.jsx';
// create.render(<Component1></Component1>);

// import Component2 from './example/day01_250916/Component2.jsx';
// create.render(<Component2></Component2>)

// import Component3 from './example/day01_250916/Component3.jsx';
// create.render(<Component3/>)

// import Task1 from './example/day01_250916/Task01.jsx';
// create.render(<Task1/>)

// import Task2 from './example/day01_250916/Task2';
// create.render(<Task2/>)

// 2025.09.17 day2 ===================================================
// import Component4 from './example/day02_250917/Component4';
// create.render(<Component4/>)

// import Component5 from './example/day02_250917/Component5';
// create.render(<Component5/>)

// import Component6 from './example/day02_250917/Component6';
// create.render(<Component6/>)

// import Component7 from './example/day02_250917/Component7';
// create.render(<Component7/>)

// import Task3 from './example/day02_250917/Task3';
// create.render(<Task3/>)

// import Task4 from './example/day02_250917/Task4';
// create.render(<Task4/>)

// 2025.09.18 day3 ===================================================
// import Task4_copy from './example/day02_250917/Task4_copy';
// create.render(<Task4_copy/>)

// import Component8 from './example/day03_250918/Component8';
// create.render(<Component8/>)

// import Component9 from './example/day03_250918/Component9';
// create.render(<Component9/>)

// import Component10 from './example/day03_250918/Component10';
// create.render(<Component10/>)

// import Task5 from './example/day03_250918/Task5';
// create.render(<Task5/>);

// 2025.09.19 내부평가 ===================================================
// import Evaluation from './evaluation/evaluation_250919/evaluation';
// create.render(<Evaluation/>)

// 2025.09.22 day04 =====================================================
// import Conponent11 from './example/day04_250922/Component11';
// create.render(<Conponent11/>)

// import Conponent12 from './example/day04_250922/Component12';
// create.render(<Conponent12/>)

// import Task6 from './example/day04_250922/Task6';
// create.render(<Task6/>)

// 2025.09.23 day05 =====================================================
// import Component13 from './example/day05_250923/Component13';
// // 관례적으로 main jsx에서 삽입.
// // [1.2] store import
// import store from './example/day05_250923/store';
// // [1.1] Porvide import
// import { Provider } from 'react-redux';

// create.render(
//     <Provider store={ store }>
//         <Component13 />
//     </Provider>
// );

import App from './example/day05_250923/Task7/App'
create.render(<App/>)