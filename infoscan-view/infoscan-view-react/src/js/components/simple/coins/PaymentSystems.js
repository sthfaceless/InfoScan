import React from 'react';
import QiwiContent from "js/components/simple/coins/QiwiContent";
import PaymentSystem from "js/components/simple/coins/PaymentSystem";
import 'css/coins/payment-systems.css';

class PaymentSystems extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            active: 'qiwipayment',
            systems: [{
                name: 'qiwipayment',
                component: <QiwiContent/>
            },{
                name: 'webmoneypayment',
                component: <div></div>
            },{
                name: 'visapayment',
                component: <div></div>
            }]
        }
    }
    active = (name, component) => {
        this.setState({active: name});
        this.props.setActive(component);
    };
    getActive = () => {
        return this.state.active;
    };
    render() {
        return (
            <div className="col s12 payment-systems">
                <div className="head grey-text">Платёжные системы</div>
                <div className="wrapper">
                    {this.state.systems.map(system => (
                        <PaymentSystem activate={this.active} getActive={this.getActive} key={system.name} system={system}/>
                    ))}
                </div>
            </div>
        );
    }
}

export default PaymentSystems;