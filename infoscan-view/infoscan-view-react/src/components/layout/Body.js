import React, {Component} from 'react';
import Main from "../pages/Main";
import {Route, Switch} from "react-router-dom";
import NotFound from "../errors/NotFound";
import OrdersRoute from "../pages/orders/OrdersRoute";
import Coins from "../pages/Coins";
import Login from "../pages/Login";
import Register from "../pages/Register";
import License from "../pages/License";
import Support from "../pages/Support";
import Restore from "../pages/Restore";
import Profile from "../pages/Profile";

class Body extends Component {

    render() {
        return (
            <>
                <Switch>
                    <Route exact path='/' component={Main}/>
                    <Route exact path='/login' component={Login}/>
                    <Route exact path='/reg' component={Register}/>
                    <Route exact path='/coins' component={Coins}/>
                    <Route path='/orders' component={OrdersRoute}/>
                    <Route exact path='/profile' component={Profile}/>
                    <Route exact path='/restore' component={Restore}/>
                    <Route exact path='/support' component={Support}/>
                    <Route exact path='/license' component={License}/>
                    <Route component={NotFound}/>
                </Switch>
            </>
        );
    }

}

export default Body;