const React = require('react');
const ReactDOM = require('react-dom');
import { BrowserRouter } from 'react-router-dom';
import Main from './MainComponent.js';

class App extends React.Component {
	render() { 
		return (
			<BrowserRouter>
				<React.Fragment>
					<p>Welcome to Reactttttt!</p>
					<Main/>
				</React.Fragment>
			</BrowserRouter>
		)
	}
}

ReactDOM.render(
	<React.StrictMode>
	<App />
	</React.StrictMode>,
	document.getElementById('react')
)