import React, {Component} from 'react';
import {Redirect} from "react-router-dom";

class Work2 extends Component {
    constructor(props){
        super(props);
        this.state = {
			username:"",
			password:"",
			jwt:"",
			data:"",
			moreData:"",
			isAuthenticated: false
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
		event.preventDefault();  
		console.log("{ wrapper - > ");
        
		//console.log(event.target[0].value);
		//console.log(event.target[1].value);
		
		
		//When receiving data from a web server, the data is always a string. - W3schools
		
		const data = { "username": ""+event.target[0].value, "password":""+event.target[1].value };
		//becareful of putting fetch api call inside another function...it disables the state interaction somehow. :/
		
		fetch('/authenticate', {
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
			this.setState({
      			jwt: response.jwt,
				isAuthenticated : true
    		});
			//console.log("First fetch done, now second: ");
			//console.log(this.state.jwt);
		})
		.catch((error) => {
            console.log(error);
        })
		//jwt is obtained, so now to fetch data from api with it
		.then(fetch('/rest_data', {
      		method: 'GET',
 			headers: new Headers({
      			'Authorization': 'Bearer ' + this.state.jwt,       
 				'Content-Type': 'application/json',
        		'Accept': 'application/json'
    		})
    	})
		.then(response => {
			console.log("debugging: 1");
            if (response.ok){
				//console.log("debugging: 2");
				//console.log(response);
                return response.text(); //to grab string
            }
            else {
				//console.log("debugging: 2.5");
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
			//console.log("debugging: 3");
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => {
			//console.log(" < - wrapper22222 }");
			//console.log(response);
			//console.log("This is what we got: " + JSON.stringify(response));
			
    		this.setState({
      			data: response
    		});

			//console.log("set state to: " + this.state.data);

		})
		.catch((error) => {
            console.log(error);
        }))
		//fetching more data now
		.then(fetch('/api/employees', {
      		method: 'GET',
 			headers: new Headers({     
 				'Content-Type': 'application/json',
        		'Accept': 'application/json'
    		})
    	})
		.then(response => {
			console.log("debugging: 99");
            if (response.ok){
				//console.log("debugging: 100");
				console.log(response);
                return response.json(); //response.json() turns response to json...JSON.parse(...) turns string to js object.
            							//we seemingly received an string array of json...which we were still able to do response.json to turn it into a json object
			}
            else {
				//console.log("debugging: 101");
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
			//console.log("debugging: 103");
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => {
			//console.log(" < - wrapper9999 }");
			console.log(typeof response);
			console.log(typeof response[0]);
			console.log(typeof response[0].firstName);
			console.log(response[0].firstName);
			console.log(response[0].lastName);
			console.log(response[0].email);
			console.log("This is what we got: " + JSON.stringify(response));
			
    		this.setState({
      			moreData: response[0]
    		});

			console.log("set state to: " + this.state.moreData.firstName);

		})
		.catch((error) => {
            console.log(error);
        }));
		
		this.setState();
		
    };

	logout = (event) => {
		event.preventDefault();
		this.setState({
			jwt : "",
			isAuthenticated : false
		})
	}

	
      
    render(){
        const username = this.state.username;
		const password = this.state.password;
		const jwt = this.state.jwt;
		const data = "data received: " + this.state.data;
		const moreData = this.state.moreData;
		
		
		//console.log(username);
		//console.log(password);
		//console.log(jwt);
		//console.log(data);
		console.log(moreData.firstName);
		
        return (
			<div>
			
				{this.state.isAuthenticated ? 
				
				/*(<Redirect to={{ pathname : "/subscriptions"  }}/>) */
				(
					<div>
						<h5>{data}</h5>
						<h5>{"" + (moreData.id) + " " + (moreData.firstName) + " " + moreData.lastName + " " + moreData.email}</h5>
						<button onClick = {this.logout}>Logout</button>
					</div>
				)
				
				: 
				
				(<div>
				<h1>Login sir</h1>
				   <form name='f' onSubmit = {this.handleSubmit}>
				      <table>
				         <tr>
				            <td>User:</td>
				            <td><input type='text' name='username'></input></td>
				         </tr>
				         <tr>
				            <td>Password:</td>
				            <td><input type='password' name='password' /></td>
				         </tr>
				         <tr>
				            <td><input name="submit" type="submit" value="Login"></input></td>
				         </tr>
				      </table>
				  </form>
				
            	</div>)}

			</div>
        );
    }
}

export default Work2;