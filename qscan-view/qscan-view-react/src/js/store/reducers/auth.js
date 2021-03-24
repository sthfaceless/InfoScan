const authFunctions = {
    AUTH: (state, action) => {

       return action.code === 0 ?
           {...state, isAuth: true, username: action.state.username, token: action.state.token} :
           {...state, isAuth: false, username: undefined, token: undefined}
    },
    LOGOUT: (state, action) => {
        return {...state, isAuth: false, username: undefined, token: undefined}
    },
    REGISTER: (state, action) => {
        return state;
    }
};

export const auth = (state = {}, action) =>
    authFunctions[action.type] ?
        authFunctions[action.type](state, action) : state;
