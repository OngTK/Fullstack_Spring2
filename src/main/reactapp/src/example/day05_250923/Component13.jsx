// [1] Store Porvide
// [1.1] Porvide import 
import { useDispatch, useSelector } from 'react-redux'
// [1.2] store import
// import store from './store.jsx'
import { login, logout } from './userSlice.jsx';

// Provider 은 dispatch 보다 먼저 실행되어야하므로 main.jsx로 이동


export default function Component13 (props) {

    // [2] store에 저장된 상태 가져오기

    // [2.1] const 상태변수명 = useSelector( (state) => { state.슬라이스명 } );
    const { isAuthenticated } = useSelector( ( state ) => state.user );
    console.log( isAuthenticated )

    // dispatch를 이용한 전역변수 상태 변경
    const dispatch = useDispatch();
    // [4] 로그인 
    const loginHandle = () => {
        // axios 생략
        dispatch( login() ); // dispath를 이용한 login 액션을 요청
    }

    // [5] 로그아웃
    const logoutHandle = () => {
        // axios 생략
        dispatch( logout() ); // dispath를 이용한 logout 액션을 요청
    }
    return (<>
        {/* [1.3] Provider 로 store를 공급 >> main.jsx 로 이동
        < Provider store={store} > */}
        <div className="container border rounded mt-3 p-3">
            <div>
                <h3> Redux 예제</h3>
                {isAuthenticated ?
                    <div>
                        <p>환영합니다.</p>
                        <button type='button' onClick={logoutHandle}> 로그아웃 버튼 </button>
                    </div>
                    :
                    <div>
                        <p>로그인 상태가 아닙니다.</p>
                        <button type='button' onClick={loginHandle}> 로그인 버튼 </button>
                    </div>
                }
            </div>
        </div>
        {/* </Provider > */}
    </>
    )
}