import { useSelector } from "react-redux"



export default function CartPage(props) {
    const cartItems = useSelector((state) => state.cart.cartItems);
    console.log(cartItems)
    

    return (<>
        <div className="fs-3 fw-semibold"> 장바구니 </div>

        <div>
            <table>
                <thead>
                    <tr>
                        <th> 상품명 </th>
                        <th> 가격 </th>
                        <th> 개수 </th>
                        <th> 총가격 </th>
                    </tr>
                </thead>
                <tbody>
                    {}
                </tbody>
            </table>


        </div>

    </>) // return end
} // Comp end