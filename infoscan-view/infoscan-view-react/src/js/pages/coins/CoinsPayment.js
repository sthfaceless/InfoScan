import React, {Component} from 'react';
import '../../../css/coinsPayment.css';

class CoinsPayment extends Component{
    render() {
        return (
            <div className="coins-payment">
                <div className="container">
                    <div className="row">
                        <div className="col s12">
                            <h4 className="header grey-text text-darken-1">Пополнение счёта</h4>
                        </div>
                        <div className="col s12 payment-systems">
                            <div className="payment-system">
                                <div className="system-img">
                                    <img src="/resources/images/qiwi.svg"/>
                                </div>
                            </div>
                        </div>
                        <div className="col s12 content">

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CoinsPayment;