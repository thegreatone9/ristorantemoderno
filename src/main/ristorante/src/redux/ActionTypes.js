//simply brainstorm the action types we want: for each action, have a string which defines its type
export const ADD_COMMENT = 'ADD_COMMENT';
export const DISHES_LOADING = 'DISHES_LOADING';
export const DISHES_FAILED = 'DISHES_FAILED';
export const ADD_DISHES = 'ADD_DISHES';
export const ADD_COMMENTS = 'ADD_COMMENTS';
export const COMMENTS_FAILED = 'COMMENTS_FAILED';
export const PROMOS_LOADING = 'PROMOS_LOADING';
export const ADD_PROMOS = 'ADD_PROMOS';
export const PROMOS_FAILED = 'PROMOS_FAILED';
export const ADD_LEADERS = 'ADD_LOADERS';
export const LEADERS_LOADING = 'LEADERS_LOADING';
export const LEADERS_FAILED = 'LEADERS_FAILED';


//new
export const AUTHENTICATE = 'AUTHENTICATE';
export const AUTHENTICATION_SUCCESS = 'AUTHENTICATION_SUCCESS';
export const AUTHENTICATION_FAILED = 'AUTHENTICATION_FAILED';

export const ADD_SUBSCRIPTIONS = 'ADD_SUBSCRIPTIONS';
export const SUBSCRIPTIONS_LOADING = 'SUBSCRIPTIONS_LOADING';
export const SUBSCRIPTIONS_FAILED = 'SUBSCRIPTIONS_FAILED';

//signup actions
export const SIGNUP = 'SIGNUP';
export const SIGNUP_SUCCESS = 'SIGNUP_SUCCESS';
export const SIGNUP_FAILED = 'SIGNUP_FAILED';

//subscribe to dishes
export const SUBSCRIBE_SUCCESSFUL = 'SUBSCRIBE_SUCCESSFUL';



//no comments loading, because by the time we get to dish detail and try to see comments, they should already
//have been loaded by then.

//now that we have some new action-types, make these actions in the actioncreators.js file