import React, {Component} from 'react';
import {Route, Switch} from "react-router-dom";
import PersonInformation from "./PersonInformation";
import MakeOrder from "./MakeOrder";
import Orders from "./Orders";

class OrdersRoute extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Switch>
                    <Route exact path="/orders/make" component={MakeOrder}/>
                    <Route exact path="/orders/:id" component={PersonInformation}/>
                    <Route exact path="/orders" component={Orders}/>
                </Switch>
            </div>
        );
    }

}

export default OrdersRoute;