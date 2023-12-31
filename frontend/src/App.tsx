import "./App.css";
import AuthProvider from "./provider/AuthProvider.tsx";
import Routes from "./routes";

function App() {
  return (
    <>
      <AuthProvider>
        <Routes/>
      </AuthProvider>
    </>
  )
}

export default App
