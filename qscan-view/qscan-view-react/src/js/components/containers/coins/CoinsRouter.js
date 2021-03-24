import React, {Component} from 'react';
import {Route, Switch} from "react-router-dom";
import Coins from "./Coins";
import CoinsPayment from "./CoinsPayment";

class CoinsRouter extends Component{

    render() {
        return (
            <Switch>
                <Route exact path='/coins' component={Coins}/>
                <Route exact path='/coins/pay' component={CoinsPayment}/>
            </Switch>
        );
    }
}

export default CoinsRouter;