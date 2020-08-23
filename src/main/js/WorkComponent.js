import React, {Component} from 'react';

class Work extends Component {
    constructor(props){
        super(props);
        this.state = {
          //name: null
			username:"",
			password:""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
		event.preventDefault();  
		console.log("{ wrapper - > ");
        
		
		//const data = new FormData(event.target);
		const data = { "data": ""+event.target[0].value};
		
		fetch('/api', {
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
			console.log("This is what we got: " + JSON.stringify(response));
			this.setState({
            	name : response.data
        	});
		});
		
		
		
        
    };
      
    render(){
        const name = this.state.name;
        return (
            <div className="App">
                <header className="App-header"> 
            		<strong>Work</strong>
                    <p>Hello user! Enter your name below: </p>
            
                    <form onSubmit = {this.handleSubmit}>
                        <input type="text" id="name" name="name"></input>
                        <input type="submit" value="Submit" ></input>
                    </form>
            
                    <p>Server replies: Hi {name}</p>
                </header>


				<h1>Login</h1>
				   <form name='f' action="login" method='POST'>
				      <table>
				         <tr>
				            <td>User:</td>
				            <td><input type='text' name='username' value=''></input></td>
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
            </div>
        );
    }
}

export default Work;