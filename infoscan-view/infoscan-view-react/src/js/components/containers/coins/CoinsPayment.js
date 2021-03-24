import React, {Component} from 'react';
import 'css/coins/coinsPayment.css';
import QiwiContent from "js/components/simple/coins/QiwiContent";
import PaymentSystems from "js/components/simple/coins/PaymentSystems";
import {connect} from "react-redux";
import {requestPayment} from "../../../store/actions/coinsActions";

class CoinsPayment extends Component{
    constructor(props) {
        super(props);
        this.state = {
            content: <QiwiContent updateBilling={this.updateBilling}/>,
        }
    }
    setActive = (component) => {
        this.setState({content: component});
    };
    updateBilling = (billing) => {
        this.setState({billing: billing});
    };
    requestPayment = () => {
        if(!this.state.billing){

        }else{
            const {dispatch} = this.props;
            dispatch(requestPayment(this.state.billing))
        }
    };
    render() {
        return (
            <div className="coins-payment">
                <div className="container">
                    <div className="row">
                        <div className="col s12">
                            <h4 className="header grey-text text-darken-1">Пополнение счёта</h4>
                        </div>
                        <PaymentSystems setActive={this.setActive}/>
                        <div className="col s12 content">
                            <div className="head grey-text">Оплата</div>
                            {this.state.content}
                            <div className="pay-wrapper">
                                <div onClick={this.requestPayment} className="pay-btn grey darken-3 white-text">Оплатить</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    request: state.coins.request
});

export default connect(mapStateToProps)(CoinsPayment);