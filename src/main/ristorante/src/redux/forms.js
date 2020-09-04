export const InitialFeedback = {
    firstname: '',
    lastname: '',
    telnum: '',
    email: '',
    agree: false,
    contactType: 'Tel.',
    message: ''
}


/*import * as ActionTypes from './ActionTypes';

export const Feedback = (state = {
    firstname: '',
    lastname: '',
    telnum: '',
    email: '',
    agree: false,
    contactType: 'Tel.',
    message: ''
}, action) => {
    switch (action.type){
        case (ActionTypes.FEEDBACK_RESPONSE):
            let response = action.payload;
            return {state, response};
        default:
            return state;
    }
};*/