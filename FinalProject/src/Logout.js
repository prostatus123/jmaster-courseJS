import React, { Component } from 'react';
import { withRouter } from 'react-router';

class Logout extends Component {

    logout = async () => {
        localStorage.removeItem("token")
        localStorage.removeItem("user")
        const { history } = this.props;
        history.replace("/login")
    }

    render() {
        return <button onClick={this.logout}>Logout</button>
    }
}

export default withRouter(Logout)