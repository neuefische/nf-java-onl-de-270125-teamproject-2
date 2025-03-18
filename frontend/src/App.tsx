import './App.css'

function App() {

    function login() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080': window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

  return (
    <>
      <h1>Willkommen bei Super Fische</h1>
      <div className="card">
        <button onClick={login}>
          Login
        </button>
      </div>
    </>
  )
}

export default App
