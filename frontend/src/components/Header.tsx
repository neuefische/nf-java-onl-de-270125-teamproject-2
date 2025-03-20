import "../css/Header.css"
import {useNavigate} from "react-router";

export default function Header() {
    const navigate = useNavigate()

    return (
    <header>

        <h1 className={"slogan-title"}>Super-Fish: Fitness from the Water!</h1>
        <nav className={"nav-bar"}>

            <button className={"nav-item"} onClick={
                () => { navigate("/") }
            }>Home</button>

            <button className={"nav-item"} onClick={
                () => navigate("/workout/")
            }>All Workouts</button>

            <button className={"nav-item"} onClick={
                () => navigate("/addworkout/")
            }>Add Workout</button>
        </nav>

    </header>)
}