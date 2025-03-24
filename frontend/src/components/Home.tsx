import Header from "./Header.tsx";

export default function Home() {

    return (
        <>
            <Header />

            <div className={"logo-container"}><img src={"./public/super-fische-logo-transparent.webp"} className={"logo"} alt={"Super-Fische - Logo"}/></div>
            <div className={"container-home-image"}>
                <img src={"./public/Superfische-photo.png"} alt={"Super-Fische - Home Titel - Bild"}
                style={{ width: "180vh", height: "100vh" }}/>

            </div>
        </>
    )
}