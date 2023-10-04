import {useNavigate} from "react-router-dom";

function Welcome() {
  const navigate = useNavigate()

  return (
    <div>
      <h1>Welcome!</h1>
      <button className={"mt-6 mb-2"} onClick={() => navigate("/register", {replace: true})}>Register</button>
      <br/>
      <button onClick={() => navigate("/login",{replace: true})}>Login</button>
      </div>
  )
}

export default Welcome