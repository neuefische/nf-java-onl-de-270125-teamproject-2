import Header from "./Header.tsx";

export default function Home() {

    return (
        <>
            <Header />
            <div className={"container-home-image"}>
                <img src={"https://www.der-supernerd.de/home-pexels-rui-dias-469842-1472887.jpg"} />
            </div>
        </>
    )
}