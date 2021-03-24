import React from 'react';
import {Link} from "react-router-dom";
import 'css/orders/orders-top.css';

class OrdersTop extends React.Component{
    render() {
        return (
            <div className="row top-row">
                <div className="col s12 header">Ваши заказы</div>
                <Link to="/orders/make" className="make-order grey darken-3 white-text">Новый заказ</Link>
                <div className="col s12 subheader">
                    <div className="subheader-element"><span className="green-text">{this.props.stat.finished}</span> готовы</div>
                    <div className="subheader-element"><span className="orange-text">{this.props.stat.processing}</span> в обработке</div>
                    <div className="subheader-element"><span className="red-text">{this.props.stat.cancelled}</span> отменены</div>
                </div>
            </div>
        );
    }
}

export default OrdersTop;