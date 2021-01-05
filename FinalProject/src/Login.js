import React, { Component } from 'react';
import { withRouter } from 'react-router';

class Login extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username: '',
            password: '',
            error: false
        }
    }
    login = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const MEMBER_URL = BASE_URL + "/member/me"
        try {
            let token = btoa(this.state.username + ":" + this.state.password)
            let resp = await fetch(MEMBER_URL,
                {
                    method: "GET",
                    headers: {
                        "Authorization": "Basic " + token,
                        "Content-Type": "application/json"
                    },
                }
            )

            if (resp.ok) {
                let userDTO = await resp.json() 
                console.log(userDTO)
                localStorage.setItem("token", token)
                localStorage.setItem("user", JSON.stringify(userDTO))
                const { history } = this.props;
                history.replace("/admin")
            } else {
                this.setState({
                    error: true
                })
            }
        } catch (err) {
            console.log(err)
        }
    }

    render() {
        return <div>
            <div>
                <label>Username:</label>
                <input type="text" onChange={(event) => {
                    this.setState({
                        username: event.target.value
                    })
                }} />
            </div>
            <div>
                <label>Username:</label>
                <input type="password" onChange={(event) => {
                    this.setState({
                        password: event.target.value
                    })
                }} />
            </div>
            {this.state.error && <div>Tai khoan hoac mat khau sai!</div>}
            <div>
                <button onClick={this.login}>Login</button>
            </div>
        </div>
    }
}

export default withRouter(Login)