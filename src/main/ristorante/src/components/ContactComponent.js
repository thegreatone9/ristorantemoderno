import React, {Component} from 'react';
import { Breadcrumb, BreadcrumbItem, Row, Input, Label, Col, Button } from 'reactstrap';
import { Control, Form, Errors, actions } from 'react-redux-form';
import { Link } from 'react-router-dom';

const required = (val) => val && val.length;
const maxLength = (len) => (val) => !(val) || (val.length <= len);
const minLength = (len) => (val) => val && (val.length >= len);
const isNumber = (val) => !isNaN(Number(val));
const validEmail = (val) => /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(val);

class Contact extends Component {

    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(values){
        console.log('Current state is ' + JSON.stringify(values));
        this.props.postFeedback(values.firstname, values.lastname, values.telnum, values.email, values.agree, values.contactType, values.message);
        this.props.resetFeedbackForm();
    }

    
    render(){

       return(
            <div className="container">
                <div className="row">
                    <Breadcrumb>
                        <BreadcrumbItem><Link to='/home'>Home</Link></BreadcrumbItem>
                        <BreadcrumbItem active>Contact Us</BreadcrumbItem>
                    </Breadcrumb>
                    <div className="col-12">
                        <h3>Menu</h3>
                        <hr/>
                    </div>
                </div>
                <div className="row row-content">
                    <div className="col-12">
                    <h3>Location Information</h3>
                    </div>
                    <div className="col-12 col-sm-4 offset-sm-1">
                            <h5>Our Address</h5>
                            <address>
                            <div style={{color:'black'}}>
                            Eskaton Garden Road<br />
		                    Dhaka, Greater<br />
		                    Bangladesh<br />
                            <i className="fa fa-phone"></i>: +88 017 964 1466<br />
                            <i className="fa fa-fax"></i>: +88 017 964 1466<br />
                            <i className="fa fa-envelope"></i>: <a href="mailto:musashakib123@gmail.com"> musashakib123@gmail.com"</a>
                            </div>
                            </address>
                    </div>
                    <div className="col-12 col-sm-6 offset-sm-1">
                        <h5>Map of our Location</h5>
                    </div>
                    <div className="col-12 col-sm-11 offset-sm-1">
                        <div className="btn-group" role="group">
                            <a role="button" className="btn btn-primary" href="tel:+880179641466"> Call</a>
                            <a role="button" className="btn btn-info"> Skype</a>
                            <a role="button" className="btn btn-success" href="mailto:musashakib123@gmail.com"> Email</a>
                        </div>
                    </div>
                </div>
                <div className = "row m-3">
                    <div className="col-12">
                          <h3>Send us your Feedback</h3>
                       </div>
                    <div className = "col-12 col-md-9">
                        <Form model="feedback" onSubmit={(values) => this.handleSubmit(values)}>
                            <Row className="form-group">
                                <Label htmlFor="firstname" md={2} >
                                    First Name
                                </Label>
                                <Col md={10}>
                                    <Control.text model=".firstname" id="firstname" name="firstname" placeholder="First Name" className="form-control" validators = {{required, minLength:minLength(3), maxLength:maxLength(15)}}></Control.text>
                                    <Errors className="text-danger" model=".firstname" show="touched" messages={{required: 'Required', minLength:' Must be greater than 2 chars', maxLength:' Must be less than 15 chars'}}/>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Label htmlFor="lastname" md={2} >
                                    Last Name
                                </Label>
                                <Col md={10}>
                                    <Control.text model=".lastname" id="lastname" name="lastname" placeholder="Last Name" className="form-control" validators = {{required, minLength:minLength(3), maxLength:maxLength(15)}}></Control.text>
                                    <Errors className="text-danger" model=".firstname" show="touched" messages={{required: 'Required', minLength:' Must be greater than 2 chars', maxLength:' Must be less than 15 chars'}}/>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Label htmlFor="telnum" md={2} >
                                    Telephone Number
                                </Label>
                                <Col md={10}>
                                    <Control.text model=".telnum" id="telnum" name="telnum" placeholder="Telephone" className="form-control" validators = {{required, minLength:minLength(3), maxLength:maxLength(15), isNumber}}></Control.text>
                                    <Errors className="text-danger" model=".telnum" show="touched" messages={{required:'Required', minLength:' Must be greater than 2 chars', maxLength:' Must be less than 15 chars', isNumber:' Must be a number'}} />
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Label htmlFor="email" md={2} >
                                    Email
                                </Label>
                                <Col md={10}>
                                    <Control.text model=".email" id="email" name="email" placeholder="Email" className="form-control" validators = {{required, validEmail }}></Control.text>
                                    <Errors className="text-danger" model=".email" show="touched" messages={{required:'Required', validEmail:' Must be a valid email'}}/>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Col md={{size: 6, offset: 2}} >
                                    <div className="form-check">
                                        <Label check>
                                            <Control.checkbox model=".agree" id="agree" name="agree" className="form-check-input"></Control.checkbox>
                                            <strong>May we contact you?</strong>
                                        </Label>
                                    </div>
                                </Col>
                                <Col md={{size: 3, offset: 1}} >
                                    <Control.select model=".contactType" id="contactType" name="contactType" className="form-control" >
                                        <option>Tel.</option>
                                        <option>Email</option>
                                    </Control.select>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Label htmlFor="message" md={2}>Your Feedback</Label>
                                <Col md={10}>
                                    <Control.textarea model=".message" id="message" name="message" rows={12} className="form-control">
                                    </Control.textarea>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Col md={{size: 10, offset: 2}}>
                                    <Button type="submit" color="primary">
                                        Send Feedback
                                    </Button>
                                </Col>
                            </Row>
                        </Form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Contact;