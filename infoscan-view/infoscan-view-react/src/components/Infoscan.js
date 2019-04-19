import React, {Component} from 'react';
import Header from './layout/Header';
import Body from './layout/Body';
import Footer from './layout/Footer';
import {BrowserRouter} from "react-router-dom";

class Infoscan extends Component {

    render() {
        return (
            <BrowserRouter>
                <Header/>
                <Body/>
                <Footer/>
            </BrowserRouter>
        );
    }

}

export default Infoscan;