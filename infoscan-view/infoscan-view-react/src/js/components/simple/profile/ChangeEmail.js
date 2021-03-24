import React, {Component} from 'react';
import 'css/profile/profile.change-email.css';

class ChangeEmail extends Component{

    render() {
        return (
            <div className="change-email grey-text">
                <h4>Смена емайл</h4>
                <input type="email" placeholder="Введите email" />
                <div className="submit-wrapper">
                    <div className="submit btn">
                        Изменить емайл
                    </div>
                </div>
            </div>
        );
    }
}

export default ChangeEmail;