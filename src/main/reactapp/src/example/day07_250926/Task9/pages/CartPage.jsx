import { useSelector } from "react-redux"
import Table from '@mui/joy/Table';
import Typography from '@mui/joy/Typography';



export default function CartPage(props) {
    const cartItems = useSelector((state) => state.cart.cartItems);
    console.log(cartItems)
    let Count = 0;// 총 수량 표시용 

    return (<>
        <div className="fs-3 fw-semibold"> 장바구니 </div>

        <div>
            <Table hoverRow sx={{ '& tr > *': { textAlign: 'center' } }}>
                <thead>
                    <tr>
                        <th style={{ width: '25%' }}>상품명</th>
                        <th style={{ width: '25%' }}>가격&nbsp;(원)</th>
                        <th style={{ width: '25%' }}>개수</th>
                        <th style={{ width: '25%' }}>총 가격&nbsp;(원)</th>
                    </tr>
                </thead>
                <tbody>
                    {cartItems.map((item, index) => {
                        return (
                            <tr key={index}>
                                <td>{item.name}</td>
                                <td>{item.price.toLocaleString()}</td>
                                <td>{item.qty}</td>
                                <td>{(item.price * item.qty).toLocaleString()}</td>
                            </tr>) // return end
                    })}
                </tbody>
            </Table>

            <div>
                <div> </div>
                <div >
                    <Typography level="body-md">총 갯수 :</Typography>

                    <Typography level="h4">
                        {(() => {
                            let countQty = 0;
                            cartItems.map((item) => {
                                countQty += item.qty
                            });
                            return (" " + countQty)
                        })()}
                    </Typography>
                    <Typography level="body-md">개</Typography>
                </div>
                <div>
                    <Typography level="body-md">총 가격 :</Typography>
                    <Typography level="h4">
                        {(() => {
                            let countPrice = 0;
                            cartItems.map((item) => {
                                countPrice += item.qty * item.price
                            });
                            return (" " + countPrice.toLocaleString())
                        })()}
                    </Typography>
                    <Typography level="body-md">원</Typography>
                </div>
            </div>
        </div>




    </>) // return end
} // Comp end