import * as ActionTypes from './ActionTypes';
import { baseUrl } from '../shared/baseUrl';


//we import all the different types of action types that we have decided to have and then define how those actions
//would work. Each action that is created is a js object. These actions will be fed into the dispatcher via the mapDispatch functions.

//Some of these actions get data from the server using fetch api and then dispatch into the store. From the store, the data enters the components. If the components trigger actions again, the actions will dispatch more data into the store.

//***ASSIGNMENT WORK!!!***
export const postFeedback = (firstname, lastname, telnum, email, agree, contactType, message) => (dispatch) => {
    const newFeedback = {
        firstname, 
        lastname, 
        telnum,
        email, 
        agree, 
        contactType, 
        message, 
    }

    newFeedback.date = new Date().toISOString();
    
    return fetch(baseUrl + 'api/feedbacks', {
        method : 'POST',
        body : JSON.stringify(newFeedback),
        headers : {
            "Content-Type" : "application/json"
        },
        credentials : 'same-origin'})
        .then(response => {
            if (response.ok){
                return response;
            }
            else {
                let error = new Error('Error: ' + response.status + response.statusText);
                error.response = error;
                throw error;
            }
        },
        error => {
            throw error;
        })
        .then(response => response.json())
        .then(response => {
            console.log(JSON.stringify(response));
            setTimeout(alert('We appreciate your feedback: ' + firstname + ' says ' + message + '.'), 2000);
            //dispatch(response); //fix this
        })
        .catch(error => {
            console.log('post feedback', error.message);
            setTimeout(alert('Your feedback could not be posted\nError: ' + error.message), 2000);
        });
};


/*we should not be needing to call this directly(?) since we are going to use REST API's HTTP-POST method*/
export const addComment = function (comment) {                                                
    //console.log(dishId, rating, author, comment);
    return ({
        type : ActionTypes.ADD_COMMENT,
        payload : comment
    });
};

export const postComment = (customerid, jwt, dishId, rating, author, comment) => (dispatch) => {
    console.log(dishId);
    const dataFetch2 = baseUrl+`api/dishes/${dishId}`;
    const dataFetch3 = baseUrl+`api/customers/${customerid}`;
    console.log("dataFetch2 : " + dataFetch2);
    console.log("dataFetch3 : " + dataFetch3);
    const transferrableDishId = dishId;
    const newComment = {
        transferrableDishId,
        rating,
        author,
        comment       
    }

    console.log(newComment);

    newComment.date = new Date().toISOString();

    let commentid = null;

    return fetch(baseUrl + 'api/comments', {
            method : "POST",
            body : JSON.stringify(newComment),
            headers : {
                'Authorization': 'Bearer ' + jwt,
                "Content-Type" : "application/json",
                'Accept' : 'application/json'
            },
            credentials : 'same-origin'})
            .then(response => {
                if (response.ok) 
                    return response;
                else {
                    let error = new Error('Error: ' + response.status + ": " + response.statusText);
                    error.response = response;
                    throw error;
                }
            },
            error => {
                throw error;
            })
            .then(response => response.json())
            .then(response => {
                console.log(response); 
                console.log(response.id); 
                commentid = response.id; 
                console.log(commentid); 
                dispatch(addComment(response)); 
                dispatch(updateDatabaseTables(jwt, commentid, customerid, dataFetch2, dataFetch3));
            })   //post method includes an id during posting and returns it as response, which will be then dispatched into store via the addComment action
            .catch(error => { console.log('post comments', error.message);  
                              setTimeout(alert('Your comment could not be posted\nError: ' + error.message), 2000); });
        };

          
export const updateDatabaseTables = (jwt, commentid, customerid, dataFetch2, dataFetch3) => (dispatch) => {
    
    console.log(commentid);
    console.log(dataFetch2);
    console.log(dataFetch3);

    fetch(baseUrl + 'api/comments' + `/${commentid}/dish`, {
        method: 'PUT',
        body: dataFetch2,
        headers: {
            'Authorization': 'Bearer ' + jwt,
            'Content-Type': 'text/uri-list'
        }
    })
    .then(response => {
        if (response.ok){
            console.log(response.statusText);
            return response;
        }
        else {
            let error = new Error("Error: " + response.status + ": " + response.statusText);
            error.response = response;
            throw error;
        }
    },
    error => {
        let errmess = new Error(error.message);
        throw errmess;
    }
    )
    .then(() => {
        console.log("New id enumerated in dish column of comments table!");
        //alert("New dish subscribed!");
        //dispatch(fetchSubscriptions(jwt, customerid));
    })
    .catch(error => { 
        console.log(error);
    });

    
    fetch(baseUrl + 'api/comments' + `/${commentid}/customer`, {
        method: 'PUT',
        body: dataFetch3,
        headers: {
            'Authorization': 'Bearer ' + jwt,
            'Content-Type': 'text/uri-list'
        }
    })
    .then(response => {
        if (response.ok){
            console.log(response.status);
            return response;
        }
        else {
            let error = new Error("Error: " + response.status + ": " + response.statusText);
            error.response = response;
            throw error;
        }
    },
    error => {
        let errmess = new Error(error.message);
        throw errmess;
    }
    )
    .then(() => {
        console.log("New id enumerated in dish column of comments table!");
        //alert("New dish subscribed!");
        dispatch(fetchSubscriptions(jwt, customerid));
    })
    .catch(error => { 
        console.log(error);
    });
}

             
 



