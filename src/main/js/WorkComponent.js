import React, {Component} from 'react';

class Work extends Component {
    constructor(props){
        super(props);
        this.state = {
          name: null
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
            </div>
        );
    }
}

export default Work;