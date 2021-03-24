import React, {Component} from 'react';
import 'css/auth/register.css';
import {Redirect} from "react-router-dom";
import M from 'materialize-css';
import FormField from "js/components/simple/forms/FormField";
import AuthForm from "js/components/simple/forms/AuthForm";

class Register extends Component{
    constructor(props) {
        super(props);

        this.state = {
            redirect: false,
            username: undefined,
            email: undefined,
            password: undefined,
            cpassword: undefined
        };

        M.updateTextFields();
    }

    submitForm = () => {
        this.setState({redirect: true});
    };

    updateUsername = (event) => {
        this.setState({username: event.target.value})
    };
    updateEmail = (event) => {
        this.setState({email: event.target.value})
    };
    updatePassword = (event) => {
        this.setState({password: event.target.value})
    };
    updateCpassword = (event) => {
        this.setState({cpassword: event.target.value})
    };


    render() {
        if(this.state.redirect)
            return <Redirect to='/profile'/>;

        return (
            <div className="register grey lighten-3">
                <div className="container">
                    <div className="row">
                        <AuthForm title="Регистрация">
                            <FormField update={this.updateUsername} label="Логин" placeholder="Введите логин" name="login" type="text"/>
                            <FormField update={this.updateEmail} label="Е-майл" placeholder="Введите емайл" name="email" type="email"/>
                            <FormField update={this.updatePassword} label="Пароль" placeholder="Введите пароль" name="password" type="password"/>
                            <FormField update={this.updateCpassword} label="Подтверждение пароля" placeholder="Повторите пароль" name="confirm-password" type="password"/>
                            <div className="submit-wrapper">
                                <button onClick={this.submitForm} className="submit btn green">Зарегистрироваться</button>
                                <input type="checkbox" value="Запомнить"/>
                            </div>
                        </AuthForm>
                    </div>
                </div>
            </div>
        );
    }
}

export default Register;