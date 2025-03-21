import {Workout} from "../types/Workout.ts";
import {useNavigate} from "react-router";


type WorkoutCardProps= {
    workout: Workout;
}

export default function WorkoutCard(props: Readonly<WorkoutCardProps>) {

    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/workout/${props.workout.id}`);
    };


    return (
        <div>
            <div className="workout-card" onClick={handleClick}>
                <h3>{props.workout.workoutName}</h3>
                <p>{props.workout.description}</p>
                <img src="" alt=""/>
            </div>
        </div>
    );
}