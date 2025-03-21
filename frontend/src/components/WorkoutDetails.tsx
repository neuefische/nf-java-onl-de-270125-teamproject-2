import {useNavigate, useParams} from "react-router";
import axios from "axios";
import {useEffect, useState} from "react";
import {Workout} from "../types/Workout.ts";

type Props =
    {
        handleWorkout: (workout: Workout) => void;
        workout?: Workout;
    }

const WorkoutDetails = (props: Props) => {
    const navigate = useNavigate();

    const params = useParams();
    const id: string | undefined = params.id;
    const [currentWorkout, setCurrentWorkout] = useState<Workout | null | undefined>(props.workout ? props.workout : undefined);

    useEffect(() => {
            fetchWorkout();
    }, [currentWorkout]);

    function fetchWorkout() {
        axios.get(`/api/workout/${id}`)
            .then(response => {
                setCurrentWorkout(response.data)
            }).catch(() => setCurrentWorkout(null))
    }

    const handleDelete = async () => {
        const confirmed = window.confirm("Are you sure you want to delete this workout?");
        if (confirmed) {
            try {
                await axios.delete(`/api/workout/${id}`);
                alert("Workout successfully deleted");
                navigate("/workout");
            } catch (error) {
                console.error(error);
                alert("Error deleting workout");
            }
        }
    };

    const handleUpdate = async () => {
        console.log("handle Update; id:" + id)
        if (currentWorkout) {
            props.handleWorkout(currentWorkout);
        }
    };

    if(currentWorkout === undefined)
        return <div>Loading ...</div>

    return (
        <div>
            {currentWorkout ?
                (<>
                    <h1>{currentWorkout.workoutName}</h1>
                    <p>{currentWorkout.description}</p>
                    <img src={currentWorkout.imagePath} alt={`Image for ${currentWorkout.workoutName}`} />
                    <button onClick={handleDelete}>Delete</button>&nbsp;
                    <button onClick={handleUpdate}>Update</button>
                </>)
            : <h1>No workout to display</h1>}
        </div>
    );
};

export default WorkoutDetails;
