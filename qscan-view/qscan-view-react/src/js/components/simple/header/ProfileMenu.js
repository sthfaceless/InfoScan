import React, {Component} from 'react';
import 'css/header/profileMenu.css';
import DropdownProfile from "./DropdownProfile";

class ProfileMenu extends Component{

    constructor(props) {
        super(props);
        this.state = {
            dropdown: false,
            closeTimeout: setTimeout(()=>{}, 100)
        }
    }
    openDropdown(){
        clearTimeout(this.state.closeTimeout);
        this.setState({dropdown : true});
    }
    closeDropdown(){
        this.setState({
            closeTimeout: setTimeout(()=>this.setState({dropdown: false}), 500)
        }) ;
    }
    render() {
        return (
            <li className="profile-menu"
                onMouseEnter={() => this.openDropdown()}
                onMouseLeave={() => this.closeDropdown()}>
                <span>
                    {this.props.username}
                </span>
                {this.state.dropdown ? <DropdownProfile logout={this.props.logout}/> : ""}
            </li>
        );
    }

}

export default ProfileMenu;