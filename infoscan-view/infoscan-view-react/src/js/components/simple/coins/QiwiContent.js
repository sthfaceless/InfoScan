import React from 'react';
import 'css/coins/qiwi-content.css';
import QiwiContentItem from "js/components/simple/coins/QiwiContentItem";

class QiwiContent extends React.Component{
    render() {
        return (
            <div className="qiwi-content">
                <QiwiContentItem step='1' name='Введите номер телефона' class='phone-number' placeholder='79198883535'/>
                <QiwiContentItem step='2' name='Введите сумму для пополнения' class='amount' placeholder='0,00 руб'/>
            </div>
        );
    }
}

export default QiwiContent;