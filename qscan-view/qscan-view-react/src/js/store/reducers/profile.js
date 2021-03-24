const profileFunctions = {
};

export const profile = (state = {}, action) =>
    profileFunctions[action.type] ?
        profileFunctions[action.type](state,  action.state)
        : state;