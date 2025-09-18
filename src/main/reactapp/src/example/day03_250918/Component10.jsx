/**
 * React 8173  <> Spring 8080
 * 브라우저 정책상 서로 다른 서버에서 통신이 불가 CORS 정책
 * 
 * ex)
 * (index):1 Access to XMLHttpRequest at 'http://localhost:8080/board' from origin 'http://localhost:5173' has been blocked by CORS policy: 
 * Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 * 
 *      ☆★ Spring Controller 에서 CORS 정책 설정 어노테이션 선언
 *          @CrossOrigin(value="http://localhost:5173") 
 *          CORS 서로 다른 서버간의 요청·응답을 허용하는 어노테이션
 */

import { useEffect, useState } from "react"
import axios from "axios"

export default function Component10(props) {
    // JS ============================================================================= 

    // [1] 데이터를 관리하는 useState
    const [bcontent, setBcontent] = useState("")
    const [bwriter, setBwriter] = useState("")

    // [2] POST 
    const boardWrite = async () => {
        const obj = { bcontent, bwriter }
        const r = await axios.post("http://localhost:8080/board", obj)
        console.log(r)

        setBcontent("")
        setBwriter("")

        boardPrint() // 등록 시 출력 method 실행
    } // func end


    // [3] GET
    const [boards, setBoards] = useState([])
    const boardPrint = async () => {
        const r = await axios.get("http://localhost:8080/board")
        console.log(r)
        setBoards(r.data)
    } // func end

    // [3.1] useEffect, 최초 컴퍼넌트 실행 시, 1회만 출력함수 실행
    useEffect(() => { boardPrint(); }, [])

    // [3.2] 개별조회
    const [updateBcontent, setUpdateBcontent] = useState("")
    const [updateBwriter, setUpdateBwriter] = useState("")
    const boardFind = async (bno) => {
        const r = await axios.get(`http://localhost:8080/board/find?bno=${bno}`)
        console.log(r)
        setUpdateBcontent(r.data.bcontent)
        setUpdateBwriter(r.data.bwriter)
    } // func end

    // [4] PUT
    const boardUpdate = async () => {

        
    } // func end

    // [5] DELETE
    const boardDelete = async (deleteBno) => {
        const r = await axios.delete(`http://localhost:8080/board?bno=${deleteBno}`)
        // boardPrint()
        // boardPrint를 다시 실행할 경우, 필요없는 서버통신이 발생함
        // 이를 방지하기 위해 filter로 boards에서 해당 열만 삭제하고 화면에 setBoard로 랜더링하도록 함        
        const newBoards = boards.filter((board)=>{return board.bno != deleteBno})
        setBoards([...newBoards])
    } // func end


    // return =========================================================================
    return (<>
        <div className="border rounded m-3 p-2">
            <div className="fs-4 fw-bold m-3">Spring - BoardService13</div>
            <div className="m-3">java → expamle → day07_25019 </div>

            {/* [2] POST  */}
            <div className="border rounded m-1 p-3 mb-3">
                <div className="fs-5 fw-bold mb-2"> POST </div>
                <div className="form-floating mb-2">
                    <input value={bcontent} onChange={(e) => { setBcontent(e.target.value) }}
                        className="form-control" id="inputBcontent" placeholder="내용" />
                    <label htmlFor="inputBcontent">내용</label>
                </div>
                <div className="form-floating mb-2">
                    <input value={bwriter} onChange={(e) => { setBwriter(e.target.value) }}
                        className="form-control" id="inputBwriter" placeholder="작성자" />
                    <label htmlFor="inputBwriter">작성자</label>
                </div>
                <button className='btn btn-outline-success btn-sm mb-2' onClick={boardWrite} > 등록</button>
            </div>


            {/* [3] GET  */}
            <div className="border rounded m-1 p-3 mb-3">
                <div className="fs-5 fw-bold mb-2"> GET / DELETE </div>
                <button className='btn btn-outline-success btn-sm mb-2' onClick={boardPrint} > 조회</button>

                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>내용</th>
                            <th>작성자</th>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            boards.map((board) => {
                                const { bno, bcontent, bwriter } = board;
                                return (
                                    <tr key={bno}>
                                        <td>{bno}</td>
                                        <td>{bcontent}</td>
                                        <td>{bwriter}</td>
                                        <td>
                                            {/* [5] DELETE */}
                                            <button className='btn btn-outline-danger btn-sm' onClick={() => { boardDelete(bno) }}> 삭제 </button>
                                        </td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>

            </div>


            {/* [4] PUT  */}
            <div className="border rounded m-1 p-3 mb-3">
                <div className="fs-5 fw-bold mb-2"> PUT </div>
                <select className="form-select mb-2" aria-label="Default select"
                    onChange={ (e) =>{console.log(e.target.value); boardFind(e.target.value);}}>
                        {
                            boards.map(( board )=>{ 
                                return(
                                <option key={board.bno} value={board.bno}>{board.bno}</option>
                            )})
                        }
                </select>

                <div className="form-floating mb-2">
                    <input value={updateBcontent} onChange={(e) => { setUpdateBcontent(e.target.value) }}
                        className="form-control" id="updateBcontent" placeholder="내용" />
                    <label htmlFor="updateBcontent">내용</label>
                </div>
                <div className="form-floating mb-2">
                    <input value={updateBwriter} onChange={(e) => { setUpdateBwriter(e.target.value) }}
                        className="form-control" id="updateBwriter" placeholder="작성자" />
                    <label htmlFor="updateBwriter">작성자</label>
                </div>
                <button className='btn btn-outline-success btn-sm mb-2' onClick={boardUpdate} > 수정</button>

            </div>
        </div>
    </>)
}