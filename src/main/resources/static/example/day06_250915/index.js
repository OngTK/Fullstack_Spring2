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


// [5] 함수  =====================================================================================================

// [5.1] 선언적 함수
function func1(param1, param2) { }

// [5.2] 익명함수
const func2 = function (param1, param2) { }

// [5.3] 화살표 함수
const func3 = (param1, param2) => { }

// [5.4] 매개변수 기본값 선언
// 별도로 매개변수를 선언하지 않을 경우, 기본값을 사용
const func4 = (param1, param2 = "배두훈") => { }

// [5.5] 함수호출
func1(4, 10)
func3(100, 'Forestella')
func3(100, { name: 'Forestella' })

// [6] 객체   =====================================================================================================
// 변수/상수 : 값을 저장하는 이름
// 값·value : 리터럴

const obj1 = { name: "배두훈", age: "40" }
// JS는 배열도 객체로 취급하긴 함
const obj2 = ["배두훈", "강형호", "조민규", "고우림"]

const name2 = "고우림"
const age2 = "31"

const obj3 = { name2, age2 }

console.log(obj3)
console.log(obj3.name2)
console.log(obj2[1])


// [7] 스프레드 연산자  " ... " ☆★☆★☆★☆★☆★☆★☆★===========================================================
// 특징 : 배열이나 객체를 복사할 때 사용
// >> 주소값을 변경하기 위해
const obj4 = { ...obj1, phone: "010-0000-0000" }
console.log(obj4)               // {name: '배두훈', age: '40', phone: '010-0000-0000'}

const obj5 = [...obj2]
console.log(obj5)               //  ['배두훈', '강형호', '조민규', '고우림']

// 복사를 해와서 결과는 그대로이지만 새로운 주소값이 발급되었다!

const obj6 = [...obj2, "김주택", "박강현", "정필립", "한태인"]
console.log(obj6)               //   ['배두훈', '강형호', '조민규', '고우림', '김주택', '박강현', '정필립', '한태인']


// [8] 구조 분해 할당 ☆★☆★☆★☆★☆★☆★☆★===================================================================
// 객체나 배열에서 값을 분해하는 방법
const user = { name: "배두훈", age: 40 }

// 반드시!!! 객체 내 key과 동일하게 상수/변수를 선언하면 분해 가능
const { name, age } = user;
console.log(name, age)              // 배두훈 40


// [9] 비구조화 할당과 나머지 연산자 ☆★☆★☆★☆★☆★☆★☆★=====================================================
const [num, ...intArray] = [1, 2, 3, 4]
// 인덱스 순서대로 분해 후, 나머지는 ... 에 저장

console.log(num)                // 1
console.log(intArray)           // [2, 3, 4]


// [10] async / await 동기화 ☆★☆★☆★☆★☆★☆★☆★============================================================
// fetch : 기본적으로 비동기
// [10.1] 비동기 fetch
const method1 = () => {
    fetch()
        .then(r => r.json())
        .then(d => console.log(d))
}

// [10.2] 동기 fetch
const method2 = async () => {
    const r = await fetch("url");
    const d = await r.json()
    console.log(d)
}

// [10.3] Promise ☆★☆★☆★☆★☆★☆★☆★
// await은 promise를 사용하는 함수들에 적용됨
const PromiseFunc = async () => {
    //resolve : 성공, reject : 실패
    return await new Promise((resolve, reject) => {
        if (10 > 13) { resolve("10이 13보다 크다") }
        else { reject("10이 13보다 작다") }
    });
}