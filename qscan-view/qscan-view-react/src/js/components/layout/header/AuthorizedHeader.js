import React, {Component} from 'react';
import {Link} from "react-router-dom";
import Navbar from "js/components/simple/header/Navbar";
import ProfileMenu from "js/components/simple/header/ProfileMenu";
import {connect} from "react-redux";
import {logout} from "js/store/actions/authActions";
import Sidebar from "js/components/simple/header/Sidebar";

class AuthorizedHeader extends Component{
    render() {
        return (
            <>
                <Navbar>
                    <li><Link className="support-header" to="/support">Помощь</Link></li>
                    <li><Link className="orders-header" to="/orders">Заказы</Link></li>
                    <li><Link className="coins-header" to="/coins">Купить</Link></li>
                    <li className="links-delimiter">|</li>
                    <ProfileMenu username={this.props.username} logout={this.props.logout}/>
                </Navbar>
                <Sidebar>
                    <li><Link to='/support'>Помощь</Link></li>
                    <li><Link to='/orders'>Заказы</Link></li>
                    <li><Link to='/coins'>Купить</Link></li>
                    <li><div className="divider"></div></li>
                    <li><Link to='/profile' className="profile-link">{this.props.username}</Link></li>
                    <li>
                        <a onClick={() => this.props.logout()} href='#' className="logout">Выход</a>
                    </li>
                </Sidebar>
            </>
        );
    }
}
const mapDispatchToProps = dispatch => ({
    logout: () => dispatch(logout())
});
export default connect(null, mapDispatchToProps) (AuthorizedHeader);