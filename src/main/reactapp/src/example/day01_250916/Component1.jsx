/**
 * @since 2025.09.16
 * 
 * # 컴포넌트
 * - 화면에 최소의 크기로 나눈 모듈
 * - ≒ 함수
 * - 코드와 화면 단을 최적화하기 위해 모듈 단위로 나누는 것
 * 
 * ## 생성방법
 * 1. 생성할 폴더 우클릭 > 새 파일
 * 2. 첫글자를 대문자로 하는 jsx 확장자로 파일 생성 (영문)
 * 3. 함수형 컴푸넌트 vs 클래스형 컴포넌트
 *      - 함수형 컴포넌트를 만들기 위해, 함수 선언 JS 문법으로 컴포넌트 선언
 *      ` function Component1( props ){  } `
 *      - default 컴포넌트 명과 파일명을 일치시킴
 * 4. 컴포넌트 안에 return 되에 출력할 html을 작성
 * 5. 다른(main.jsx) 파일에서 해당 컴포넌트를 import 할 수 있도록 export를 정의 
 */

function Component1( props ){ 

    return ( <h1>First Component</h1> )

} //func end

export default Component1;