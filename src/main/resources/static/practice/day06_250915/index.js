console.log("index js exe")

// [1] 변수·let 과 상수·const 키워드 ==============================================================================

let count = 10;         // 변수 선언과 초기화
count = 20;             // 변수 수정

const count2 = 20;      // 상소 선언과 초기화 (상수는 수정 불가)

const obj = { name: "포레스텔라" }        // 변수/상수에 객체 담기 가능
const method = () => { }                 // 변수/상수에 func 담기 가능
const forestella = ["배두훈", "강형호", "조민규", "고우림"]         // 변수에 배열 담기 가능

// ** var ** : let 키워드가 없었던 과거에 사용되었던 키워드
// 특징 : 동일 변수명 선언이 가능 >> 중복 변수명에 의한 관리·식별의 복잡함 대두 >> let 키워드의 탄생
var count3 = 30;
var count3 = 40;

// [2] 백틱(``) : 문자열 템플릿, 문자열 내 JS표현식을 연결할 때 사용  =================================================
console.log(`Hello, world! ${count}`)
let html = ``
html += `<div> Hello, world! ${count}</div>`
console.log(html)

// [3] 조건문  ====================================================================================================
// [3.1] if - else if - else 조건문
const point = 85;
if (point >= 90) {
    console.log("A학점")
} else if (point >= 80) {
    console.log("B학점")
} else {
    console.log("C학점")
}

// [3.2] 삼항 연산자 : 간단한 조건문에 적합
// 조건 ? 참 : 거짓
console.log(point >= 90 ? "A학점" : point >= 80 ? "B학점" : "C학점")

// [3.3] 단축평가
// 조건 && true 결과            == true이면 결과값 출력 or False 이면 "false"
// 조건 || false 결과           == true이면 "true" or False 이면 결과값 출력
console.log(point >= 90 && "A학점");
console.log(point >= 90 || "B학점");


// [4] 반복문  =====================================================================================================
const array = [10, 20, 30, 40, 50];


// [4.1] for 문
for (let i = 0; i < array.length; i++) {
    console.log(array[i])
}

// [4.2] 향상된 for 문
// in : index로 반복
for (let i in array) {
    console.log(array[i])
}

// of : 배열[i]를 반복
for (let value of array) {
    console.log(value)
}

// [4.3] forEach
// 특징 : return 불가
array.forEach((value) => { console.log(value) })

// [4.4] map ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
// 특징 : return 가능
let newArray = array.map((value) => { console.log(value); return value })
console.log(newArray)

// [4.5] filter
// 특정 : 조건에 따른 return 가능
let newArray2 = array.filter((value) => { console.log(value); return value > 20; })
console.log(newArray2)