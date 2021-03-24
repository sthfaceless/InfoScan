import React, {Component} from 'react';
import HeaderContainer from "./components/layout/header/HeaderContainer";
import Body from './components/layout/Body';
import Footer from './components/layout/Footer';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import store from "./store/store";
import Modal from "./components/containers/Modal";

class Infoscan extends Component {
    render() {
        return (
            <Provider store={store}>
                <BrowserRouter>
                    <HeaderContainer/>                                                                                                                                                                          
                    <Body/>                                                                                                                                                                                                                                                                                                                                                                                                     
                    <Footer/>
                </BrowserRouter>                                                                                                                                                                                                                                                                                                                                                                                                                                                        
                <Modal/>
            </Provider>
        );
    }

}

export default Infoscan;