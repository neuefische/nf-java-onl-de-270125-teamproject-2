import {useState} from "react";
import {Workout} from "../types/Workout.ts";

type Props = {
    saveWorkout(workout: Workout): void
}

export function AddWorkout(props: Readonly<Props>) {

    const [imagePath, setImagePath] = useState<string>("")
    const [workoutName, setWorkoutName] = useState<string>("")
    const [description, setDescription] = useState<string>("")

    return (
        <div>
            <form onSubmit={(event) => {
                event.preventDefault()
                console.log("Form submitted")
                props.saveWorkout({id: "temp1234", imagePath, workoutName, description})}}>
            <input placeholder="Photo upload" value={imagePath} size={80}
                   onChange={(event) => {
                       setImagePath(event.target.value)
                   }}/>
            <br/>
            <input placeholder="Name" value={workoutName} size={80} required={true}
                   onChange={(event) => {
                       setWorkoutName(event.target.value)
                   }}/>
            <br/>
            <textarea placeholder="Description" value={description} rows={10} cols={83}
                      onChange={(event) => {
                          setDescription(event.target.value)
                      }}/>
            <br/><br/>
            <button>Save</button>
            </form>
        </div>
    )
}