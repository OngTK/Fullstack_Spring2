import { useState, useEffect, use } from "react"
import axios, { Axios } from "axios"

// [1] 메인 컴포넌트
export default function Evaluation(props) {

    return (<>
        <div className="container border rounded m-3 p-3">
            <div className="fs-2 fw-bold mb-3"> 익명 영화 토론 플랫폼 </div>
            <Movie />
            <Reply />
        </div>
    </>)
} // Comp end

function Movie(props) {

    // 영화관련 useState 초기화
    const [movies, setMovies] = useState([])
    const [mName, setMname] = useState("")
    const [mDirector, setMdirector] = useState("")
    const [mGenre, setMgenre] = useState("")
    const [mIntro, setMintro] = useState("")
    const [mPassword, setMpassword] = useState("")

    // [1] 영화 전체 출력
    const readAllMovie = async () => {
        const r = await axios.get('http://localhost:8080/movie')
        // console.log(r.data)
        setMovies([...r.data])
    } // func end
    useEffect(() => { readAllMovie() }, [])

    // [2] 영화 등록
    const createMovie = async () => {
        const obj = { mname: mName, mdirector: mDirector, mgenre: mGenre, mintro: mIntro, mpassword: mPassword }
        // console.log(obj)
        const r = await axios.post('http://localhost:8080/movie', obj)

        if (r.data == 1) {
            alert("등록 성공")
            // PK번호 출력을 위하여 목록 재조회
            readAllMovie()
            // Input 초기화
            setMname("")
            setMdirector("")
            setMgenre("")
            setMintro("")
            setMpassword("")
        } else {
            alert("등록 실패")
        }
    } // func end

    // [3] 영화 삭제
    const [mno, setMno] = useState(0)
    const [delmpassword, setDelMpassword] = useState("")

    const deleteMovie = async () => {
        const r = await axios.delete(`http://localhost:8080/movie?mno=${mno}&mPassword=${delmpassword}`)
        if (r.data == 1) {
            alert("삭제 성공")
            // 목록 재조회
            readAllMovie()
            // Input 초기화
            setMno("")
            setDelMpassword("")
        } else {
            alert("삭제 실패")
        }
    } // func end


    // return =============================================================================
    return (<>
        <div className="border rounded p-3 mb-3">
            <div className="fs-4 fw-semibold mb-3"> 영화 </div>

            {/* ====================== 영화 목록 출력 ====================== */}
            <div>
                <div className="fs-5 fw-semibold mb-3">영화 목록</div>
                <table className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th className="align-middle text-center" style={{ width: "10%" }}>영화번호</th>
                            <th className="align-middle text-center" style={{ width: "15%" }}>제목</th>
                            <th className="align-middle text-center" style={{ width: "15%" }}>장르</th>
                            <th className="align-middle text-center" style={{ width: "15%" }}>감독</th>
                            <th className="align-middle text-center" style={{ width: "45%" }}>설명</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            movies.map((movie) => {
                                return (
                                    <tr key={movie.mno}>
                                        <td className="align-middle text-center">{movie.mno}</td>
                                        <td className="align-middle text-center">{movie.mname}</td>
                                        <td className="align-middle text-center">{movie.mgenre}</td>
                                        <td className="align-middle text-center">{movie.mdirector}</td>
                                        <td className="align-middle">{movie.mintro}</td>
                                    </tr>
                                )
                            }) // map end
                        }
                    </tbody>
                </table>
            </div>
            <div className="d-flex justify-content-between">

                {/* ====================== 영화 등록 ====================== */}

                <div className="border rounded p-3 mb-3" style={{ width: "49%" }} >
                    <div className="fs-5 fw-semibold mb-3">영화 등록</div>
                    <form >
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputMname" placeholder="제목"
                                value={mName} onChange={(e) => { setMname(e.target.value) }} />
                            <label htmlFor="inputMname">제목</label>
                        </div>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputMgenre" placeholder="장르"
                                value={mGenre} onChange={(e) => { setMgenre(e.target.value) }} />
                            <label htmlFor="inputMgenre">장르</label>
                        </div>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputMdirector" placeholder="감독"
                                value={mDirector} onChange={(e) => { setMdirector(e.target.value) }} />
                            <label htmlFor="inputMdirector">감독</label>
                        </div>
                        <div className="form-floating mb-2">
                            <textarea className="form-control" id="inputMintro" placeholder="설명"
                                value={mIntro} onChange={(e) => { setMintro(e.target.value) }} >
                            </textarea>
                            <label htmlFor="inputMintro">설명</label>
                        </div>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputMpassword" placeholder="비밀번호" type="password"
                                value={mPassword} onChange={(e) => { setMpassword(e.target.value) }} />
                            <label htmlFor="inputMpassword">비밀번호</label>
                        </div>
                    </form>
                    <div>
                        <button className='btn btn-outline-success btn-sm mb-2' onClick={createMovie} > 등록</button>
                    </div>
                </div>

                {/* ====================== 영화 삭제 ====================== */}
                <div className="border rounded p-3 mb-3" style={{ width: "49%" }} >
                    <div className="fs-5 fw-semibold mb-3"> 삭제</div>

                    <div className="form-floating mb-2">
                        <input className="form-control" id="inputMno" placeholder="영화번호" type="number"
                            value={mno} onChange={(e) => { setMno(e.target.value) }} />
                        <label htmlFor="inputMno">영화번호</label>
                    </div>
                    <div className="form-floating mb-2">
                        <input className="form-control" id="inputDelmpassword" placeholder="비밀번호" type="password"
                            value={delmpassword} onChange={(e) => { setDelMpassword(e.target.value) }} />
                        <label htmlFor="inputDelmpassword">비밀번호</label>
                    </div>
                    <div>
                        <button className='btn btn-outline-danger btn-sm mb-2' onClick={deleteMovie} > 삭제 </button>
                    </div>

                </div>
            </div>
        </div>
    </>)

} // Movie Comp end

