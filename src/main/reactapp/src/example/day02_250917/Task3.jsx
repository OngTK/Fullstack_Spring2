import { useState } from "react"


export default function Task3(props) {
    // JS

    // [1] 현재 수량을 관리하는 useState
    const [qty, setQty] = useState(0)
    const increase = () => {
        setQty(qty + 1)
    }
    const decrease = () => {
        setQty(qty - 1)
    }

    // [2] 제품명 상태 관리 
    const [product, setProduct] = useState("")
    const changeProduct = (e) => {  
        setProduct(e.target.value)
        console.log(e)
    }

    return (<>
        <div>제품명 : <input value={product} onChange={changeProduct}/></div>
        <div>현재 수량 : <span>{qty}</span></div>
        <button onClick={decrease}>감소</button> <button onClick={increase}>증가</button>
    </>)
}