import './App.css'
import UpdateWorkout from "./components/UpdateWorkout.tsx";

function App() {

    const testWorkout: {id: string, imagePath: string, description: string, name: string} = {
        id: "1", imagePath: "www.images.de/1", description: "Ich bin ein Workout", name: "Standard"
    };

  return (
    <>
     <UpdateWorkout workout={testWorkout} onWorkoutItemChange={() => console.log("Changed")}/>
    </>
  )
}

export default App
