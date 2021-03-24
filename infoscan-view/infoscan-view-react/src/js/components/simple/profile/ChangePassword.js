import React, {Component} from 'react';
import 'css/profile/profile.change-password.css';

class ChangePassword extends Component{

    render() {
        return (
            <div className="change-password grey-text">
                <h4>Смена пароля</h4>
                <div className="inputs">
                    <input type="password" placeholder="Старый пароль"/>
                    <input type="password" placeholder="Новый пароль"/>
                    <input type="password" placeholder="Подтвердите новый пароль"/>
                </div>
                <div className="submit-wrapper">
                    <div className="submit btn">
                        Изменить пароль
                    </div>
                </div>
            </div>
        );
    }
}

export default ChangePassword;