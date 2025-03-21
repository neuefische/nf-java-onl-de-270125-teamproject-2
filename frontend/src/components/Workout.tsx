export default function Workout() {

    function logout() {
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        window.open(host + '/logout', '_self')
    }

    return (
        <>
            <h1> Hallo Superfische</h1>
            <button onClick={logout}>Logout</button>
        </>
    );
}