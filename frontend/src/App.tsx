import {AddWorkout} from "./components/AddWorkout.tsx";
import {Workout} from "./types/Workout.ts";
import axios from "axios";
import './css/App.css'
import Home from "./components/Home.tsx";

function App() {

    function saveWorkout(workout: Workout) {
        axios.post("/api/workout", workout)
            .then((response) => {
                console.log(response)
            })
            .catch((errorResponse) => {
                console.log(errorResponse)
            })
    }

    return (
        <>
            <Home/>
            <AddWorkout saveWorkout={saveWorkout}/>
        </>
    )
}

export default App
