import React, {Component} from 'react';
import { Navbar, Nav, NavItem } from 'reactstrap';
import { NavLink } from 'react-router-dom';

class Header extends Component {
	render() {
		return(
			<React.Fragment>
				<Navbar dark expand="md">
					<div className="container">
						<Nav navbar>
                                <NavItem>
                                    <NavLink className="nav-link" to="/home"> Home</NavLink>
                                </NavItem>
 								<NavItem>
                                    <NavLink className="nav-link" to="/another"> Another</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/work"> Work</NavLink>
                                </NavItem>
								<NavItem>
                                    <NavLink className="nav-link" to="/work2"> Work2</NavLink>
                                </NavItem>
                            </Nav>
					</div>
				</Navbar>
			</React.Fragment>
		);
	}
}

export default Header;
	