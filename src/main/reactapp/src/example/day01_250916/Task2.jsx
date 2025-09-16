// Task2 : 화면을 구성하시오.


/* [Component 에 CSS 호출하는 방법] */
import './Task2.css'

const products = [
    { title: "무선 키보드", price: 45000, inStock: true },
    { title: "게이밍 마우스", price: 32000, inStock: false },
    { title: "27인치 모니터", price: 280000, inStock: true }
];

export default function Task2(props) {
    // JS

    // retun
    return (<>
    <div class="products">
        <Product item={products[0]} />
        <Product item={products[1]} />
        <Product item={products[2]} />
    </div>
    </>)

} // func end

function Product(props) {
    // js
    const { title, price, inStock } = props.item

    // return
    return (<>
            <ul>
                <li>{title}</li>
                <li>가격 : {price.toLocaleString()}</li>
                <li>{inStock ? "재고 있음" : "재고 없음"}</li>
            </ul>
    </>)
} // func end