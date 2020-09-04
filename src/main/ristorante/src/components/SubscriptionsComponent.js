import React from 'react';
import { Button, Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle} from 'reactstrap';
import { Loading } from './LoadingComponent.js';
import { baseUrl } from '../shared/baseUrl';

function RenderCard({item, isLoading, errMess, unsubscribe, authentication}){

    const customerid = authentication.customerid;
    const jwt = authentication.jwt;

    if (isLoading){
        return(
            <Loading />
        );
    }

    else if (errMess) {
        return (
            <h4> {errMess} </h4>
        );
    }

    else {
        return(
            <div>
                <Card>
                    <CardImg src={baseUrl + item.image} alt={item.name} />
                    <CardBody>
                        <CardTitle>{item.name}</CardTitle>
                        {/* if item designation is not null, then render: */}
                        {item.designation ? <CardSubtitle>{item.designation}</CardSubtitle> : null}
                        <CardText>{item.description}</CardText>
                    </CardBody>
                </Card>
                <div>
                    <Button onClick={() => handleUnsubscribe(unsubscribe, customerid, jwt, item.id)}>Unsubscribe</Button>
                </div>
            </div>
        );
    }
}

function handleUnsubscribe(unsubscribe, customerid, jwt, dishid){
    unsubscribe(jwt,customerid, dishid);
}

function Subscriptions(props) {
    console.log(props);
    console.log(props.subscriptions.subscriptions);
    return (
        <div className="container">
                {
                    (props.authentication.isAuthenticated && ((typeof props.subscriptions.subscriptions) !== "undefined")) ? 
                        /*(<Redirect to={{ pathname : "/subscriptions"  }}/>) */
                        (
                             
                            <RenderSubscriptionItem unsubscribe = {props.unsubscribe} authentication = {props.authentication} subscriptions = {props.subscriptions.subscriptions} others = {props.subscriptions}/>
                                
                        )
                        
                        : 
                        
                        (
                            <div style={{padding: "40px"}}>
                                <h3>You need to Login to see your subscriptions.</h3>
                            </div>
                        )
                }
        </div>
    );
}

function RenderSubscriptionItem({unsubscribe, authentication, subscriptions, others}) {
    
    if (subscriptions.length > 0) {
        let subs = subscriptions.map((subscription) => { 
            return(
                <div key={subscription.id} className = "col-6 col-md-5 m-1" style={{padding: "40px", display:"inline-block"}}>
                    <RenderCard unsubscribe = {unsubscribe} authentication = {authentication} item={subscription} isLoading={others.isLoading} errMess={others.errMess}/>
                </div>
            )
        });
        return subs;
    }

    else {
        return(
            <div style={{padding: "40px"}}>
                <h3>Visit our menu page and subscribe to the best dishes in town, NOW!</h3>
            </div>
        )
    }
    
    
    
}

export default Subscriptions;