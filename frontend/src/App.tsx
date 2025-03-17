import { BrowserRouter as Router, Routes } from "react-router-dom";

import './css/App.css'
import Home from "./components/Home.tsx";



function App() {
    return (
        <>
        <Home />
        <Router>
            <Routes>


            </Routes>
        </Router>
        </>
    );
};

export default App;