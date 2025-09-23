
export default function LoginPage(props) {

    return (<>
        <div>
            
            <h2>LoginPage</h2>

            <form>
                <input type="text" placeholder="아이디" className="form-control mb-2" style={{ width: "25%" }}/>
                <input type="password" placeholder="비밀번호" className="form-control mb-2" style={{ width: "25%" }}/>
            </form>


        </div>
    </>)
}