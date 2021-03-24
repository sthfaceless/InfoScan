import React, {Component} from 'react';
import 'css/auth/login.css';
import {Link, Redirect} from "react-router-dom";
import FormField from "js/components/simple/forms/FormField";
import AuthForm from "js/components/simple/forms/AuthForm";
import {connect} from "react-redux";
import {openModal} from "js/store/actions/modalActions";
import {authWithCredentials} from "../../../store/actions/authActions";

class Login extends Component{
    constructor(props) {
        super(props);
        this.state = {
            redirect: false
        }
    }

    onSubmit = (e) => {
        this.setState({redirect: true});
        this.props.login(this.state.username, this.state.pass);
    };

    usernameUpdate = (event) => {
        this.setState({username: event.target.value})
    };
    passwordUpdate = (event) => {
        this.setState({pass: event.target.value})
    };

    render() {
        if(this.state.redirect)
            return <Redirect to='/profile'/>;

        return (
            <div className="login-panel grey lighten-3">
                <div className="container">
                    <div className="row">
                        <AuthForm title="Вход">
                            <FormField update={this.usernameUpdate} label="Логин"  placeholder="Введите логин" name="login" type="text"/>
                            <FormField update={this.passwordUpdate} label="Пароль" placeholder="Введите пароль" name="password" type="password"/>
                            <div className="submit-wrapper">
                                <button onClick={this.onSubmit} className="submit btn blue">Войти</button>
                                <input type="checkbox" value="Запомнить"/>
                            </div>
                            <Link className="forgot-password blue-text text-lighten-3" to='/restore'>Забыли пароль?</Link>
                        </AuthForm>
                    </div>
                </div>
            </div>
        );
    }

}

const mapDispatchToProps = dispatch => {
    return {
        login: (username, pass) => dispatch(authWithCredentials(username, pass)),
        openModal: (content, options) => dispatch(openModal(content, options))
    }
};

export default connect(null, mapDispatchToProps) (Login);