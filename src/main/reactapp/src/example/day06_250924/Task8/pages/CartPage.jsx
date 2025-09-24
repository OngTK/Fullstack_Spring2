import { useSelector } from "react-redux"



export default function CartPage(props) {
    const cartItems = useSelector((state) => state.cart.cartItems);
    console.log(cartItems)
    let Count = 0;// 총 수량 표시용 

    return (<>
        <div className="fs-3 fw-semibold"> 장바구니 </div>

        <div>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th> 상품명 </th>
                        <th> 가격 </th>
                        <th> 개수 </th>
                        <th> 총가격 </th>
                    </tr>
                </thead>
                <tbody>
                    {cartItems.map((item) => {
                        return (
                            <tr key={item.id}>
                                <td>{item.name}</td>
                                <td>{item.price.toLocaleString()}</td>
                                <td>{item.qty}</td>
                                <td>{(item.price * item.qty).toLocaleString()}</td>
                            </tr>) // return end
                    })} {/* map end */}
                </tbody>
            </table>
            <div className="row">
                <div className="col"> </div>
                <div className="col"> 총 갯수 :
                    <span className="fs-5 fw-semibold">{(() => {
                        let countQty = 0;
                        cartItems.map((item) => {
                            countQty += item.qty
                        });
                        return (" " + countQty )
                    })()}
                    </span>
                    <span> 개 </span>
                </div>
                <div className="col"> 총 가격 :
                    <span className="fs-5 fw-semibold">{(() => {
                        let countPrice = 0;
                        cartItems.map((item) => {
                            countPrice += item.qty * item.price
                        });
                        return (" " + countPrice.toLocaleString())
                    })()}
                    </span>
                    <span> 원 </span>
                </div>
            </div>
        </div>

    </>) // return end
} // Comp end