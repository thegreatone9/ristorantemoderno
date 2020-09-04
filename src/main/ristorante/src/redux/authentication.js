import * as ActionTypes from './ActionTypes';

export const Authentication = (state = { //extended to 3 properties
    isAuthenticated : false,   //initially false, since authentication has not happened
    errMess : null,     //errMess set to corresponding error message from server upon authentication failure
    jwt : null,
    customerid: null
    }, action) => {
    switch (action.type) {
        case (ActionTypes.AUTHENTICATION_SUCCESS):
            return {...state, customerid: action.payload.customerid, isAuthenticated: true, errMess: null, jwt:action.payload.jwt};

        case (ActionTypes.AUTHENTICATION_FAILED):
            return {...state, isAuthenticated: false, errMess: action.payload};

        default:
            return state;
    }
}