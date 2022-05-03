import {Route} from "react-router-dom";
import {Routes} from "react-router";
import Layout from "./Layout";
import StripeMainPage from "../stripe/StripeMainPage";
import Github from "./Github";
import Home from "./Home";

function App() {

    return (
        <div>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Home/>}/>
                    <Route path="stripe" element={<StripeMainPage/>}/>
                    <Route path="github" element={<Github/>}/>

                </Route>
            </Routes>
        </div>
    );
}

export default App;