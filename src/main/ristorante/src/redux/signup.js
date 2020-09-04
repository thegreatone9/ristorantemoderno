import * as ActionTypes from './ActionTypes';

export const Signup = (state = { //extended to 3 properties
    isAuthenticated : false,   //initially false, since signup has not happened
    errMess : null,     //errMess set to corresponding error message from server upon signup failure
    jwt : null
    }, action) => {
    switch (action.type) {
        case (ActionTypes.SIGNUP_SUCCESS):
            return {...state, isAuthenticated: true, errMess: null, jwt:action.payload.jwt};

        case (ActionTypes.SIGNUP_FAILED):
            return {...state, isAuthenticated: false, errMess: action.payload};

        default:
            return state;
    }
}