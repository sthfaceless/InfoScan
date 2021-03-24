import React from 'react';
import {Link} from "react-router-dom";
import 'css/coins/pay-card.css';

class PayCard extends React.Component{
    render() {
        return (
            <div className="pay-card grey darken-3 white-text">
                <div className="amount">
                    <span>{this.props.balance}</span> коина
                </div>
                <Link to='/coins/pay' className="buy white grey-text text-darken-3">
                    Пополнить баланс
                </Link>
            </div>
        );
    }
}

export default PayCard;