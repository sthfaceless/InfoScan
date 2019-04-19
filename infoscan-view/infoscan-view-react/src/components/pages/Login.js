import React, {Component} from 'react';
import './../../css/login.css';
import {Link, Redirect} from "react-router-dom";

class Login extends Component{
    constructor(props) {
        super(props);
        this.state = {
            redirect: false
        }
    }

    onSubmit = () => {
        this.setState({rendering: true});
    };

    render() {
        if(this.state.redirect)
            return <Redirect to='/profile'/>;

        return (
            <div className="login-panel grey lighten-3">
                <div className="container">
                    <div className="row">
                        <form className="col s12 m8 l6 offset-m2 offset-l3 white grey-text">
                            <div className="title">
                                Вход
                            </div>
                            <div className="field">
                                <span className="input-label">Логин</span>
                                <input placeholder="Введите логин" name="login" type="text"/>
                            </div>
                            <div className="field">
                                <span className="input-label">Пароль</span>
                                <input placeholder="Введите пароль" name="password" type="password"/>
                            </div>
                            <div className="submit-wrapper">
                                <button onClick={this.onSubmit} className="submit btn blue" type="submit">Войти</button>
                                <input type="checkbox" value="Запомнить"/>
                            </div>
                            <Link className="forgot-password blue-text text-lighten-3" to='/restore'>Забыли пароль?</Link>
                        </form>
                    </div>
                </div>
            </div>
        );
    }

}

export default Login;