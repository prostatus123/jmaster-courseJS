import './App.css';

import React from 'react';
import { BrowserRouter as Router, Link, Route, Switch, Redirect } from 'react-router-dom';

import Admin from './admin/Admin';
import Login from './Login';

export default function App() {
  return (//JSX
    <Router>
      <Switch>
        <Route path="/admin"
          render={() =>
            localStorage.getItem("token") ?
              <Admin />
              : <Redirect to="/login" />
          }
        />
        <PrivateRoute path="/admin">
          <Admin />
        </PrivateRoute>
        <Route path="/">
          <Client />
        </Route>
      </Switch>
    </Router>
  );
}

function PrivateRoute({ children, ...rest }) {
  return (
    <Route {...rest}
      render={() =>
        localStorage.getItem("token") ? (
          children
        ) : (
            <Redirect to="/login" />
          )
      }
    />
  );
}

function Client() {
  return <div>
    <h1>Welcome to System!</h1>
    <MenuClient />
    <ClientRoute />
  </div>
}
function ClientRoute() {
  return <Switch>
    <Route path="/login">
      <Login />
    </Route>
  </Switch>
}
function MenuClient() {
  return <ul>
    <li><Link to="/">Home</Link></li>
    <li><Link to="/login">Login</Link></li>
  </ul>
}