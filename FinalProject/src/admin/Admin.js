import React, { Component } from 'react';
import { Link, Route, Switch, useLocation } from 'react-router-dom';

import Logout from '../Logout';
import Category from './category/Category';
import AddPost from './post/AddPost';
import EditCategory from './category/EditCategory';
import User from './user/User';
import EditUser from './user/EditUser';


export default class Admin extends Component {
    constructor(props) {
        super(props)
        this.state = { user: {} }
    }

    componentDidMount() {
        this.getCurrentUser()
    }

    getCurrentUser = () => {
        let user = JSON.parse(localStorage.getItem("user"))
        if (user) {
            this.setState({ user })
        }
    }

    render() {
        return <div>
            <h1>Welcome {this.state.user.name} <Logout /></h1>
            <AdminMenu />
            <AdminRoute />
        </div>
    }
}

function AdminRoute() {
    return <Switch>
        <Route path="/admin" exact>
            <div>Dashboard Admin</div>
        </Route>
        <Route path="/admin/user">
            <div>User Search</div>
            <User />
        </Route>
        <Route path="/admin/user/edit/:id">
            <EditUser />
        </Route>

        <Route path="/admin/category/edit/:id">
            <EditCategory />
        </Route>
        <Route path="/admin/category" exact>
            <Category />
        </Route>

        <Route path="/admin/post/add" exact>
            <AddPost />
        </Route>
        <Route path="*">
            <NotFound />
        </Route>
    </Switch>
}
function AdminMenu() {
    return <ul>
        <li><Link to="/admin/user">User</Link></li>
        <li><Link to="/admin/category">Category</Link></li>
        <li><Link to="/admin/post/add">Add Post</Link></li>
    </ul>
}

function NotFound() {
    let location = useLocation();
    return (
        <div>
            <h3>
                No match for <code>{location.pathname}</code>
            </h3>
        </div>
    );
}