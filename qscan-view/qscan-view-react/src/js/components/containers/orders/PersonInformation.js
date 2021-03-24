import React, {Component} from 'react';
import BadRequest from "../../errors/BadRequest";
import 'css/orders/person-information.css';
import SocialNetworks from "js/components/core/SocialNetworks";
import InfoBlock from "js/components/simple/orders/InfoBlock";
import {connect} from "react-redux";
import {loadOrder} from "../../../store/actions/ordersActions";

class PersonInformation extends Component{

    constructor(props) {
        super(props);
        this.state = {
            id: props.match.params.id,
            item: undefined
        };
    }

    componentDidMount() {
        if(/^\d+$/.test(this.state.id)) {
            const action = loadOrder(this.props.orders, this.state.id);
            this.props.dispatch(action);
            if(action.code === 0)
                this.setState({item: this.props.orders[this.state.id]})
        }
    }

    render() {
        if(!/^\d+$/.test(this.state.id) || !this.state.item)
            return <BadRequest/>;
        return (
            <div className="person-information">
                <div className="container">
                    <div className="row grey-text text-darken-1">
                        <div className="col s12">
                            <h4 className="header">Заказ <span className="blue white-text">{this.state.item.id}</span></h4>
                        </div>
                        <div className="col s12 m4 left-column">
                            <div className="user-img">
                                <img src="/resources/images/user.png" alt=""/>
                            </div>
                            <SocialNetworks networks={this.state.item.socialNetworks}/>
                        </div>
                        <div className="col s12 m8 right-column">
                            {this.state.item.info.map(item => (
                                <InfoBlock key={item.name} name={item.name} items={item.items}/>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

const mapStateToProps = state => ({
   orders: state.orders
});

export default connect(mapStateToProps)(PersonInformation);