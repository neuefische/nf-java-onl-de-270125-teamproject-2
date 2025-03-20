import {useNavigate} from "react-router";
import axios from "axios";


type WorkoutProps = {
    id: string;
    workoutName: string;
    description: string;
    imagePath: string;
};

const WorkoutDetails = ({ id, workoutName, description, imagePath }:WorkoutProps) => {
    const navigate = useNavigate();

    const handleDelete = async () => {
        const confirmed = window.confirm("Are you sure you want to delete this workout?");
        if (confirmed) {
            try {
                await axios.delete(`/api/workout/${id}`);
                alert("Workout successfully deleted");
                navigate("/workouts");
            } catch (error) {
                console.error(error);
                alert("Error deleting workout");
            }
        }
    };

    return (
        <div>
            <h1>{workoutName}</h1>
            <p>{description}</p>
            <img src={imagePath} alt={`Image for ${workoutName}`} />
            <button onClick={handleDelete}>Delete</button>
        </div>
    );
};

export default WorkoutDetails;
