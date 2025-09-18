/**
 * TASK5 : 기존 TASK4.jsx 이어 useEffect/axios를 활용해서 
 * spring+mybatis 서버 와 통신하여 TASK5 완성(등록/전체조회/삭제)하시오.
 * @since 2025.09.18
 */

import { useEffect, useState } from "react";
import axios from "axios";

export default function Task5() {

    // [1] useState : 상태관리 훅
    const [name, setName] = useState("");
    const [phone, setPhone] = useState("");
    const [age, setAge] = useState("");
    const [members, setMembers] = useState([]) // 회원 정보 객체를 저장하는 리스트

    // [2] 등록 버튼
    const onAdd = async () => {
        const obj = { name, phone, age }     // 이름, 연락처, 나이 정보를 담는 객체 생성

        const r = await axios.post("http://localhost:8080/task05/member", obj)

        if (r.data = 1) {
            alert("저장 성공")

            // members.push(obj)                   // 객체를 members 배열에 push
            // setMembers([...members])            // 객체가 push된 새로운 members를 스프레드 연산자로 복사한 후 set
            
            // 위와 같이 처리할 경우, 방금 저장한 레코드는 PK가 없는 상태로 화면에 보여지므로
            // 삭제 버튼이 동작하지 않는 것을 확인
            // 번거롭지만 readAllMember를 다시 실행하여 members 를 다시 저장하는 것으로 해결
            readAllMember()

            setName("");
            setPhone("");
            setAge("");
        } else {
            alert("저장 실패")
        }
    } // func end

    // [3] 삭제 버튼 
    // 연락처 중복이 없다는 가정하에, 연락처 정보를 받아서 비교 후 배열에서 삭제
    const onDelete = async (deleteMno) => {
        const r = await axios.delete(`http://localhost:8080/task05/member?mno=${deleteMno}`)

        if( r.data = 1) {
            alert("삭제 성공")
        // const newMembers = members.map((member) => { if(member.phone != deletePhone) return member ; })
        const newMembers = members.filter((member) => { return member.mno != deleteMno; });
        setMembers([...newMembers]);
        } else {
            alert("삭제 실패")
        }

    } // func end

    // [4] 전체조회
    const readAllMember = async () => {
        const r = await axios.get("http://localhost:8080/task05/member")
        setMembers(r.data)
    }

    // useEffect가 컴포넌트 최초 실행 시, 전체조회 func 1회 실행
    useEffect(() => { readAllMember() }, [])

    // return ===============================================================
    return (<>
        <div className="border rounded m-3 p-4">
            <div className="fs-2 fw-bold mb-1">전화번호부</div>
            {/* input + 등록 버튼 */}
            <div className="mb-3 row p-3">
                <input type="text" className="form-control col" placeholder="성명"
                    onChange={(e) => { setName(e.target.value) }} value={name} />
                <input type="text" className="form-control col ms-2" placeholder="연락처 (예: 010-1234-5678)"
                    onChange={(e) => { setPhone(e.target.value) }} value={phone} />
                <input type="number" className="form-control col ms-2" placeholder="나이"
                    onChange={(e) => { setAge(e.target.value) }} value={age} />
                <button type="button" className="btn btn-success ms-2 col-1"
                    onClick={onAdd}> 등록</button>
            </div>

            {/* members 리스트 출력 */}
            <div>
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th className="align-middle text-center">성명</th>
                            <th className="align-middle text-center">연락처</th>
                            <th className="align-middle text-center">나이</th>
                            <th className="align-middle text-center">비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            members.map((member) => {
                                const { mno, name, phone, age } = member;
                                return (
                                    <tr key={mno}>
                                        <td className="align-middle text-center">{name}</td>
                                        <td className="align-middle text-center">{phone}</td>
                                        <td className="align-middle text-center">{age}</td>
                                        <td className="d-flex justify-content-center">
                                            {/* ================================= [5] DELETE ================================= */}
                                            <button className='btn btn-outline-danger btn-sm' onClick={()=>{onDelete(mno)}}> 삭제 </button>
                                        </td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
        </div>
    </>) // return end

} // Comp end