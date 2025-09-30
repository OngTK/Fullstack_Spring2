import Input from '@mui/joy/Input'
import Button from '@mui/joy/Button'
import axios from 'axios'

export default function Component15(props) {

    // [1]
    const axios1 = async () => {
        try {
            const r = await axios.get("http://localhost:8080/axios")
            const d = await r.data;
            console.log("[1] : ", d)
        } catch (e) {
            console.log(e)
        }
    } // func end

    return (<>
        <h3>Component15 </h3>
        <Button onClick={axios1}> axios1버튼</Button>


    </>)
} // Comp end