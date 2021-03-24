import {SERVER_PATH} from "../Config";

export const PAYMENTS = 'PAYMENTS';
export const REQUEST = 'REQUEST';
export const BALANCE = 'BALANCE';

export function loadBalance() {

    return fetch(SERVER_PATH + '/api/coins', {
        method: 'GET',
        credentials: 'include'
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Failed to get balance')
    }).then(json => ({
            type: BALANCE,
            code: 0,
            state: {
                balance: json.amount
            }
        }))
        .catch(error => ({
            type: BALANCE,
            code: 1,
            state: {}
        }))

}

export function loadPayments(payments) {

    if(!payments.more && payments.items.length !== 0)
        return {
            type: PAYMENTS,
            code: 1,
            state: {}
        };

    return fetch(SERVER_PATH + '/api/coins/history?start='+payments.items.length,{
        method: 'GET',
        credentials: 'include'
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Failed to get payments')
    })
        .then(json => ({
            type: PAYMENTS,
            code: 0,
            state: {
                payments: json
            }
        }))
        .catch(error => ({
            type: PAYMENTS,
            code: 1,
            state: {}
        }))

}

export function requestPayment(billing) {
    return fetch(SERVER_PATH + '/api/coins', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(billing)
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Failed to create request')
    })
        .then(json => ({
            type: REQUEST,
            code: 0,
            state: json
        }))
        .catch(error => ({
            type: REQUEST,
            code: 1,
            state: {}
        }))
}

