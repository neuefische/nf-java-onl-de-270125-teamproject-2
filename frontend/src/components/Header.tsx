import "../css/Header.css"
import {useLocation, useNavigate} from "react-router";

export default function Header() {
    const navigate = useNavigate()
    const location = useLocation();
    const isActive = (path: string) => location.pathname === path ? "active" : "";

    return (
    <header>

        <h1 className={"slogan-title"}>Super-Fish: Fitness from the Water!</h1>
        <nav className={"nav-bar"}>

            <button className={`nav-item ${isActive("/")}`} onClick={
                () => { navigate("/home") }
            }>Home</button>

            <button
                className={`nav-item ${isActive("/workout/")}`}
                onClick={() => navigate("/workout/")}>
                All Workouts
            </button>

            <button
                className={`nav-item ${isActive("/addworkout/")}`}
                onClick={() => navigate("/addworkout/")}>
                Add Workout
            </button>
        </nav>

    </header>)
}