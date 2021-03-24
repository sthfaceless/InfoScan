import React, {Component} from 'react';
import 'css/header/dropdown-profile.css';
import {Link} from "react-router-dom";

class DropdownProfile extends Component{
    render() {
        return (
            <div className="dropdown-profile grey-text">
                <Link className='dropdown-element' to='/profile'>Профиль</Link>
                <div onClick={()=>this.props.logout()} className='dropdown-element'>Выйти</div>
            </div>
        );
    }
}

export default DropdownProfile;