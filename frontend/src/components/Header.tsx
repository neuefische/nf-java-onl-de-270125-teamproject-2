import "../css/Header.css"

export default function Header() {
    return (
    <header>

        <h1>Home</h1>
        <nav className={"nav-bar"}>
            <button className={"nav-item"}>Home</button>
            <button className={"nav-item"}>All Workouts</button>
            <button className={"nav-item"}>Add Workout</button>
        </nav>

        <img src={"https://www.der-supernerd.de/superfische-logo.webp"} className={"logo"} />
    </header>)
}