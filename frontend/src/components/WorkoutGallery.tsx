import {Workout} from "../types/Workout.ts";
import WorkoutCard from "./WorkoutCard.tsx";

type WorkoutProps = {
    workouts: Workout[];
   // deleteTodo: (todoId: string) => void;
}
export default function WorkoutGallery(props: Readonly<WorkoutProps>) {
    const cards = props.workouts.map((workout) =>
        <WorkoutCard key={workout.id}
                  workout={workout}
                 />);
    return (
        <div className="">
            {cards}
        </div>
    );
}