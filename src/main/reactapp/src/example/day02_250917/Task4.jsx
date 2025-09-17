import { use, useState } from "react"

export default function Task4(props) {
    // js
    const [people, setPeople] = useState([
        { id: 1, name: "배두훈", phone: "010-0000-0000", age: "40" }
    ]);
    const [currentId, setCurrentId] = useState(1);
    const [name, setName] = useState("")
    const [phone, setPhone] = useState("")
    const [age, setAge] = useState(0)

    const createInfo = () => {
        const newId = currentId + 1;
        const newPerson = { id: newId, name, phone, age };
        setPeople([...people, newPerson]);
        setCurrentId(newId);
        setName("");
        setPhone("");
        setAge("");
    }

    const deletePerson = (id) => {
        const updatedPeople = people.filter((person) => person.id !== id);
        setPeople(updatedPeople);
    };

    return (<>
        <div className="border rounded m-3 p-4">
            <div className="fs-2 fw-bold mb-1">전화번호부</div>
            <div className="mb-3 row p-3">
                <input type="text" className="form-control col" placeholder="성명" onChange={(e) => {
                    setName(e.target.value);
                    // console.log(name); console.log(e.target.value);
                }} />
                <input type="text" className="form-control col ms-2" placeholder="연락처 (예: 010-1234-5678)" onChange={(e) => {
                    setPhone(e.target.value);
                    // console.log(phone); console.log(e.target.value);
                }} />
                <input type="number" className="form-control col ms-2" placeholder="나이" onChange={(e) => {
                    setAge(e.target.value);
                    // console.log(age); console.log(e.target.value);
                }} />
                <button type="button" className="btn btn-dark ms-2 col-1" onClick={createInfo}> 등록</button>
            </div>
            <ul>
                {people.map((person) => ( <List key={person.id} data={person} onDelete={deletePerson} /> ))}

            </ul>
            <div>총 {people.length} 명</div>
        </div>
    </>)
}


function List({ data, onDelete }) {
  const { id, name, phone, age } = data;

  return (
    <li className="d-flex justify-content-between mb-1">
      <div>
        <span className="fw-bold">성명: </span>{name}
        <span className="fw-bold"> 연락처: </span>{phone}
        <span className="fw-bold"> 나이: </span>{age}
      </div>
      <button className="btn btn-outline-danger btn-sm" onClick={() => onDelete(id)}>
        삭제
      </button>
    </li>
  );
}
