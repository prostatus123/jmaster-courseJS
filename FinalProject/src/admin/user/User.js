import React, { Component } from 'react';
import { Link } from 'react-router-dom';
// import AddUser from './AddCategory';

// import AddUser from './user/AddUser';
import AddUser from './AddUser';


export default class User extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            responseDTO: {
                data: []
            },
            searchDTO: { length: 10, start: 0, order: [{ column: 0, dir: "desc" }], columns: [{ data: "id" }] }
        }
    }

    componentDidMount() {
        this.loadUser()
    }

    reset = () => {
        let searchDTO = this.state.searchDTO;
        searchDTO.start = 0;//reset ve 0
        this.setState({ searchDTO })

        this.setState({
            responseDTO: {
                data: []
            }
        })

        this.loadUser()
    }

    loadMore = () => {
        let searchDTO = this.state.searchDTO;
        searchDTO.start = searchDTO.start + searchDTO.length;//tang start len
        this.setState({ searchDTO })

        this.loadUser()
    }

    loadUser = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const SEARCH_USER_URL = BASE_URL + "/admin/accounts"

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(SEARCH_USER_URL,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Basic " + token
                    },
                    body: JSON.stringify(this.state.searchDTO)
                }
            )

            if (resp.ok) {
                let responseDTO = await resp.json()

                let oldResponseDTO = this.state.responseDTO;
                oldResponseDTO.data = oldResponseDTO.data.concat(responseDTO.data)

                this.setState({ responseDTO: oldResponseDTO })
            }
        } catch (err) {
            console.log(err)
        }
    }

    render() {
        return <div>
            <AddUser reload={this.reset} />
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>address</th>
                    <th>roles</th>
                    <th>Action</th>
                </tr>

                {this.state.responseDTO.data.map(user => {
                    return <RowItem user={user} reset={this.reset} />
                })}
            </table>
            <button onClick={this.loadMore}>Load more</button>
        </div>
    }
}

class RowItem extends Component {
    deleteItem = async (id) => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const DELETE_USER_URL = BASE_URL + "/admin/user/delete?id=" + id

        try {
            let token = localStorage.getItem("token")
            console.log(token);
            
            let resp = await fetch(DELETE_USER_URL,
                {
                    method: "DELETE",
                    headers: {
                        "Authorization": "Basic " + token,
                    },
                }
            )

            if (resp.ok) {
                this.props.reset()
            }
        } catch (err) {
            console.log(err)
        }
    }

    render() {
        return <tr>
            <td>{this.props.user.id}</td>
            <td>{this.props.user.name}</td>
            <td>{this.props.user.phone}</td>
            <td>{this.props.user.address}</td>
            <td>{this.props.user.roles}</td>
            <td><button onClick={() => this.deleteItem(this.props.user.id)}>Delete</button>
                <Link to={'/admin/user/edit/' + this.props.user.id}>Edit</Link>
            </td>
        </tr>
    }
}