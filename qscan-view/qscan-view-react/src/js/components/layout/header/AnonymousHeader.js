import React, {Component} from 'react';
import {Link} from "react-router-dom";
import Navbar from "js/components/simple/header/Navbar";
import {Sidenav} from "materialize-css";
import Sidebar from "js/components/simple/header/Sidebar";

class AnonymousHeader extends Component {

    componentDidMount(){
        Sidenav.init(document.getElementById('slide-out'), {});
    }
    render() {
        return (
            <>
                <Navbar>
                    <li><Link className="support-header" to="/support">Помощь</Link></li>
                    <li className="links-delimiter">|</li>
                    <li><Link className="login btn-small red accent-4" to="/login">Вход</Link></li>
                    <li><Link className="reg btn-small red accent-4" to="/reg">Регистрация</Link></li>
                </Navbar>
                <Sidebar>
                    <li><Link to='/login'>Вход</Link></li>
                    <li><Link to='/reg'>Регистрация</Link></li>
                    <li><div className="divider"></div></li>
                    <li><Link to='/support'>Помощь</Link></li>
                </Sidebar>
            </>
        );
    }

}

export default AnonymousHeader;