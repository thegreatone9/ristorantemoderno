import React, { Component } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import Work from './WorkComponent';
import Home from './HomeComponent';
import Another from './AnotherComponent';
import Header from './HeaderComponent';

class Main extends Component {
    render() {
    	return(
		  <div>
	  	  <Header/>
		  <hr/><hr/>
		  <Switch>
			  <Route path='/home' component={Home} />
              <Route path='/another' component={Another} />
              <Route exact path='/work' component={Work} />
              <Redirect to="/home" />
          </Switch>
		  </div>
		);
	}
}
export default Main;   