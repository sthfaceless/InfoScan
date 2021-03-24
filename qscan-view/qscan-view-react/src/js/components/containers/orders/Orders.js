import React, {Component} from "react";
import 'css/orders/orders.css';
import OrdersTop from "js/components/simple/orders/OrdersTop";
import SearchBar from "js/components/core/SearchBar";
import More from "js/components/core/More";
import OrderItem from "js/components/simple/orders/OrderItem";
import {connect} from "react-redux";
import {loadOrders, loadOrdersStatistics} from "../../../store/actions/ordersActions";

class Orders extends Component{
    constructor(props) {
        super(props);
        this.state = {
            filters: {
                date: {
                    dropdown: false
                },
                status: {
                    dropdown: false
                }
            }
        }
    }

    componentDidMount() {
        const {dispatch} = this.props;
        dispatch(loadOrders(this.props.orders));
        dispatch(loadOrdersStatistics());
    }

    loadMore = () => {
        this.props.dispatch(loadOrders(this.props.orders));
    };

    render() {
        return (
            <div className="orders">
                <div className="container">
                    <OrdersTop stat={this.props.orders.ordersStat}/>
                    <div className="row">
                        <SearchBar searchFilters={this.props.orders.searchFilters}/>
                    </div>
                    <div className="row">
                        <div className="col s12 content">
                            {Object.values(this.props.orders.items).map(item => (
                                <OrderItem key={item.id} order={item}/>
                            ))}
                        </div>
                    </div>
                    <div className="row">
                        {this.props.orders.more ? <More action={this.loadMore}/> : ''}
                    </div>
                </div>
            </div>
        );
    }

}

const mapStateToProps = state => ({
   orders: state.orders
});

export default connect(mapStateToProps)(Orders);