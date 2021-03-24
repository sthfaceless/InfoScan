import React, {Component} from 'react';
import 'css/coins/coins.css';
import SearchBar from "js/components/core/SearchBar";
import PayCard from "js/components/simple/coins/PayCard";
import More from "js/components/core/More";
import PaymentItem from "js/components/simple/coins/PaymentItem";
import {connect} from "react-redux";
import {loadBalance, loadPayments} from "../../../store/actions/coinsActions";

class Coins extends Component{

    componentDidMount() {
       const {dispatch} = this.props;
       dispatch(loadBalance());
       dispatch(loadPayments(this.props.payments));
    }

    loadMore = () => {
        this.props.dispatch(loadPayments(this.props.payments));
    };

    render() {
        return (
            <div className="coins">
                <div className="container">
                    <div className="row">
                        <div className="col s12 l8 operations-history">
                            <div className="header grey-text text-darken-1">
                                История операций
                            </div>
                            <SearchBar searchFilters={this.props.searchFilters}/>
                            <div className="content">
                                {this.props.payments.map(payment => (
                                    <PaymentItem key={payment.id} payment={payment}/>
                                ))}
                                {this.props.payments.more ? <More action={this.loadMore}/> : ''}
                            </div>
                        </div>
                        <div className="col s12 l4 actions">
                            <PayCard balance={this.props.balance ? this.props.balance : 'Неизвестно'}/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    searchFilters: state.coins.searchFilters,
    payments: state.coins.payments,
    balance: state.coins.balance
});

export default connect(mapStateToProps)(Coins);