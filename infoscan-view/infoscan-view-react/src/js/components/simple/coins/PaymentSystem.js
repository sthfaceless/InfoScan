import React from 'react';

class PaymentSystem extends React.Component{
    render() {
        const {system} = this.props;
        return (
            <div onClick={() => this.props.activate(system.name, system.component)}
                 className={'payment-system ' + (system.name === this.props.getActive() ? 'active' : '')}>
                <div className="system-img">
                    <img src={'/resources/images/'+system.name+'.png'}/>
                </div>
            </div>
        );
    }
}

export default PaymentSystem;