import React, {Component} from 'react';
import 'css/main.css';
import {Link} from "react-router-dom";

class Main extends Component{

    render() {
        return (
            <div className="main">
                <div className="container">
                    <div className="row main-elements">
                        <div className="title-wrapper">
                            <img src="/resources/images/logo.png" alt="logo"/>
                            <h1 className="title white-text">InfoScan</h1>
                        </div>
                        <h6 className="subtitle white-text">Найдите кого угодно</h6>
                        <Link className="start red accent-4 white-text" to='/reg'>Попробовать</Link>
                    </div>
                </div>
            </div>
        );
    }

}

export default Main;