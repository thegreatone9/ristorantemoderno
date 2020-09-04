import React, {Component} from "react";
import { Card, CardImg, CardText, CardBody, CardTitle, Breadcrumb, BreadcrumbItem, Button,
                     Modal, ModalHeader, ModalBody, Row, Col, Label } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Control, LocalForm, Errors } from 'react-redux-form';
import { Loading } from './LoadingComponent';
//import { baseUrl } from '../shared/baseUrl';
import { FadeTransform, Fade, Stagger } from 'react-animation-components';



        function RenderDish({dish,isLoading,errMess})
        {
            if (isLoading) {
                return(
                    <div className="container">
                        <div className="row">            
                            <Loading />
                        </div>
                    </div>
                );
            }
            else if (errMess) {
                return(
                    <div className="container">
                        <div className="row">            
                            <h4>{errMess}</h4>
                        </div>
                    </div>
                );
            }
            else if (dish != null)
           
            // const dish = this.props.dish;
             return(
               
                     <div  className="col-12 col-md-5 m-1">
               <FadeTransform
                in
                transformProps={{
                    exitTransform: 'scale(0.5) translateY(-50%)'
                }}>
            <Card>
                <CardImg top src={dish.image} alt={dish.name} />
                <CardBody>
                    <CardTitle>{dish.name}</CardTitle>
                    <CardText>{dish.description}</CardText>
                </CardBody>
            </Card>
            </FadeTransform>
                 </div>
                
             );
        else
             return(
                 <div> </div>
             );
        }

        function RenderComments({comments, postComment, dishId})
        {
            if (comments == null) {
                return (<div></div>)
            }
            
                const showComments = comments.map(comment=> {
                    return (
                        
                        <Fade in>
                        <li key={comment.id} >
                            <p>{comment.comment}</p>
                            <p>--{comment.author},
                            &nbsp;
                            {new Intl.DateTimeFormat('en-US', {
                            year: 'numeric',
                            month: 'long',
                            day: '2-digit'
                        }).format(new Date(comment.date))}
                            </p>
                        </li>
                        </Fade>
                       
                    );
                })
                 

                return (
                    <div className='col-12 col-md-5 m-1'>
                        <h4> Comments </h4>
                        <ul className='list-unstyled'>
                        <Stagger in>
                            {showComments}
                            </Stagger>
                        </ul>
                        <CommentForm dishId={dishId} postComment={postComment} />
                    </div>
                )
                
               
            
        }
        const  DishDetail = (props) =>  {
            const dish = props.dish
            if (dish == null) {
                return (<div></div>)
            }
         
            return (
                <div className="container">
                <div className="row">
                    <Breadcrumb>
                        <BreadcrumbItem><Link to="/menu">Menu</Link></BreadcrumbItem>
                        <BreadcrumbItem active>{props.dish.name}</BreadcrumbItem>
                    </Breadcrumb>
                    <div className="col-12">
                        <h3>{props.dish.name}</h3>
                        <hr />
                    </div>                
                </div>
                <div className="row">
                    <RenderDish dish={props.dish} isLoading ={props.isLoading} errMess ={props.errMess} />
                    <RenderComments comments={props.comments}
        postComment={props.postComment}
        dishId={props.dish.id}
      />
                </div>
                </div>
            );
        }

    
    
export default DishDetail;



const required = (val) => val && val.length;
const maxLength = (len) => (val) => !(val) || (val.length <= len);
const minLength = (len) => (val) => val && (val.length >= len);

export class CommentForm extends Component {
    constructor(props) {
        super(props)

        this.state = {
            isModalOpen: false
        };

        this.toggleModal = this.toggleModal.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleModal() {
        this.setState({ isModalOpen: !this.state.isModalOpen });
    }

    handleSubmit(values) {
        this.toggleModal();
       // alert('Current State is: ' + JSON.stringify(values));
       this.props.postComment(this.props.dishId, values.rating, values.author, values.comment);
    }

    render() {
        return(
            <div>
                <Button outline onClick={this.toggleModal}><span className="fa fa-pencil fa-lg"></span> Submit Comment</Button>

                <Modal isOpen={this.state.isModalOpen} toggle={this.toggleModal}>
                    <ModalHeader toggle={this.toggleModal}>Submit Comment</ModalHeader>
                    <ModalBody>
                        <LocalForm onSubmit={(values) => this.handleSubmit(values)}>
                            <Row  className="form-group">
                                <Label for="rating" md={12}>Rating</Label>
                                <Col  md={12}>
                                    <Control.select defaultValue="5" model=".rating" id="rating" name="rating" className="form-control" >
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </Control.select>
                                </Col>
                            </Row>
                            <Row className="form-group">
                                <Label htmlFor="author"  md={12}>Your Name</Label>
                                <Col  md={12}>
                                    <Control.text model=".author" id="author" name="author" placeholder="Author" className="form-control" validators={{ required, minLength: minLength(3), maxLength: maxLength(15) }} />
                                    <Errors className="text-danger" model=".author" show="touched" messages={{ required: 'Required', minLength: 'Must be greater than 3 characters', maxLength: 'Must be 15 charaters or less' }} />
                                </Col>
                            </Row>

                            <Row className="form-group">
                                <Label htmlFor="feedback"  md={12}>Your feedback</Label>
                                <Col  md={12}>
                                    <Control.textarea model=".comment" id="comment" name="comment" resize="none" rows="6" className="form-control" validators={{ required }} />
                                    <Errors className="text-danger" model=".comment" show="touched" messages={{ required: 'Required' }} />
                                </Col>
                            </Row>

                            <Button type="submit" value="submit" color="primary">Submit</Button>
                        </LocalForm>
                    </ModalBody>
                </Modal>

            </div>
        );
    }
}