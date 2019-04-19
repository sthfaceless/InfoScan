import React, {Component} from 'react';
import '../../css/header.css';
import {Link} from "react-router-dom";

class Header extends Component {

    render() {
        return (
            <nav className="grey darken-3">
                <div className="nav-wrapper container">
                    <Link to="/" className="brand-logo">Info<span className="red-text text-darken-2">Scan</span></Link>
                    <ul id="nav-mobile" className="right header-links hide-on-med-and-down">
                        <li><Link className="support" to="/support">Помощь</Link></li>
                        <li><Link className="license" to="/license">Лицензия</Link></li>
                        <li className="links-delimiter">|</li>
                        <li><Link className="login btn-small red accent-4" to="/login">Вход</Link></li>
                        <li><Link className="reg btn-small red accent-4" to="/reg">Регистрация</Link></li>
                    </ul>
                </div>
            </nav>
        );
    }

}

export default Header;