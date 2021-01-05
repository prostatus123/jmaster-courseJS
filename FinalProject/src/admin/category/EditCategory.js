import React from 'react';
import { withRouter } from 'react-router-dom';

class EditCategory extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            name: ''
        }

    }

    componentDidMount() {
        this.loadCategory()
    }
    //can bind this o trong ham
    handleChangeName = (evt) => {
        this.setState({
            name: evt.target.value
        })
    }

    loadCategory = async () => {
        ///doc id
        const { match } = this.props;
        const id = match.params.id

        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const SEARCH_CATEGORY_URL = BASE_URL + "/category/" + id

        try {
            let resp = await fetch(SEARCH_CATEGORY_URL,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json"
                    },
                }
            )

            if (resp.ok) {
                let category = await resp.json()
                this.setState(category)
            }
        } catch (err) {
            console.log(err)
        }
    }

    submit = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const UPDATE_CATEGORY_URL = BASE_URL + "/admin/category/update"

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(UPDATE_CATEGORY_URL,
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
            <button onClick={this.submit}>Update</button>
        </div>
    }

}


export default withRouter(EditCategory)