import {Workout} from "../types/Workout.ts"

import axios from "axios";
import {ChangeEvent, FormEvent, useState} from "react";
import {useNavigate} from "react-router";

type Props = {
    workout: Workout | undefined
    handleUpdatedWorkout: (workout: Workout) => void
}

// Best practice optional props mit typ Workout, useParams brauche ich nicht, von Workout Details übergeben
export default function UpdateWorkout(props: Props) {

    //states und useEffect dürfen nie conditionally aufgerufen werden
    const [givenWorkout, setGivenWorkout] = useState<Workout>(
        props.workout ? props.workout : {id: "", imagePath: "", workoutName: "", description: ""}
    );

    const navigate = useNavigate();

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        console.log("Workout gespeichert")
        saveWorkout();
        event.preventDefault();
        navigate(`/workout/${givenWorkout.id}`); // go back to Detail page, which is not reloaded
    };

    function saveWorkout()
    {
        console.log("ID von gespeichertem Wortkout" + givenWorkout.id)
        // request to backend
        axios.put("/api/workout/" + givenWorkout.id,
            givenWorkout
        ).then(() => props.handleUpdatedWorkout(givenWorkout))
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
                        <input name="workoutName" type="text" value={givenWorkout.workoutName} onChange={event => updateGivenWorkout(event)}/>
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