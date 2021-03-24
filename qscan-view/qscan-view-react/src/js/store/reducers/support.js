const supportFunctions = {
};

export const support = (state = {}, action) =>
    supportFunctions[action.type] ?
        supportFunctions[action.type](state,  action.state)
        : state;