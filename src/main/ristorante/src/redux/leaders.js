import * as ActionTypes from './ActionTypes';

export const Leaders = (state = { //extended to 3 properties
    isLoading : true,   //initially true, since dishes have not loaded
    errMess : null,     //errMess set to corresponding error message from server
    leaders : []         //initially set to empty array, but when loaded (due to ADD_LEADERS) it will be full
    }, action) => {
    switch (action.type) {
        case (ActionTypes.ADD_LEADERS):
            return {...state, isLoading: false, errMess: null, leaders: action.payload};

        case (ActionTypes.LEADERS_LOADING):
            return {...state, isLoading: true, errMess: null, leaders: []};     //whatever the current value of state is...i make some changes to the object. Precautionary assignments are made too even though they seem redudant.
        
        case (ActionTypes.LEADERS_FAILED):
            return {...state, isLoading: false, errMess: action.payload, leaders : []};

        default:
            return state;
    }
}