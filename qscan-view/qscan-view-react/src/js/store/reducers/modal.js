const modalFunctions = {
    MODAL: (state, action) => {
        return action.state.open ?
            {...state, isOpen: true, content: action.state.content, options: action.state.options} :
            {...state, isOpen: false}
    }
};

export const modal = (state={}, action) =>
    modalFunctions[action.type] ? modalFunctions[action.type](state, action) : state;