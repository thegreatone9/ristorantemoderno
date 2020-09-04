import React, { Component } from 'react';
import { Switch, Route, Redirect, withRouter } from 'react-router-dom';
import { connect } from 'react-redux';
//we import the action js objects and will then dispatch into store, and then receive props in this component
import { updateDatabaseTables, unsubscribe, subscribe, logout, signup, authenticate, postFeedback, postComment, fetchDishes, fetchPromos, fetchComments, fetchLeaders } from '../redux/ActionCreators';
import { actions } from 'react-redux-form'; //importing an action to enable persistent forms
import Header from './HeaderComponent';
import Footer from './FooterComponent';
import Menu from './MenuComponent';
import DishDetail from './DishDetailComponent';
import Home from './HomeComponent';
import Contact from './ContactComponent';
import About from './AboutComponent';
import Subscriptions from './SubscriptionsComponent';
import { TransitionGroup, CSSTransition } from 'react-transition-group';


/*mapStateToProps has the Store state as an argument/param (provided by react-redux::connect) and 
it's used to link the component with a CERTAIN PART of the store state. By linking I mean the object 
returned by mapStateToProps will be provided at construction time as props*/


//one can think of state as the object that comes out of the store (via the Provider?), and is put into the props

const mapStateToProps = state => {
    return {
      dishes : state.dishes,
      comments : state.comments,                
      promotions: state.promotions,             
      leaders: state.leaders,
      authentication : state.authentication,
      subscriptions : state.subscriptions,
      signup : state.signup
    }
}

//once you connect mapDispatchToProps to a component, the component receives props.dispatch, which can
//be used to dispatch into the store. The 'dispatch' function argument in the mapDispatchToProps function, 
//is from the store and will be obtained upon connection below.

//The mapDispatchToProps function returns an object containing: an postComment property, which will have the function 
//dispatch(action) as its value. This object containing the postComment property can be used as props for this component.


//one can think of dispatch as a function that is associated with/comes out of the store, and transitions an action into the props

const mapDispatchToProps = (dispatch) => ({
  postFeedback : (firstname, lastname, telnum, email, agree, contactType, message) => dispatch(postFeedback(firstname, lastname, telnum, email, agree, contactType, message)),
  postComment : (customerid, jwt, dishId, rating, author, comment) => dispatch(postComment(customerid, jwt, dishId, rating, author, comment)),
  fetchDishes : () => { dispatch(fetchDishes()) },                    //when this component mounts, we will call this function from the props
  resetFeedbackForm: () => { dispatch(actions.reset('feedback')) },  //adding another dispatch: form will be named as feedback and it will be updated. Now send to Contact Component.
  fetchComments: () => { dispatch(fetchComments()) },
  fetchPromos: () => { dispatch(fetchPromos()) },
  fetchLeaders: () => { dispatch(fetchLeaders()) },
  authenticate: (username, password) => { dispatch(authenticate(username, password)) }, 
  signup: (username, password, firstname, lastname) => { dispatch(signup(username, password, firstname, lastname)) },
  logout : () => { dispatch(logout()) },
  subscribe : (jwt, customerid, dishid) => { dispatch(subscribe(jwt, customerid, dishid)) },
  unsubscribe : (jwt, customerid, dishid) => { dispatch(unsubscribe(jwt, customerid, dishid)) },
  updateDatabaseTables : (jwt, commentid, customerid, dataFetch2, dataFetch3) => { dispatch(updateDatabaseTables(jwt, commentid, customerid, dataFetch2, dataFetch3)) }
});



class Main extends Component {


  //a good time to call to fetch data for application is when the component has just completed mounting
  //these functions, in turn dispatch data into the store and hence the props (see their action creators)
  componentDidMount() {
    this.props.fetchDishes(); //these function calls via actions are happening here!
    this.props.fetchComments();
    this.props.fetchPromos();
    this.props.fetchLeaders();
  }

  render() {
    const HomePage = () => {
      return(
          <Home //see the files 'dishes.js' and 'promotions.js': because the props now contains a dishes and promotions object (from dishes or promotions.js reducer), we need to call dishes.dishes and promotions.promotions to get the value of the key
            dish={this.props.dishes.dishes.filter((dish) => dish.featured)[0]}
            dishesLoading={this.props.dishes.isLoading}
            dishesErrMess={this.props.dishes.errMess}
            promotion={this.props.promotions.promotions.filter((promo) => promo.featured)[0]}
            promosLoading={this.props.promotions.isLoading}
            promosErrMess={this.props.promotions.errMess}
            leader={this.props.leaders.leaders.filter((leader) => leader.featured)[0]}
            leadersLoading={this.props.leaders.isLoading}
            leadersErrMess={this.props.leaders.errMess}
          />
      );
    }

    const DishWithId = ({match}) => {
      return(    
          <DishDetail 
            dish={this.props.dishes.dishes.filter((dish) => dish.id === parseInt(match.params.dishId, 10))[0]}  //10 is base 10
            isLoading={this.props.dishes.isLoading}
            errMess={this.props.dishes.errMess}
            comments={this.props.comments.comments.filter((comment) => comment.transferrableDishId === parseInt(match.params.dishId, 10))}
            commentsErrMess={this.props.comments.comments.errMess}
            postComment={this.props.postComment} 
            authentication = {this.props.authentication}
            subscribe = {this.props.subscribe}
          />
      );
    };

    const AboutWithLeaders = () => {
      return (
        <About leaders={this.props.leaders.leaders} leadersErrMess={this.props.leaders.errMess}/>
      );
    }

    return (
      <div>
        <Header logout = {this.props.logout} signup = {this.props.signup} authenticate = {this.props.authenticate} isAuthenticated = {this.props.authentication.isAuthenticated}/>
        <TransitionGroup>
          {/*The key value is a unique string that represents the current location.*/}
          <CSSTransition key={this.props.location.key} classNames="page" timeout={300}>
          <Switch>
            <Route path="/home" component={HomePage}/>
            <Route exact path="/menu" component={() => <Menu dishes={this.props.dishes} />}/>
            <Route exact path="/contactus" component={() => <Contact resetFeedbackForm = {this.props.resetFeedbackForm} postFeedback = {this.props.postFeedback}></Contact>} />
            <Route exact path="/subscriptions" component={() => <Subscriptions unsubscribe = {this.props.unsubscribe} subscriptions = {this.props.subscriptions} authentication = {this.props.authentication}/>} />
            <Route path='/menu/:dishId' component={DishWithId} />
            <Route path='/aboutus' component={AboutWithLeaders} />
            <Redirect to="/home"/>
          </Switch>
          </CSSTransition>
        </TransitionGroup>
        <Footer />
      </div>
    );
  }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));