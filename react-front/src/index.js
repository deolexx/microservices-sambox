import React from 'react';
import ReactDOM from 'react-dom';
import './styles/styles.css'
import './styles/SimpleWindow.css'
import App from "./navbar/App";
import {BrowserRouter,} from "react-router-dom";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </React.StrictMode>
    , document.getElementById('root'));