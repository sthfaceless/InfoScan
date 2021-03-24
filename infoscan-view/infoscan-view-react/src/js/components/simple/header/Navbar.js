import React, {Component} from 'react';
import {Link} from "react-router-dom";
import 'css/header/navbar.css';

class Navbar extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <nav className="grey darken-3">
                <a href="#" data-target="slide-out" className="sidenav-trigger hide-on-large-only"><i
                    className="material-icons">|||</i></a>
                <div className="nav-wrapper container">
                    <Link to="/" className="brand-logo">Info<span className="red-text text-darken-2">Scan</span></Link>
                    <ul id="nav-mobile" className="right header-links hide-on-med-and-down">
                        {this.props.children}
                    </ul>
                </div>
            </nav>
        );
    }
}

export default Navbar;