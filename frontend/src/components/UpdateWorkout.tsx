import Workout from "../types/Workout.tsx"
type Props = {
    workout: Workout,
    onWorkoutItemChange: () => void
}

import axios from "axios";
import {ChangeEvent, FormEvent, useState} from "react";

export default function UpdateWorkout(props: Props) {

    //const navigate = useNavigate();

    const [givenWorkout, setGivenWorkout] = useState(props.workout);

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        console.log("Workout gespeichert")
        saveWorkout();
        event.preventDefault();
        //navigate("/"); // go back to Dashboard
    };

    function saveWorkout()
    {
        console.log("ID von gespeichertem Wortkout" + props.workout.id)
        // request to backend
        axios.put("/api/workout/" + props.workout.id, {
            givenWorkout
        } as Workout)
    }

    function updateGivenWorkout(event: ChangeEvent<HTMLInputElement>)
    {
        const key = event.target.name;
        const value = event.target.value;
        setGivenWorkout({...givenWorkout, [key]: value})
    }

    return(
        <div className="workout-update">
            <h2>Update your workout</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Path to image file:
                        <input name="imagePath" type="text" value={givenWorkout.imagePath} onChange={event => updateGivenWorkout(event)}/>
                    </label>
                    <label>Workout Name:
                        <input name="name" type="text" value={givenWorkout.name} onChange={event => updateGivenWorkout(event)}/>
                    </label>
                    <label>Description:
                        <input name="description" type="text" value={givenWorkout.description} onChange={event => updateGivenWorkout(event)}/>
                    </label>
                </div>
                <div>
                    <button>Update</button>
                </div>
            </form>
        </div>
    );
}