function Reply(props) {

    // [0] 관련 정보 useState
    const [movies, setMovies] = useState([])
    const [replies, setReplies] = useState([])
    const [rContent, setRcontent] = useState("")
    const [rPassword, setRpassword] = useState("")
    const [mNo, setMno] = useState(0)

    // [0.1] 영화목록 조회
    const readAllMovie = async () => {
        const r = await axios.get('http://localhost:8080/movie')
        // console.log(r.data)
        setMovies([...r.data])
    } // func end
    useEffect(() => { readAllMovie() }, [])

    // [1] 영화별 댓글 조회
    const readReply = async (selectMno) => {
        const r = await axios.get(`http://localhost:8080/reply/read?mno=${selectMno}`)
        console.log(r)
        setReplies([...r.data])
    } // func end

    // [2] 댓글 등록
    const createReply = async () => {
        const obj = { rcontent: rContent, rpassword: rPassword, mno: mNo }
        const r = await axios.post(`http://localhost:8080/reply`, obj)
        if (r.data == 1) {
            alert("등록 성공")

            setRcontent("")
            setRpassword("")
            setMno("0")
            // readReply(mNo)
        } else {
            alert("등록 실패")
        }
    } // func

    // [3] 댓글 삭제
    const [rNo, setRno] = useState(0)
    const [delRpassword, setDelRpassword] = useState("")

    const deleteReply = async () => {
        const r = await axios.delete(`http://localhost:8080/reply?rno=${rNo}&rPassword=${delRpassword}`)
        if (r.data == 1) {
            alert("삭제 성공")
            setRno(0)
            setDelRpassword("")
        } else {
            alert("삭제 실패")
        }
    }


    // return =============================================================================
    return (<>
        <div className="border rounded p-3">
            <div className="fs-5 fw-semibold mb-3"> 댓글 </div>
            <div className="d-flex justify-content-between">
                <div style={{ width: "49%" }}>
                    <div className="border rounded p-3 mb-3" >

                        {/* ============================================= 댓글 달기 ============================================= */}
                        <div className="fs-5 fw-semibold mb-3"> 댓글 달기</div>
                        <select className="form-select mb-3" aria-label="Default select"
                            onClick={(e) => { setMno(e.target.value) }}>
                            <option value={0}> 영화를 선택하세요.</option>
                            {
                                movies.map((movie) => {
                                    return (
                                        <option value={movie.mno} key={movie.mno}>{movie.mname}</option>
                                    )
                                })
                            }
                        </select>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputrContent" placeholder="댓글 내용"
                                value={rContent} onChange={(e) => { setRcontent(e.target.value) }} />
                            <label htmlFor="inputrContent">댓글 내용</label>
                        </div>

                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputrPassword" placeholder="비밀번호" type="password"
                                value={rPassword} onChange={(e) => { setRpassword(e.target.value) }} />
                            <label htmlFor="inputrPassword">비밀번호</label>
                        </div>
                        <div>
                            <button className='btn btn-outline-success btn-sm mb-2' onClick={createReply} > 등록</button>
                        </div>

                    </div>
                    {/* ============================================= 댓글 삭제 ============================================= */}
                    <div className="border rounded p-3 mb-3">
                        <div className="fs-5 fw-semibold mb-3"> 댓글 삭제</div>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputRno" placeholder="댓글 번호" type="number"
                                value={rNo} onChange={(e) => { setRno(e.target.value) }} />
                            <label htmlFor="inputRno">댓글 번호</label>
                        </div>
                        <div className="form-floating mb-2">
                            <input className="form-control" id="inputDelrpassword" placeholder="비밀번호" type="password"
                                value={delRpassword} onChange={(e) => { setDelRpassword(e.target.value) }} />
                            <label htmlFor="inputDelrpassword">비밀번호</label>
                        </div>
                        <div>
                            <button className='btn btn-outline-danger btn-sm mb-2' onClick={deleteReply} > 삭제 </button>
                        </div>

                    </div>
                </div>

                {/* ============================================= 댓글 목록 ============================================= */}
                <div className="border rounded p-3 mb-3" style={{ width: "49%" }}>
                    <div className="fs-5 fw-semibold mb-3"> 댓글 보기</div>
                    <select className="form-select mb-3" aria-label="Default select"
                        onClick={(e) => { readReply(e.target.value) }}>
                        <option value={0}> 영화를 선택하세요.</option>
                        {
                            movies.map((movie) => {
                                return (
                                    <option value={movie.mno} key={movie.mno}>{movie.mname}</option>
                                )
                            })
                        }
                    </select>
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th className="align-middle text-center" style={{ width: "25%" }}>번호</th>
                                <th className="align-middle text-center" style={{ width: "75%" }}>내용</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                replies.map((reply) => {
                                    return (<tr key={reply.rno}>
                                        <td className="align-middle text-center">{reply.rno}</td>
                                        <td className="align-middle">{reply.rcontent}</td>
                                    </tr>)
                                })
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </>)

} // Reply Comp end