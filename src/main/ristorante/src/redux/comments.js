//import { COMMENTS } from '../shared/comments'; <- these no longer needed since they will brought directly by the server via fetch
import * as ActionTypes from './ActionTypes';

//each of these reducer files updates/modifies its exported state, based on the action that it receives in through the dispatcher, to the Store (which is a compilation
//of all the reducers and states)
export const Comments = (state = {
    errMess : null,
    comments : []
    }, action) => {
    switch(action.type){
        case ActionTypes.ADD_COMMENTS:
            return {...state, isLoading: false, errMess: null, comments: action.payload};

        case (ActionTypes.COMMENTS_FAILED):
            return {...state, isLoading: false, errMess: action.payload, comments : []};

        case ActionTypes.ADD_COMMENT:
            let comment = action.payload;
            //updating the comments, since the state of this Comments action now also contains errMess

            //update*: now that we have post-comment, comment id is auto added by server and the following line is handled in the action 'addComment'
            /*
            comment.id = state.comments.length;
            comment.date = new Date().toISOString();

            Also, the way it works now is that the comment is first added to the server, and then added to the redux store
            */
            return {...state, comments: state.comments.concat(comment)}
        default:
            return state;
    }
};