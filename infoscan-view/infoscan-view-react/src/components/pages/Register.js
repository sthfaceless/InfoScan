import React, {Component} from 'react';
import '../../css/register.css';
import {Redirect} from "react-router-dom";
import M from 'materialize-css';

class Register extends Component{
    constructor(props) {
        super(props);

        this.state = {
            redirect: false
        };

        M.updateTextFields();
    }

    submitForm = () => {
        this.setState({redirect: true});
    };

    render() {
        if(this.state.redirect)
            return <Redirect to='/profile'/>;

        return (
            <div className="register grey lighten-3">
                <div className="container">
                    <div className="row">
                        <form className="col offset-l3 offset-m2 s12 m8 l6 white grey-text">
                            <div className="header-form">
                                <h2>Регистрация</h2>
                            </div>
                            <div className="field">
                                <span className="input-label">Логин</span>
                                <input placeholder="Введите логин" name="login" type="text"/>
                            </div>
                            <div className="field">
                                <span className="input-label">Е-майл</span>
                                <input placeholder="Введите емайл" name="email" type="email"/>
                            </div>
                            <div className="field">
                                <span className="input-label">Пароль</span>
                                <input placeholder="Введите пароль" name="password" type="password"/>
                            </div>
                            <div className="field">
                                <span className="input-label">Подтверждение пароля</span>
                                <input placeholder="Повторите пароль" name="confirm-password" type="password"/>
                            </div>
                            <div className="submit-wrapper">
                                <button onClick={this.submitForm} className="submit btn green">Зарегистрироваться</button>
                                <input type="checkbox" value="Запомнить"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Register;