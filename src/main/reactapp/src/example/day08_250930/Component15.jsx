import Input from '@mui/joy/Input'
import Button from '@mui/joy/Button'
import axios from 'axios'

export default function Component15(props) {

    // [1] axios 기본 통신
    const axios1 = async () => {
        try {
            const r = await axios.get("http://localhost:8080/axios")
            const d = await r.data;
            console.log("[1] : ", d)
        } catch (e) {
            console.log(e)
        }
    } // func end

    // [2] 로그인
    const axios2 = async () => {
        try {
            const obj = { id: "admin", pwd: "qwer1234" }
            const option = {withCredentials : true} //HTTP 인증, Session 유지 true 
            // post는 3번째 자리에 option ==> .post( URL, BODY, OPTION )
            const r = await axios.post("http://localhost:8080/axios", obj, option)
            const d = await r.data;
            console.log("[2] : ", d)
        } catch (e) {
            console.log(e)
        }
    } // func end

    // [3] 내 정보 조회
    const axios3 = async () => {
        try {
            const option = {withCredentials : true} // get은 2번째 자리에 option
            // .get( URL, OPTION )
            const r = await axios.get("http://localhost:8080/axios/info", option)
            const d = await r.data;
            console.log("[3] : ", d)
        } catch (e) { console.log(e) }
    }

    return (<>
        <h3>Component15 </h3>
        <Button onClick={axios1}> axios1 버튼</Button>
        <br />
        <Button onClick={axios2}> axios2 버튼</Button>
        <br />
        <Button onClick={axios3}> axios3 버튼</Button>

    </>)
} // Comp end