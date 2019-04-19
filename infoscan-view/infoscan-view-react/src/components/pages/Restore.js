import React, {Component} from 'react';
import '../../css/restore.css';
import {Redirect} from "react-router-dom";

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
                        <form className="col s12 m8 l6 offset-l3 offset-m2 white grey-text">
                            <div className="title">
                                Восстановление пароля
                            </div>
                            <div className="subtitle">
                                На ваш адрес будет отправлено письмо для восстановления пароля
                            </div>
                            <div className="field">
                                <input placeholder="Введите ваш email" name="email" type="email"/>
                            </div>
                            <button onSubmit={this.onSubmit} className="btn orange submit">Отправить письмо</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Restore;