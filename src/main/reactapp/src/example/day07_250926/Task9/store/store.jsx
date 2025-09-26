import { configureStore } from "@reduxjs/toolkit";
import cartSlice from './cartSlice'

import { persistStore, persistReducer } from "redux-persist";
import storage from 'redux-persist/lib/storage'

// [1] 퍼시스턴스 설정
const persistConfig = {key : "cart", storage}

// [2] persistedReducer 설정
const persistedReducer = persistReducer( persistConfig, cartSlice)

// [3] store 선언
const store = configureStore({
    reducer : {
        cart : persistedReducer
    }
}) 

// [4] 내보내기
export default store;

export const persistor = persistStore( store )
