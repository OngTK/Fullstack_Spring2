import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    {/* 최초 렌더링 위치 */}
      <h1>Hello, world <br /> 최초 렌더링하는 컴포넌트(func) = App() </h1>
    </>
  )
}

export default App
