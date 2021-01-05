import React from 'react';

export default class AddCategory extends React.Component {
    constructor(props) {
        super(props)
        this.state = {

            name: ''

        }
    }
    
    //can bind this o trong ham

    handleChangeName = (evt) => {
        this.setState({
            name: evt.target.value
        })
    }

    addCategory = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const ADD_CATEGORY_URL = BASE_URL + "/admin/category/add"

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(ADD_CATEGORY_URL,
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
                <input type="text" onChange={this.handleChangeName} />
            </div>
            <button onClick={this.addCategory}>Add</button>
        </div>
    }

}