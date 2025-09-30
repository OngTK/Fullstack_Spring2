import Input from '@mui/joy/Input'
import Button from '@mui/joy/Button'
import axios from 'axios'
import { use, useRef } from 'react'

export default function Component15(props) {

    // [1] axios 기본 통신
    const axios1 = async () => {
        try {
            const r = await axios.get("http://localhost:8080/axios")
            const d =  r.data;
            console.log("[1] : ", d)
        } catch (e) {
            console.log(e)
        }
    } // func end

    // [2] 로그인
    const axios2 = async () => {
        try {
            const obj = { id: "admin", pwd: "qwer1234" }
            const option = { withCredentials: true } //HTTP 인증, Session 유지 true 
            // post는 3번째 자리에 option ==> .post( URL, BODY, OPTION )
            const r = await axios.post("http://localhost:8080/axios", obj, option)
            const d =  r.data;
            console.log("[2] : ", d)
        } catch (e) {
            console.log(e)
        }
    } // func end

    // [3] 내 정보 조회
    const axios3 = async () => {
        try {
            const option = { withCredentials: true } // get은 2번째 자리에 option
            // .get( URL, OPTION )
            const r = await axios.get("http://localhost:8080/axios/info", option)
            const d =  r.data;
            console.log("[3] : ", d)
        } catch (e) { console.log(e) }
    }

    // fetch는 기본 전송타입이 form
    // axios는 기본 전송타입이 json

    // [4] 일반 폼
    // form 전송시, form 안에 name으로 매핑이 진행됨
    const formRef1 = useRef();
    const axios4 = async () => {
        try{
            // [4.1] useRef 가 참조중인 dom 객체 가져오기
            const form = formRef1.current;
            // [4.2] headers 정보 입력 
            const option = { headers : { "Content-type" : "application/x-www.form-urlencoded" } } 
            // [4.3] axios 통신
            const r = await axios.post("http://localhost:8080/axios/form",form,option);
            const d = r.data;
            console.log("[4] : " , d)

        }catch(e){
            console.log(e)
        }
    }

    // [5] 첨부파일 폼
    const formRef2 = useRef()
    const axios5 = async () => {
        try{
            // [5.1] uesRef로 참조중인 dom 객체 가져오기
            const form = formRef2.current;
            // [5.2] Form 내에 data를 byte 형식으로 변환
            const formData = new FormData(form);
            // [5.3] headers 정보 
            const option = { headers: { "Content-Type": "multipart/form-data" }  }
            // [5.4] axios
            const r = await axios.post("http://localhost:8080/axios/formdata",formData, option)
            const d = r.data;
            console.log("[5] : " , d)
        } catch(e){
            console.log(e)
        }
    }


    return (<>
        <h3>Component15 </h3>
        <Button onClick={axios1}> axios1 버튼</Button>
        <br />
        <Button onClick={axios2}> axios2 버튼</Button>
        <br />
        <Button onClick={axios3}> axios3 버튼</Button>
        <br />
        <form ref={formRef1}>
            <Input name="id"/>
            <Input name="password"/>
            <Button type='button' onClick={axios4}>axios4</Button>
        </form>
        <form ref={formRef2}>
            <Input type='file' name='file'/>
            <Button type='button' onClick={axios5}>axios5</Button>    
        </form>

    </>)
} // Comp end