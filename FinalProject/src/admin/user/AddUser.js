import React from 'react';

export default class AddUser extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            password: '',
            phone: '',
            address: '',
            roles: [],
        }
    }
    //can bind this o trong ham
    setParams = (event) => {
        let name = event.target.name
        let value = event.target.value
        this.setState({
            [name]:value
        })
    }

    addUser = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api/";
        const ADD_USER_URL = BASE_URL + "admin/user/add"

        try {
            let token = localStorage.getItem("token")
            console.log(token)
            let resp = await fetch(ADD_USER_URL,
                {
                    method: "POST",
                    headers: {
                        "Authorization": "Basic " + token,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(this.state)
                }
            )

            console.log(resp)
            if (resp.ok) {
                let responseDTO = await resp.json()
                console.log(responseDTO)
                this.setState({ success: "SUCCESS" })
                this.props.reload()
            }
        } catch (err) {
            console.log(err)
        }
    }

    render() {
        //trong onclick phai truyen vao 1 ham
        return <div>
            <p>{this.state.success}</p>
            <div>
                <label>Name:</label>
                <input type="text"  name="name" onChange={this.setParams} />
                <label>Password</label>
                <input type="text"  name="password" onChange={this.setParams} />
                <label>phone</label>
                <input type="text"  name="phone" onChange={this.setParams} />
                <label>Address</label>
                <input type="text"  name="address" onChange={this.setParams} />
                <select  name="roles">
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    <option value="ROLE_MEMBER">ROLE_MEMBER</option>
                </select>
            </div>
            <button onClick={this.addUser}>Add</button>
        </div>
    }

}