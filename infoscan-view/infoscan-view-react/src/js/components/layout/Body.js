import React, {Component} from 'react';
import Main from "js/components/containers/Main";
import {Route, Switch} from "react-router-dom";
import NotFound from "js/components/errors/NotFound";
import OrdersContainer from "js/components/containers/orders/OrdersRouter";
import CoinsContainer from "js/components/containers/coins/CoinsRouter";
import Login from "js/components/containers/auth/Login";
import Register from "js/components/containers/auth/Register";
import License from "js/components/containers/License";
import Support from "js/components/containers/Support";
import Restore from "js/components/containers/auth/Restore";
import Profile from "js/components/containers/Profile";

class Body extends Component {

    render() {
        return (
            <Switch>
                <Route exact path='/' component={Main}/>
                <Route exact path='/login' component={Login}/>
                <Route exact path='/restore' component={Restore}/>
                <Route exact path='/reg' component={Register}/>
                <Route path='/coins' component={CoinsContainer}/>
                <Route path='/orders' component={OrdersContainer}/>
                <Route exact path='/profile' component={Profile}/>
                <Route exact path='/support' component={Support}/>
                <Route exact path='/license' component={License}/>
                <Route component={NotFound}/>
            </Switch>
        );
    }

}

export default Body;