// REACT 실습1 : Task1과 Profile 컴포넌트를 완성하시오.

// Fecth 이용하여 서버로 부터 받은 데이터/자료 

const data = [
    {
        name: 'Hedy Lamarr',
        imageUrl: 'https://i.pravatar.cc/150?img=47'
    },
    {
        name: 'Grace Hopper',
        imageUrl: 'https://i.pravatar.cc/150?img=48'
    }
];

export default function Task1(props) {
    // JS

    return (<>
        <Profile person={data[0]} />
        <Profile person={data[1]} />
    </>)
} // func end

function Profile(props) {
    // JS
    // console.log(props)
    const { name, imageUrl } = props.person;
    // console.log(name)
    // console.log(imageUrl)

    return (<>
        <h2>{name}</h2>
        <img src={imageUrl} />
    </>);
}  // func end
