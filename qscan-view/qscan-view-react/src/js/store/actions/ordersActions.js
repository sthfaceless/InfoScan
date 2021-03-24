import {SERVER_PATH} from "../Config";

export const ORDERS = 'ORDERS';
export const ORDER = 'ORDER';
export const ORDERS_STATISTICS = 'ORDERS_STATISTICS';
export const ORDER_CREATE = 'ORDER_CREATE';

export function loadOrders(orders) {

    if(!orders.more && Object.keys(orders.items).length !== 0)
        return {
            type: ORDERS,
            code: 1,
            state: {}
        };

    return fetch(SERVER_PATH + '/api/orders?start='+Object.keys(orders.items).length, {
        method: 'GET',
        credentials: "include"
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Failed to load orders')
    })
        .then(json => ({
            type: ORDERS,
            code: 0,
            state: json
        }))
        .catch(error => ({
            type: ORDERS,
            code: 1,
            state: {}
        }))

}

export function loadOrdersStatistics() {
    
}

export function loadOrder(orders, id) {

    if(orders.items[id].info)
        return {
            type: ORDER,
            code: 1,
            state: {}
        };

    return fetch(SERVER_PATH + '/api/orders?id='+id, {
        method: 'GET',
        credentials: "include"
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Failed to get orders')
    }).then(json => ({
            type: ORDER,
            code: 0,
            state: json
        }))
        .catch(error => ({
            type: ORDER,
            code: 1,
            state: {}
        }))
}

export function createOrder(order) {

    return fetch(SERVER_PATH + '/api/orders', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(order)
    }).then(response => {
        if(response.ok)
            return {
                type: ORDER_CREATE,
                code: 0,
                state: {}
            };
        else throw new Error('Failed to create order')
    }).catch(error => ( {
            type: ORDER_CREATE,
            code: 1,
            state: {}
        }))

}