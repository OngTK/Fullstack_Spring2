export default function Component5(props) {
    const items = ['사과', '바나나', '딸기']

    // [1] forEach 반복문
    items.forEach((item) => { console.log(item) })
    // forEach는 return이 불가능

    const newItems = items.forEach((item) => { return item })
    console.log(newItems) // 결과 : undefined

    // [2] map 반복문
    const newItems2 = items.map((item) => { return item })
    console.log(newItems2)  // 결과 ['사과', '바나나', '딸기']

    // [3] 변수에 변화를 일으키는 함수
    const onAdd = () => {
        items.push("수박")
        console.log(items)
    }
    // 함수(컴포넌트)는 1회 호출에 1번 리턴
    // 즉, 한 번 return 된 HTML·JSX는 수정되지 않는다 
    // React : Component 불변성

    // 해결방법 : return을 다시 해서 HTML을 다시 출력 = 함수를 다시 호출
    // => 재랜더링 : 훅( useState )☆★☆★

    return (<>
        <h3> JSX 반복문 forEach vs Map</h3>
        <ul>
            {
                // map은 return이 가능하므로 반복문을 활용한 화면 구현이 가능
                items.map( ( item ) => { return <li> { item } </li> } )
            }
        </ul>
        {/* JSX에서 func 뒤에 () 붙이면 func 실행이 되므로 붙이지 않는다.*/}
        <button onClick={onAdd}> item 추가</button>
    </>)
}