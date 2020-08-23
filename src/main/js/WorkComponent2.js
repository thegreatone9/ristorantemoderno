import React, {Component} from 'react';

class Work2 extends Component {
    constructor(props){
        super(props);
        this.state = {
			username:"",
			password:"",
			jwt:"",
			data:""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
		event.preventDefault();  
		console.log("{ wrapper - > ");
        
		console.log(event.target[0].value);
		console.log(event.target[1].value);
		
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
			console.log(" < - wrapper }");
			console.log(JSON.stringify(response));
			console.log("This is what we got: " + response.jwt);
			this.setState({
      			jwt: response.jwt
    		});
			console.log("First fetch done, now second: ");
			console.log(this.state.jwt);
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
				console.log("debugging: 2");
				console.log(response);
                return response.text(); //to grab string
            }
            else {
				console.log("debugging: 2.5");
                let error = new Error("Error: " + response.status + ": " + response.statusText);
                error.response = response;
                throw error;
            }
        },
        error => {
			console.log("debugging: 3");
            let errmess = new Error(error.message);
            throw errmess;
        }
        )
        .then(response => {
			console.log(" < - wrapper22222 }");
			console.log(response);
			console.log("This is what we got: " + JSON.stringify(response));
			
    		this.setState({
      			data: response
    		});

			console.log("set state to: " + this.state.data);

		})
		.catch((error) => {
            console.log(error);
        }));
		
	
		
    };
      
    render(){
        const username = this.state.username;
		const password = this.state.password;
		const jwt = this.state.jwt;
		const data = "data received: " + this.state.data;
		
		console.log(username);
		console.log(password);
		console.log(jwt);
		console.log(data);
		
        return (
            <div>
				<h1>Login</h1>
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
				            <td><input name="submit" type="submit" value="submit"></input></td>
				         </tr>
				      </table>
				  </form>
		
				<h5>{data}</h5>
            </div>
        );
    }
}

export default Work2;