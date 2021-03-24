import {combineReducers} from 'redux';
import {orders} from "./orders";
import {auth} from "./auth";
import {support} from "./support";
import {profile} from "./profile";
import {coins} from "./coins";
import {modal} from "./modal";

export default combineReducers({
    orders,
    auth,
    support,
    profile,
    coins,
    modal
});