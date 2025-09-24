import { createSlice } from "@reduxjs/toolkit";

// [1] 상태 초기화
const initialState  = {
    cartItems : []
};

// [2] cartSlice 정의

const cartSlice = createSlice({
    name : "cart",
    initialState ,
    reducers:{
        input : (state, action) => { 
            // [2.1] 장바구니에 담은 상품
            const item = action.payload;
            // [2.2] 장바구니에 담은 상품이 이미 장바구니에 있는지 확인
            let existedItem = 0;
            for(let i = 0 ; i < state.cartItems.length ; i++ ){
                if( state.cartItems[i].id == item.id ){
                    existedItem = item.id;
                    console.log(existedItem)
                    break;
                } 
            } 

            // [2.3] 조건문 
            // 이미 있는 상품이면 수량 1증가, 없던 상품이면 신규 추가
            if( existedItem != 0){
                state.cartItems.forEach((item)=>{
                    if(item.id == existedItem){item.qty += 1}
                })                
            } else { 
                state.cartItems.push( {...item, qty : 1} )
            }
        }
    }
}); 

// [3] 내보내기
export default cartSlice.reducer;

export const {input} =cartSlice.actions;