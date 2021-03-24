import React from 'react';
import 'css/orders/order-item.css';
import {Link} from "react-router-dom";

class OrderItem extends React.Component{
    getColorByStatus = (status) => {
        switch (status) {
            case 'Принят':
                return 'orange';
            case 'В обработке':
                return 'blue';
            case 'Отклонён':
                return 'red';
            case 'Завершён':
                return 'green';
            default:
                return '';
        }
    };
    render() {
        const {order} = this.props;
        return (
            <Link to={'/orders/'+order.id} className="order grey-text">
                <div className="left grey-text">
                    <div className="date">{order.date}</div>
                </div>
                <div className="center grey-text text-darken-2">
                    <span className="name">{order.username}</span>
                </div>
                <div className="right">
                    <span className={'status white-text '+this.getColorByStatus(order.orderStatus)}>{order.orderStatus}</span>
                </div>
            </Link>
        );
    }
}

export default OrderItem;