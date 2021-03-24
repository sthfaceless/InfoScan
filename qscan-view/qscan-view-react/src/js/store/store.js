import {createStore} from "redux";
import reducer from './reducers/reducer';
import {getInitialState} from "./initialState";

const initialState = getInitialState();
const store = createStore(reducer, initialState);

export default store;