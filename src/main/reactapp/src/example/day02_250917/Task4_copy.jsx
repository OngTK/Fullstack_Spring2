/**
  * 전일자(250917) 과제 Task4 강사님 풀이
 * @since 2025.09.18
 */

import { useState } from "react";

export default function Task4_copy(props) {

    // [1] useState : 상태관리 훅
    const [name, setName] = useState("");
    const [phone, setPhone] = useState("");
    const [age, setAge] = useState("");
    const [members, setMembers] = useState([]) // 회원 정보 객체를 저장하는 리스트

    // [2] 등록 버튼
    const onAdd = () => {
        const obj = { name, phone, age }     // 이름, 연락처, 나이 정보를 담는 객체 생성
        members.push(obj)                   // 객체를 members 배열에 push
        setMembers([...members])            // 객체가 push된 새로운 members를 스프레드 연산자로 복사한 후 set

        setName("");
        setPhone("");
        setAge("");
    } // func end

    // [3] 삭제 버튼 
    const onDelete = (deletePhone) => {
        // const newMembers = members.map((member) => { if(member.phone != deletePhone) return member ; })
        const newMembers = members.filter((member)=>{ return member.phone != deletePhone ; });
        setMembers([...newMembers]);
    } // func end


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
                {
                    members.map((member) => {
                        return (
                            <div className="d-flex justify-content-between mb-1">
                                <div >
                                    <span className="fw-bold"> 성명: </span>{member.name}
                                    <span className="fw-bold"> 연락처: </span>{member.phone}
                                    <span className="fw-bold"> 나이: </span>{member.age}
                                </div>
                                <button className="btn btn-outline-danger btn-sm" onClick={() => { onDelete(member.phone) }}>
                                    삭제
                                </button>
                            </div>
                        )
                    })
                }
            </div>

        </div>
    </>) // return end

} // func end