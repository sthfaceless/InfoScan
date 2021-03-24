const coinsFunctions = {
    BALANCE: (state, action) => {
        return action.code === 0 ? {...state, balance: action.state.balance} :
            {...state, balance: undefined};
    },
    PAYMENTS: (state, action) => {
        if(action.code === 0) {

            const payments = state.payments.items;

            let size = Object.keys(action.state.payments).length, more = false;
            if(size > 5){
                size = 5;
                more = true;
            }

            for (let i = 0; i < size; i++) {
                payments.push(action.state.payments[i]);
            }
            return {...state, payments: {items: payments, more: more}}
        }else
           return  state;
    },
    REQUEST: (state, action) => {
        return action.code === 0 ? {...state, request: action.state} :
            {...state, request: {status: false}}
    }
};

export const coins = (state = {}, action) =>
    coinsFunctions[action.type] ?
        coinsFunctions[action.type](state,  action.state)
        : state;