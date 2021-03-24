import React, {Component} from 'react';
import 'css/orders/createOrder.css';
import DatePicker from "js/components/core/DatePicker";
import RadioGender from "js/components/simple/orders/RadioGender";
import SocialNetworks from "js/components/core/SocialNetworks";
import {createOrder} from "js/store/actions/ordersActions";

class CreateOrder extends Component{
    constructor(props) {
        super(props);
        this.state = {
            creating: false,
            order: {}
        }
    }

    createOrder = () => {
        if(this.state.creating)
            return;
        this.setState({creating: true});
        createOrder(this.state.order);
    };
    updateFirstName = (event) => {const order = this.state.order;order.firstName = event.target.value;this.setState({order: order})};
    updateLastName = (event) => {const order = this.state.order;order.lastName = event.target.value;this.setState({order: order})};
    updatePatronymic = (event) => {const order = this.state.order;order.patronymic = event.target.value;this.setState({order: order})};
    updateGender = (name) => {const order = this.state.order;order.gender = name;this.setState({order: order})};
    updateEmail = (event) => {const order = this.state.order;order.email = event.target.value;this.setState({order: order})};
    updatePseudoname = (event) => {const order = this.state.order;order.pseudoname = event.target.value;this.setState({order: order})};
    updatePhone = (event) => {const order = this.state.order;order.phone = event.target.value;this.setState({order: order})};
    updateIp = (event) => {const order = this.state.order;order.ip = event.target.value;this.setState({order: order})};
    updateDate = (date) => {const order = this.state.order;order.date = date;this.setState({order: order})};

    render() {
        return (
            <div className="create-order">
                <div className="container">
                    <div className="row">
                        <div className="col s12 grey-text text-darken-3">
                            <div className="header">
                                Создать заказ
                            </div>
                        </div>
                    </div>
                    <div className="row content">
                        <div className="col s12 m6 left">
                            <div className="left-header">Добавить фотографию</div>
                            <div className="user-img">
                                <img src="/resources/images/user.png" alt=""/>
                                <div style={{display: 'none'}} className="over"><img src="/resources/images/plus.svg"/></div>
                            </div>
                            <SocialNetworks networks={[]}/>
                        </div>
                        <div className="col m6 s12 right">
                            <input onChange={this.updateFirstName} className="content-field" placeholder="Имя" type="text"/>
                            <input onChange={this.updateLastName} className="content-field" placeholder="Фамилия" type="text"/>
                            <input onChange={this.updatePatronymic} className="content-field" placeholder="Отчество" type="text"/>
                            <RadioGender onUpdate={this.updateGender} class='gender' name='Пол' fieldName='gender' items={[
                                {name: 'Мужской', value: 'MAN'},
                                {name: 'Женский', value: 'WOMAN'}
                                ]}/>
                            <DatePicker onUpdate={this.updateDate} id='order-datepicker' placeholder='Дата рождения'/>
                            <input onChange={this.updatePseudoname} className="content-field" placeholder="Псевдоним" type="text"/>
                            <input onChange={this.updateEmail} className="content-field" placeholder="Почта" type="text"/>
                            <input onChange={this.updatePhone} className="content-field" placeholder="Телефон" type="text"/>
                            <input onChange={this.updateIp} className="content-field" placeholder="Ip" type="text"/>
                        </div>
                        <div className="col s12 create">
                            <div onClick={this.createOrder} className="create-btn grey darken-3 white-text">Сделать заказ</div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}


export default CreateOrder;