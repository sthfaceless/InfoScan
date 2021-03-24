import React, {Component} from 'react';
import {Route, Switch} from "react-router-dom";
import PersonInformation from "./PersonInformation";
import CreateOrder from "./CreateOrder";
import Orders from "./Orders";

class OrdersRouter extends Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Switch>
                    <Route exact path="/orders/make" component={CreateOrder}/>
                    <Route exact path="/orders/:id" component={PersonInformation}/>
                    <Route exact path="/orders" component={Orders}/>
                </Switch>
            </div>
        );
    }

}

export default OrdersRouter;