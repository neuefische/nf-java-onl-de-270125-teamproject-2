import {AddWorkout} from "./components/AddWorkout.tsx";
import {Workout} from "./types/Workout.ts";
import './css/App.css'
import Home from "./components/Home.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import WorkoutGallery from "./components/WorkoutGallery.tsx";

export default function App() {

    const [workouts, setWorkouts] = useState<Workout[]>([])

    useEffect(() => {
        console.log("First time rendering App")
        loadWorkouts()
    }, [])
    const loadWorkouts = () => {
        console.log("Load Workouts")
        axios.get("/api/workout")
            .then((response) => {
                console.log("Request finished")
                console.log(response.data)
                setWorkouts(response.data)
            })
            .catch((errorResponse) => {
                console.log(errorResponse)
            })
    }
        console.log("After Request")

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
                <WorkoutGallery workouts={workouts}/>
            </>
    )

}