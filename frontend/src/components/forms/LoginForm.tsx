import {FormEvent, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import {useAuth} from "../../provider/AuthProvider.tsx";
import {useNavigate} from "react-router-dom";

function LoginForm() {
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")

  const {setToken} = useAuth()
  const navigate = useNavigate()

  const handleLogin = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    try {
      const res = await axios({
        method: "POST",
        url: `${import.meta.env.VITE_BACKEND_URL}/auth/generateToken`,
        data: {
          username: username,
          password: password
        }
      })
      if (res.data) setToken(res.data)
    } catch (e) {
      console.error(e)
    } finally {
      navigate("/", { replace: true })
    }
  }

  return (
    <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm">
        <img className="mx-auto w-auto" src="src/assets/messenger.svg" alt="icon"/>
        <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-100">
          Sign in to your account
        </h2>
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form className="space-y-6" onSubmit={(e) => handleLogin(e)} method="POST">
          <div>
            <label htmlFor="username" className="flex flex-row justify-start text-sm font-medium leading-6 text-gray-100">
              Username
            </label>
            <div className="mt-2">
              <input
                id="username"
                name="username"
                type="text"
                autoComplete="username"
                required
                className="block w-full rounded-md border-0 p-1.5 bg-gray-100 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-100">
                Password
              </label>
              <div className="text-sm">
                <a href="#" className="font-semibold text-indigo-600 hover:text-indigo-500">
                  Forgot password?
                </a>
              </div>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                className="block w-full rounded-md border-0 p-1.5 bg-gray-100 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
              Sign in
            </button>
          </div>

          <div>
            <Link to={"/register"}
                  className="font-semibold text-indigo-600 hover:text-indigo-500">
              Register a new account!
            </Link>
          </div>

        </form>

      </div>
    </div>
  )
}

export default LoginForm