console.log("util js exe")

// 1. 함수 선언
const hello = (name) => { return name+"님"}

// 2. 함수 내보내기
//  export default는 js 파일 당 1개 만 가능
export default hello;

// NamedExport : 이름 붙여 내보내기
// 여러 개 export 가능
export const PI = 3.14;
export const E = 2.71;