import * as ActionTypes from './ActionTypes';

export const Subscriptions = (state = { //extended to 3 properties
    isLoading : true,   //initially true, since subscriptions have not loaded
    errMess : null,     //errMess set to corresponding error message from server upon authentication failure
    subscriptions : []         //initially set to empty array, but when loaded (due to ADD_SUBSCRIPTIONS) it will be full
    }, action) => {
    switch (action.type) {
        case (ActionTypes.ADD_SUBSCRIPTIONS):
            console.log("inside subscriptions reducer");
            console.log(typeof action.payload);
            console.log(action.payload);
            return {...state, isLoading: false, errMess: null, subscriptions: action.payload};

        case (ActionTypes.SUBSCRIPTIONS_LOADING):
            return {...state, isLoading: true, errMess: null, subscriptions: []};     //whatever the current value of state is...i make some changes to the object. Precautionary assignments are made too even though they seem redudant.
        
        case (ActionTypes.SUBSCRIPTIONS_FAILED):
            return {...state, isLoading: false, errMess: action.payload, subscriptions : []};

        default:
            return state;
    }
}