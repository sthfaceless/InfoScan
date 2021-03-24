import React, {Component} from 'react';
import 'css/auth/restore.css';
import {Redirect} from "react-router-dom";
import FormField from "js/components/simple/forms/FormField";
import AuthForm from "js/components/simple/forms/AuthForm";

class Restore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            redirect: false
        }
    }

    onSubmit = () => {
        this.setState({redirect: true})
    };

    render() {
        if(this.state.redirect)
            return <Redirect to='/login'/>;

        return (
            <div className="restore-panel grey lighten-3">
                <div className="container">
                    <div className="row">
                        <AuthForm title="Восстановление пароля" subtitle="На ваш адрес будет отправлено письмо для восстановления пароля">
                            <FormField label="Email" placeholder="Введите ваш email" name="email" type="email"/>
                            <button onSubmit={this.onSubmit} className="btn orange submit">Отправить письмо</button>
                        </AuthForm>
                    </div>
                </div>
            </div>
        );
    }
}

export default Restore;