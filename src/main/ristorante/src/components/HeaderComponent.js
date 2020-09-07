import React, {Component} from 'react';
import { Navbar, NavbarBrand, Nav, NavbarToggler, Collapse, NavItem, Jumbotron, Modal, ModalHeader, ModalBody, Button, Form, FormGroup, Input, Label } from 'reactstrap';
import { NavLink } from 'react-router-dom';

class Header extends Component {
    constructor(props){
        super(props);
        this.state = {
            isNavOpen : false,
            isLoginModalOpen: false,
            isSignupModalOpen: false,
            isAuthenticated : this.props.isAuthenticated
        }
        //need to bind in react, to use functions in JSX. alternative to using arrow function inside the onClick() in the JSX
        this.toggleNav = this.toggleNav.bind(this);
        this.toggleLoginModal = this.toggleLoginModal.bind(this);
        this.toggleSignupModal = this.toggleSignupModal.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        this.handleSignup = this.handleSignup.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    toggleNav(){
        this.setState({
            isNavOpen : !this.state.isNavOpen
        });
    }

    toggleLoginModal(){
        this.setState({
            isLoginModalOpen: !this.state.isLoginModalOpen
        });
    }

    toggleSignupModal(){
        this.setState({
            isSignupModalOpen: !this.state.isSignupModalOpen
        });
    }

    handleLogin(event){
        this.toggleLoginModal();
        //alert("Username: " + this.username.value + "\nPassword: " + this.password.value + "\nRemember: " + this.remember.checked);
        console.log("Username: " + this.username.value + " Password: " + this.password.value);
        this.props.authenticate(this.username.value, this.password.value);
        event.preventDefault();
    }

    handleSignup(event){
        this.toggleSignupModal();
        console.log("First Name: " + this.firstname.value + " Last Name: " + this.lastname.value + "Username: " + this.username.value + " Password: " + this.password.value);
        this.props.signup(this.username.value, this.password.value, this.firstname.value, this.lastname.value);
        event.preventDefault();
    }

    handleLogout(event) {
        event.preventDefault();
        this.props.logout();
    }

    render(){
        return(
            <React.Fragment>
                <Navbar dark expand="md">
                    <div className="container">
                        {/*this reactstrap toggler button will be shown only for xs to sm screens. It controls collapse of navbar items*/}
                        <NavbarToggler onClick={this.toggleNav} />
                        <NavbarBrand className="mr-auto" href="/">
                            <img src='images/logo.png' height="40" width="50" alt='Ristorante Moderno'/>
                        </NavbarBrand>
                        <Collapse isOpen={this.state.isNavOpen} navbar>
                            <Nav navbar>
                                <NavItem>
                                    <NavLink className="nav-link" to="/home"> Home</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/aboutus"> About Us</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/menu"> Menu</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className="nav-link" to="/contactus"> Contact Us</NavLink>
                                </NavItem>
                                <NavItem>
                                {
                                    this.props.isAuthenticated ?

                                    (
                                        <NavLink className="nav-link" to="/subscriptions"> <span style={{color:"yellow"}}>Your Subscriptions</span></NavLink>
                                    )
                                    :
                                    (
                                        <NavLink className="nav-link" to="/subscriptions"> Your Subscriptions</NavLink>
                                    )
                                }

                                </NavItem>
                            </Nav>
                            <Nav className="ml-auto" navbar>
                                {
                                     this.props.isAuthenticated ? 
				
                                     /*(<Redirect to={{ pathname : "/subscriptions"  }}/>) */
                                     (
                                         <div className="">
                                            <NavItem style={{color:"yellow", padding:"5px", display:"inline-block"}}>Welcome!</NavItem> 
                                            <NavItem style={{display:"inline-block"}}><Button  onClick={this.handleLogout}  style={{height:"33px", display:"inline-block"}}>Logout</Button></NavItem>
                                         </div>
                                     )
                                     
                                     : 
                                     
                                     (
                                        <div>
                                            <NavItem>
                                                <Button onClick={this.toggleLoginModal} color="success" style={{margin:"5px"}}><span > <b>Login</b></span></Button>
                                                <Button onClick={this.toggleSignupModal} color="danger" style={{margin:"5px"}}><span > <b>New? SignUp</b></span></Button>
                                            </NavItem>
                                        </div>
                                     )
                                }
                               
                                <Modal isOpen={this.state.isLoginModalOpen} toggle={this.toggleLoginModal}>
                                    <ModalHeader toggle={this.toggleLoginModal}>Login</ModalHeader>
                                    <ModalBody>
                                        <Form onSubmit={this.handleLogin}>
                                            <FormGroup>
                                                <Label htmlFor="username">Email</Label>
                                                <Input type="text" id="username" name="username" innerRef = {(input) => this.username = input}></Input>
                                            </FormGroup>
                                            <FormGroup>
                                                <Label htmlFor="password">Password</Label>
                                                <Input type="password" id="password" name="password" innerRef = {(input) => this.password = input}></Input>
                                            </FormGroup>
                                            <FormGroup check>
                                                <Label check>
                                                    <Input type="checkbox" name="remember" innerRef = {(input) => this.remember = input}></Input>
                                                    <p>Remember me</p>
                                                </Label>
                                            </FormGroup>
                                            <Button type="submit" value="submit" className="primary">Login</Button>
                                            
                                        </Form>
                                    </ModalBody>
                                </Modal>

                                <Modal isOpen={this.state.isSignupModalOpen} toggle={this.toggleSignupModal}>
                                    <ModalHeader toggle={this.toggleSignupModal}>Sign Up</ModalHeader>
                                    <ModalBody>

                                        <Form onSubmit={this.handleSignup}>

                                            
                                            <FormGroup>
                                                <Label htmlFor="firstname">First Name</Label>
                                                <Input type="text" id="firstname" name="firstname" innerRef = {(input) => this.firstname = input}></Input>
                                            </FormGroup>

                                            <FormGroup>
                                                <Label htmlFor="lastname">Last Name</Label>
                                                <Input type="text" id="" name="lastname" innerRef = {(input) => this.lastname = input}></Input>
                                            </FormGroup>

                                            <FormGroup>
                                                <Label htmlFor="username">Email</Label>
                                                <Input type="text" id="username" name="username" innerRef = {(input) => this.username = input}></Input>
                                            </FormGroup>
                                            
                                            <FormGroup>
                                                <Label htmlFor="password">Password</Label>
                                                <Input type="password" id="password" name="password" innerRef = {(input) => this.password = input}></Input>
                                            </FormGroup>

                                            <Button type="submit" value="submit" className="primary">SignUp</Button>
                                            
                                        </Form>
                                    </ModalBody>
                                </Modal>
                            </Nav>
                        </Collapse>
                    </div>
                </Navbar>
                <Jumbotron style={{background: "linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.8)), url('images/jumbotron.png') center"}}>
                    <div className="container" >
                        <div className = "row row-header">
                            <div className = "col-12 col-sm-6">
                                <h1>Ristorante Moderno</h1>
                                <p>In a post-COVID world and culture, powered by modern technology, take inspiration from the World's best cuisines, and create a unique fusion experience. Our lipsmacking creations will tickle your culinary senses!</p>
                            </div>
                        </div>
                    </div>
                </Jumbotron>
            </React.Fragment>
        );
    }
}

export default Header;