const ordersFunctions = {
    ORDERS: (state, action) => {
        if(action.code === 0){
            const orders = action.state;
            const items = state.items;

            let more = false, size = Object.keys(orders).length;
            if(size % 5 !== 0){
                more = true;
                size--;
            }
            for (let i = 0; i < size; i++) {
                const item = orders[i];
                if(!items[item.id])
                    items[item.id] = item;
            }

            return {...state, items: items, more: more};
        }else return state;
    },
    ORDERS_STATISTICS: (state, action) => {

    },
    ORDER: (state, action) => {
        if(action.code === 0){
            const items = state.items;
            items[action.state.id] = action.state;
            return {...state, items: items};
        }else return state;
    }
};

export const orders = (state = {}, action) =>
    ordersFunctions[action.type] ?
        ordersFunctions[action.type](state,  action.state)
        : state;