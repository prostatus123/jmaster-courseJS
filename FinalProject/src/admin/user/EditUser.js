import React from 'react';
import { withRouter } from 'react-router-dom';

class EditUser extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            phone: '',
            address: '',

        }

    }

    componentDidMount() {
        this.loadUser()
    }
    //can bind this o trong ham
    handleChangeName = (evt) => {
        this.setState({
            name: evt.target.value
        })
    }
    handleChangePhone = (evt) => {
        this.setState({
            phone: evt.target.value
        })
    }
    handleChangeAdress = (evt) => {
        this.setState({
            address: evt.target.value
        })
    }


    loadUser = async () => {
        ///doc id
        const { match } = this.props;
        const id = match.params.id

        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const UPDATE_USER_URL = BASE_URL + "/admin/user/" + id

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(UPDATE_USER_URL,
                {
                    method: "GET",
                    headers: {
                        " Authorization": "basic " + token,
                        "Content-Type": "application/json"
                    },
                }
            )

            if (resp.ok) {
                let user = await resp.json()
                this.setState(user)
            }
        } catch (err) {
            console.log(err)
        }
    }

    submit = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const UPDATE_USER_URL = BASE_URL + "/admin/user/update"

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(UPDATE_USER_URL,
                {
                    method: "PUT",
                    headers: {
                        "Authorization": "Basic " + token,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(this.state)
                }
            )

            if (resp.ok) {
                this.setState({ success: "SUCCESS" })
                const { history } = this.props
                history.goBack()
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
                <input type="text" value={this.state.name} onChange={this.handleChangeName} />
            </div>
            <div>
                <label>Name:</label>
                <input type="text" value={this.state.phone} onChange={this.handleChangePhone} />
            </div>
            <div>
                <label>Name:</label>
                <input type="text" value={this.state.address} onChange={this.handleChangeAdress} />
            </div>
            <button onClick={this.submit}>Update</button>
        </div>
    }

}


export default withRouter(EditUser)