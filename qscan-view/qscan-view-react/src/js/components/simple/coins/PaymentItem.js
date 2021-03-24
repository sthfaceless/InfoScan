import React from 'react';
import 'css/coins/payment-item.css';

class PaymentItem extends React.Component{
    render() {
        const {payment} = this.props;
        return (
            <div className="payment-item">
                <div className="left">
                    <img src={'/resources/images/'+payment.system+'.png'}/>
                </div>
                <div className="right">
                    <div className="info grey-text">
                        <div className="id">{payment.id}</div>
                        <div className="name grey-text text-darken-2">Пополнение с {payment.systemName}</div>
                        <div className="date">{payment.date}</div>
                    </div>
                    <div className="amount green-text">{payment.amount}</div>
                </div>
            </div>
        );
    }
}

export default PaymentItem;