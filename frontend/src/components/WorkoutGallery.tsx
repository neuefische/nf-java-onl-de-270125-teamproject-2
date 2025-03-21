import {Workout} from "../types/Workout.ts";
import WorkoutCard from "./WorkoutCard.tsx";
import Header from "./Header.tsx";

type WorkoutProps = {
    workouts: Workout[];
};

export default function WorkoutGallery(props: Readonly<WorkoutProps>) {

    if (!Array.isArray(props.workouts)) {
        return <div>No workouts available</div>;
    }


    return (
        <>
            <Header />
            <div className="workout-gallery">
                {props.workouts.map((workout) => (
                    <WorkoutCard key={workout.id} workout={workout} />
                ))}
            </div>
        </>
    );
}