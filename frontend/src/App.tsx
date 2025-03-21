import {AddWorkout} from "./components/AddWorkout.tsx";
import {Workout} from "./types/Workout.ts";
import './css/App.css'
import Home from "./components/Home.tsx";
import {useEffect, useState} from "react";
import axios from "axios";
import WorkoutGallery from "./components/WorkoutGallery.tsx";
import {Route, Routes, useNavigate} from "react-router";
import WorkoutDetails from "./components/WorkoutDetails.tsx";
import UpdateWorkout from "./components/UpdateWorkout.tsx";

export default function App() {

    const [workouts, setWorkouts] = useState<Workout[]>([])
    const [workout, setWorkout] = useState<Workout>()

    const navigate = useNavigate();

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

    loadWorkouts() // Gregor: Is this okay for all team members? And: It does not work! :-(
    }

    function handleWorkout(workout: Workout) {
        setWorkout(workout);
        navigate(`/workout/${workout.id}/update`);
    }

    function handleUpdatedWorkout(workout: Workout) {
        setWorkout(workout);
    }

    return (
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/workout/:id/update"
                   element={<UpdateWorkout workout={workout} handleUpdatedWorkout={handleUpdatedWorkout}/>}/>
            <Route path="/workout" element={<WorkoutGallery workouts={workouts}/>}/>
            <Route path="/workout/:id" element={<WorkoutDetails handleWorkout={handleWorkout} workout={workout}/>}/>
            <Route path="/addworkout" element={<AddWorkout saveWorkout={saveWorkout}/>}/>
        </Routes>
    )

}