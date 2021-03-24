import React, {Component} from 'react';
import AuthorizedHeader from "./AuthorizedHeader";
import AnonymousHeader from "./AnonymousHeader";
import {connect} from "react-redux";
import 'css/header/header.css';
import {logout} from "js/store/actions/authActions";

class HeaderContainer extends Component{

    render() {
        return this.props.auth ?
            <AuthorizedHeader username={this.props.username} logout={this.props.logout}/>
            : <AnonymousHeader/>;
    }
}

const mapStateToProps = state => ({
    auth: state.auth.isAuth,
    username: state.auth.username
});

const mapDispatchToProps = dispatch => ({
    logout: () => dispatch(logout())
});

export default connect(mapStateToProps, mapDispatchToProps) (HeaderContainer);