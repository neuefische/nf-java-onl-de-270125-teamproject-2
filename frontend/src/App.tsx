import './App.css'
import {Route, Routes} from "react-router";
import Login from "./components/Login.tsx";
import ProtectedRoutes from "./components/ProtectedRoutes.tsx";
import {useEffect, useState} from "react";
import Workout from "./components/Workout.tsx";
import axios from "axios";

function App() {

    const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false)

    function getMe() {
        axios.get("/api/auth/me")
            .then(() => setIsLoggedIn(true))
            .catch(e => console.error(e))
    }

    useEffect(getMe, []);

    return (
        <Routes>
            <Route path="/" element={<Login/>}/>
            <Route element={<ProtectedRoutes isLoggedIn={isLoggedIn}/>}>
                <Route path="/workout" element={<Workout/>}/>
            </Route>
        </Routes>
    )
}

export default App
