import React from 'react';
import { Card, CardImg, CardImgOverlay, CardTitle, Breadcrumb, BreadcrumbItem } from 'reactstrap';
import { Link } from 'react-router-dom';
import { Loading } from './LoadingComponent';
import { baseUrl } from '../shared/baseUrl';

//this function is passed into the functional Menu component below
function RenderMenuItem({dish}) {
    return(
        <Card>
            {/* since we are no longer using the onClick functions in MenuComponent, we'll instead use Link to route to the selected dish:  */}
            <Link to={`/menu/${dish.id}`}>
                {/*fetching image from server directly using url and json key*/}
                <CardImg width="100%" src={baseUrl + dish.image} alt={dish.name}></CardImg>
                <CardImgOverlay>
                    <CardTitle style={{color:'floralwhite'}}>{dish.name}</CardTitle>
                </CardImgOverlay>
            </Link>
        </Card>
    );
}

const Menu = (props) => {
    console.log('Menu component render invoked.');
    const menu = props.dishes.dishes.map((dish) => {
        return (
            <div key={dish.id} className = "col-12 col-md-5 m-1">
                <RenderMenuItem dish = {dish}/>
            </div>
        );
    });

    if (props.dishes.isLoading) {
        return (
            <div className="container">
                <div className="row">
                    <Loading />
                </div>
            </div>
        );
    }

    else if (props.dishes.errMess) {
        return (
            <div className="container">
                <div className="row">
                    <h4>{props.dishes.errMess}</h4>
                </div>
            </div>
        );
    }

    else {
        return (
            <div className="container">
                <div className="row">
                    <Breadcrumb>
                        <BreadcrumbItem><Link to='/home'>Home</Link></BreadcrumbItem>
                        <BreadcrumbItem active>Menu</BreadcrumbItem>
                    </Breadcrumb>
                    <div className="col-12">
                        <h3>Simply the best Menu in town! Click on your favorite to subscribe.</h3>
                        <hr/>
                    </div>
                </div>
                <div className="row">
                    {menu}
                </div>
            </div>
        );
    }
}


export default Menu;