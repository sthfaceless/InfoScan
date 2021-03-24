import {SERVER_PATH} from "../Config";

export const QUESTION = 'QUESTION';

export function createQuestion(question) {

    return  fetch(SERVER_PATH + '/api/support', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(question)
    }).then(response => {
        if(response.ok)
            return {
                type: QUESTION,
                code: 0,
                state: {}
            };
        else throw new Error('Failed to create question')
    }).catch(error => ({
            type: QUESTION,
            code: 1,
            state: {}
        }))

}