//send this action object to the specific component's props (in our case the MainComponent) from where you want to dispatch it into store

//this addComment function should just be changing the 'comments' part of the state, so we 
//should go to the comments.js reducer file next...


//This action is returning a function as opposed to an action object...it is a thunk, which
//will be used to dispatch a function into the store
export const fetchDishes = () => (dispatch) => {

    dispatch(dishesLoading(true));

    return fetch(baseUrl + 'api/dishes')
    .then(response => {
        if (response.ok) {
            console.log("ATLEAST WE CONNECTED TO THE DISHES ALRIGHT!");
            return response.json();
        } else {
          var error = new Error('Error ' + response.status + ': ' + response.statusText);
          error.response = response;
          throw error;
        }
      },
      error => {
            var errmess = new Error(error.message);
            throw errmess;
      })
    .then(response => {
        
        console.log(response._embedded);
        return(response._embedded);
    })
    .then(dishes => dispatch(addDishes(dishes.dishes)))
    .catch(error => dispatch(dishesFailed(error.message)));
}

export const fetchComments = () => (dispatch) => {
    dispatch(dishesLoading(true));
    //after a 2s delay (due to json-server flag), fetch API will load stuff:
    return fetch(baseUrl + 'api/comments')
        .then(response => {
            if (response.ok){
                return response.json();
            }
            else {
                var error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            var errmess = new Error(error.message);
            throw errmess;
        }    
        )
        .then(response => {
            return response._embedded;
        })
        .then(comments => dispatch(addComments(comments.comments)))
        .catch(error => dispatch(commentsFailed(error.message)));
}

export const fetchLeaders = () => (dispatch) => {
    //after a 2s delay (due to json-server flag), fetch API will load leaders;
    dispatch(dishesLoading(true));

    return fetch(baseUrl + 'api/leaders')
        .then(response => {
            if (response.ok){
                return response.json();
            }
            else {
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => response._embedded)
        .then(leaders => dispatch(addLeaders(leaders.leaders)))
        .catch(error => dispatch(leadersFailed(error.message)));
}


export const signup = (username, password, firstname, lastname) => (dispatch) => {
    const data = { "username": ""+username, "password":""+password, "firstname": ""+firstname, "lastname":""+lastname };
        return fetch(baseUrl + 'signup', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok){
                return response;
            }
            else {
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => response.text())
        .then((responseText) => {
            if (responseText.substring(7,8) !== 'A') {
                console.log(responseText);
                console.log(responseText.substring(7,8));
                setTimeout(alert("Your account was created. Welcome to Ristorante Moderno!"), 2500);
                dispatch(authenticate(username, password));
            }
            else {
                console.log(responseText);
                setTimeout(alert("Those credentials already exist on server."), 4000);
            }
        })
        .catch(error => {     
            dispatch(signupFailed(error.message));
            setTimeout(alert("Signing up failed!"), 2000);
        })
}

export const signupSuccess = (jwt) => ({
    type: ActionTypes.SIGNUP_SUCCESS,
    payload : {
        jwt : jwt
    }
});

export const signupFailed = (errmess) => ({
    type: ActionTypes.SIGNUP_FAILED,
    payload: errmess
});

//baseUrl: http://localhost:3001/
export const authenticate = (username, password) => (dispatch) => {
        const data = { "username": ""+username, "password":""+password };
        return fetch(baseUrl + 'authenticate', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok){
                return response;
            }
            else {
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => response.json())
        .then(response => {
            //console.log(" < - wrapper }");
            //console.log(JSON.stringify(response));
            //console.log("This is what we got: " + response.jwt);
            /*(this.setState({
                jwt: response.jwt,
                isAuthenticated : true
            });*/
            console.log(response);
            console.log(response.jwt);
            console.log(response.customerid);
            //dispatch(authenticationSuccess(response.jwt));
            dispatch(fetchSubscriptions(response.jwt, response.customerid));
            setTimeout(alert("You have been logged in! :)"), 2000);
            //console.log("First fetch done, now second: ");
            //console.log(this.state.jwt);
        })
        .catch(error => { 
            //console.log(error); 
            dispatch(authenticationFailed(error.message));
            setTimeout(alert("Credentials do not exist or match with database records!"), 2000);
        })
}

export const authenticationSuccess = (customerid, jwt) => ({
    type: ActionTypes.AUTHENTICATION_SUCCESS,
    payload : {
        customerid: customerid,
        jwt : jwt
    }
});

export const authenticationFailed = (errmess) => ({
    type: ActionTypes.AUTHENTICATION_FAILED,
    payload: errmess
});


export const logout = () => (dispatch) => {
    dispatch(authenticationFailed());
}

//baseUrl: http://localhost:3001/
export const fetchSubscriptions = (jwt, customerid) => (dispatch) => {
    //after a 2s delay (due to json-server flag), fetch API will load leaders;
    

    console.log(jwt);
    console.log(customerid);
    return fetch(baseUrl + `api/customers/${customerid}/subscriptions`, {
        method: 'GET',
        headers: new Headers({
            'Authorization': 'Bearer ' + jwt,       
            'Content-Type': 'application/json',
            'Accept': 'application/json'
      })
        })
        .then(response => {
            if (response.ok){
                return response.json();
            }
            else {
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => response._embedded)
        .then(subscriptions => {const tryy = "heyy"; console.log(typeof tryy); console.log("hey I am here!!!!"); console.log(subscriptions.dishes); console.log(subscriptions); dispatch(addSubscriptions(subscriptions.dishes)); dispatch(authenticationSuccess(customerid, jwt));})
        .catch(error => dispatch(subscriptionsFailed(error.message)));
}


//these return objects...they are not thunk
export const subscriptionsLoading = () => ({
    type: ActionTypes.SUBSCRIPTIONS_LOADING
});

export const subscriptionsFailed = (errmess) => ({
    type: ActionTypes.SUBSCRIPTIONS_FAILED,
    payload: errmess
});

export const addSubscriptions = (subscriptions) => ({
    type: ActionTypes.ADD_SUBSCRIPTIONS,
    payload: subscriptions
});



export const subscribe = (jwt, customerid, dishid)  => (dispatch) => {
    const data = baseUrl+`api/customers/${customerid}`;
    console.log(data);
    return fetch(baseUrl + 'api/dishes' + `/${dishid}/customers`, {
        method: 'PUT',
        body: data,
        headers: {
            'Content-Type': 'text/uri-list'
        }
    })
    .then(response => {
        if (response.ok){
            console.log(response.statusText);
            return response;
        }
        else {
            let error = new Error("Error: " + response.status + ": " + response.statusText);
            error.response = response;
            throw error;
        }
    },
    error => {
        let errmess = new Error(error.message);
        throw errmess;
    }
    )
    .then(() => {
        console.log("New dish subscribed!");
        setTimeout(alert("New dish subscribed!"), 2000);
        dispatch(fetchSubscriptions(jwt, customerid));
    })
    .catch(error => { 
        console.log(error);
    });
}

export const unsubscribe = (jwt, customerid, dishid)  => (dispatch) => {
    console.log("sending delete command for selected dish");
    return fetch(baseUrl + 'api/customers' + `/${customerid}/subscriptions/${dishid}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + jwt,       
        }
    })
    .then(response => {
        if (response.ok){
            console.log(response.statusText);
            return response;
        }
        else {
            let error = new Error("Error: " + response.status + ": " + response.statusText);
            error.response = response;
            throw error;
        }
    },
    error => {
        let errmess = new Error(error.message);
        throw errmess;
    }
    )
    .then(() => {
        console.log("Dish unsubscribed!");
        setTimeout(alert("The selected dish was unsubscribed!"), 2000);
        dispatch(fetchSubscriptions(jwt, customerid));
    })
    .catch(error => { 
        console.log(error);
    });
}



//these return objects...they are not thunk
export const dishesLoading = () => ({
    type: ActionTypes.DISHES_LOADING
});

export const dishesFailed = (errmess) => ({
    type: ActionTypes.DISHES_FAILED,
    payload: errmess
});

export const addDishes = (dishes) => ({
    type: ActionTypes.ADD_DISHES,
    payload: dishes
});


//the 'leaders' actions
export const leadersLoading = () => ({
    type: ActionTypes.LEADERS_LOADING
});

export const leadersFailed = (errmess) => ({
    type: ActionTypes.LEADERS_FAILED,
    payload: errmess
});

export const addLeaders = (leaders) => ({
    type: ActionTypes.ADD_LEADERS,
    payload: leaders
});


export const commentsFailed = (errmess) => ({
    type: ActionTypes.COMMENTS_FAILED,
    payload: errmess
});

export const addComments = (comments) => ({
    type: ActionTypes.ADD_COMMENTS,
    payload: comments
});



export const fetchPromos = () => (dispatch) => {
    dispatch(promosLoading());
    //after a 2s delay (due to json-server flag), fetch API will load stuff:
    return fetch(baseUrl + 'api/promotions')
        .then(response => {
            if (response.ok){
                return response.json();
            }
            else {
                var error = new Error('Error: ' + response.status + ': ' + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
            var errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => response._embedded)
        .then(promos => dispatch(addPromos(promos.promotions)))
        .catch(error => dispatch(promosFailed(error.message)));
}


//these return objects...they are not thunk
export const promosLoading = () => ({
    type: ActionTypes.PROMOS_LOADING
});

export const promosFailed = (errmess) => ({
    type: ActionTypes.PROMOS_FAILED,
    payload: errmess
});

export const addPromos = (promos) => ({
    type: ActionTypes.ADD_PROMOS,
    payload: promos
});
