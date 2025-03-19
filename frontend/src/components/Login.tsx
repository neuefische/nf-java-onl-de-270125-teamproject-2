export default function Login() {

    function login() {
        //distinguish between local developer environment and productive environment
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        //open the github authorization in the same windows
        window.open(host + '/oauth2/authorization/github', '_self')
    }
    // return a button for the login with login on click functionality
    return (
        <button onClick={login}>Login with Github</button>
    );
}