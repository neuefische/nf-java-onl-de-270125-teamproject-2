import Header from "./Header.tsx";

export default function Home() {

    return (
        <>
            <Header />

            <div className={"logo-container"}><img src={"./public/super-fische-logo-transparent.webp"} className={"logo"} alt={"Super-Fische - Logo"}/></div>
            <div className={"container-home-image"}>
                <img src={"./public/home-pexels-rui-dias-469842-1472887.jpg"} alt={"Super-Fische - Home Titel - Bild"} />
            </div>
        </>
    )
}