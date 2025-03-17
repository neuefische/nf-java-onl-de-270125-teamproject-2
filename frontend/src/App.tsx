import {AddWorkout} from "./components/AddWorkout.tsx";
import {Workout} from "./types/Workout.ts";
import axios from "axios";
import './css/App.css'
import Home from "./components/Home.tsx";
import {Workout} from "./types/Workout.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import {Route, Routes} from "react-router";

export default function App() {

    const [workouts, setWokrouts] = useState<Workout[]>([])

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
                setWokrouts(response.data)
            })
            .catch((errorResponse) => {
                console.log(errorResponse)
            })
        console.log("After Request")

        function saveWorkout(workout: Workout) {
            axios.post("/api/workout", workout)
                .then((response) => {
                    console.log(response)
                })
                .catch((errorResponse) => {
                    console.log(errorResponse)
                })


    return (
        <>
            <Home/>
            <AddWorkout saveWorkout={saveWorkout}/>

        </>
    )
}