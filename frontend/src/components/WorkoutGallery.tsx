import {Workout} from "../types/Workout.ts";
import WorkoutCard from "./WorkoutCard.tsx";

type WorkoutProps = {
    workouts: Workout[];
};

export default function WorkoutGallery(props: Readonly<WorkoutProps>) {
    if (!Array.isArray(props.workouts)) {
        return <div>No workouts available</div>;
    }

    const cards = props.workouts.map((workout) =>
        <WorkoutCard key={workout.id} workout={workout} />
    );

    return (
        <div className="workout-gallery">
            {cards}
        </div>
    );
}