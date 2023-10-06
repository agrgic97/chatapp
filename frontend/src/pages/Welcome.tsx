import {useNavigate} from "react-router-dom";

function Welcome() {
  const navigate = useNavigate()

  return (
    <div>
      <img className="mx-auto w-auto" src="src/assets/messenger.svg" alt="icon"/>
      <h1 className="my-10">Welcome to my Messenger!</h1>
      <button className={"mb-2"} onClick={() => navigate("/register", {replace: true})}>Register</button>
      <br/>
      <button onClick={() => navigate("/login",{replace: true})}>Login</button>
      </div>
  )
}

export default Welcome