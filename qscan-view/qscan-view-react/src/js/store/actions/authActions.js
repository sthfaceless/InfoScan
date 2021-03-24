import {SERVER_PATH} from "js/store/Config";
import {setCookie} from "../../services/cookie-service";

export const LOGOUT = 'LOGOUT';
export const AUTH = 'AUTH';
export const REGISTER = 'REGISTER';

export function logout() {

    fetch(SERVER_PATH + '/api/auth/logout', {
        method: 'POST',
        credentials: 'include',
    });
    setCookie('auth_token', '', {expires: new Date()});

    return {
        type: LOGOUT
    }
}

export function authWithCredentials(username, pass) {
    return fetch(SERVER_PATH + '/api/auth', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json; charset=utf-8;'
        },
        body: JSON.stringify({
            username: username,
            password: pass
        })
    }).then(response =>{
        if(response.ok)
            return response.json();
        else throw new Error('Bad authentication')
    }).then(json => ({
            type: AUTH,
            code: 0,
            state: {
                isAuth: true,
                username: json.username,
                token: json.token
            }
        }))
        .catch(error => ({
            type: AUTH,
            code: 1,
            state: {}
        }));
}

export function authWithToken(token) {

    if(!token || token === '')
        return {
            type: AUTH,
            code: 1,
            state:{}
        };

    return fetch(SERVER_PATH + '/api/auth/token', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify({token: token})
    }).then(response => {
        if(response.ok)
            return response.json();
        else throw new Error('Bad authentication')
    })
        .then(json => ({
            type: AUTH,
            code: 0,
            state:{
                isAuth: true,
                username: json.username,
                token: json.token
            }
        }))
        .catch(error => ({
            type: AUTH,
            code: 1,
            state: {}
        }));
}

export function register(username, email, password) {

    return fetch(SERVER_PATH + '/api/user/reg', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json; charset=utf-8;'
        },
        body: JSON.stringify({
            username: username,
            email: email,
            password: password
        })
    }).then(response => {
        if(response.ok)
            return {
                type: REGISTER,
                code: 0
            };
        else throw new Error('bad registration')
    }).catch(error => ({
            type: REGISTER,
            code: 1
        }))

}