import {Workout} from "../types/Workout.ts";


type WorkoutCardProps= {
    workout: Workout;
}

export default function WorkoutCard(props: Readonly<WorkoutCardProps>) {

    return (
        <div>
            <div>
                <h3>{props.workout.workoutName}</h3>
                <p>{props.workout.description}</p>
                <img src="" alt=""/>
            </div>
        </div>
    );
}