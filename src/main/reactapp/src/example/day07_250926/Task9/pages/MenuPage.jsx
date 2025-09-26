import { useDispatch, useSelector } from "react-redux";
import { input } from "../store/cartSlice";

const menu = [
    { id: 1, name: "아메리카노", price: 3000 },
    { id: 2, name: "카페라떼", price: 4000 },
    { id: 3, name: "카푸치노", price: 4500 },
];


export default function MenuPage(props) {

    const dispatch = useDispatch();

    const addtocartHandel = (item) => {
        dispatch(input(item))
    }

    const cartItems = useSelector((state) => state.cart.cartItems);
    console.log(cartItems)

    return (<>
        <div>
            <div className="fs-3 fw-semibold"> 메뉴 </div>

            <div>
                <ul style={{ listStyle: "none" }}>
                    {menu.map((item) => {
                        return (<li key={item.id}>
                            <div className="border rounded m-3 p-3 d-flex justify-content-between">
                                <div style={{ width: "33%" }}>
                                    상품명: <span className="fs-5 fw-semibold">{item.name}</span>
                                </div>
                                <div style={{ width: "33%" }}>
                                    가격: <span className="fs-5 fw-semibold">{item.price.toLocaleString()}</span>
                                </div>
                                <button type="button" className="btn btn-outline-success btn-sm"
                                    onClick={() => { addtocartHandel(item) }}> 장바구니에 담기 </button>
                            </div>
                        </li>
                        ) // return end
                    })} {/* map end  */}
                </ul>
            </div>

        </div>
    </>) // return end
} // Comp end

