import { createStore, combineReducers, applyMiddleware } from 'redux';
//import { Reducer, initialState} from './reducer';
import { createForms, formReducer } from 'react-redux-form'; //enables us to input form state into our store
import {Leaders} from './leaders';
import {Comments} from './comments';
import {Dishes} from './dishes';
import {Promotions} from './promotions';
import { InitialFeedback } from './forms';
import {Authentication} from './authentication';
import {Signup} from './signup';
import {Subscriptions} from './subscriptions';
import thunk from 'redux-thunk';
import logger from 'redux-logger';

export const ConfigureStore = () => {
    const store = createStore(
        combineReducers({
            dishes:Dishes,
            leaders:Leaders,
            comments:Comments,
            promotions:Promotions,
            signup:Signup,
            authentication:Authentication,
            subscriptions:Subscriptions,
            ...createForms({                  //create a reducer that takes care of the form
                feedback: InitialFeedback
            })
        }),
        applyMiddleware(thunk, logger)    //these stuff are enhancers for our store, and thunker and logger are now available in our application
        
        );
    return store;
}


/*
import { Reducer, initialState } from './reducer';
import { createStore } from 'redux';

export const ConfigureStore = () => {
    const store = createStore(Reducer, initialState);
    return store;
}*/