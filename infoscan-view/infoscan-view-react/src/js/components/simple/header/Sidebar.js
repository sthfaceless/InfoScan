import React from 'react';
import {Sidenav} from "materialize-css";
import 'css/header/sidebar.css';

class Sidebar extends React.Component{
    componentDidMount(){
        Sidenav.init(document.getElementById('slide-out'), {});
    }
    render() {
        return (
            <ul id="slide-out" className="sidenav grey darken-3 white-text">
                <li>
                    <div className="user-view">
                        <h4>InfoScan</h4>
                    </div>
                </li>
                {this.props.children}
            </ul>
        );
    }
}

export default Sidebar;