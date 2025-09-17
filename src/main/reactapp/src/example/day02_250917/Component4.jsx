const member = [{ name: "배두훈", birth: 1986 }, { name: "강형호", birth: 1988 }, { name: "조민규", birth: 1990 }, { name: "고우림", birth: 1995 }]


export default function Component4(props) {

    // JS 구역
    const obj = { team: "Forestella", since: 2017 };
    return (<>
        <h3>{obj.team}</h3>
        <h3>결성 :{obj.since}</h3>
        <SubCom info={member[0]} />
        <SubCom info={member[1]} />
        <SubCom info={member[2]} />
        <SubCom info={member[3]} />
    </>)
} // func end

function SubCom(props) {
    console.log(props)
    const { name, birth } = props.info
    return (<>
        <h4>이름 : {name}</h4>
        <h4>출생 : {birth}</h4>
        <SubSubComp name={name} birth={birth} />
    </>)
}


let count2 = 0; // 전역변수
function SubSubComp({ name, birth }) {
    console.log(name)
    console.log(birth)

    // 지역 변수!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // 현재 실행중인 컴포넌트 안에서만 사용되는 변수
    let count = 0;
    const onAdd = () => {
        count++;
        count2++;
        console.log(`지역변수 : ${count}`)
        console.log(`전역변수 : ${count2}`)
        document.querySelector(".countExport").innerHTML = count;
    }

    return (<>
        {/* JSX에서는 onclick >> onClick */}
        <button onClick={onAdd}> 버튼 </button>
        <div class="countExport"> 지역변수 : / 전역변수 : </div>
        <div> --- </div>
    </>)
}