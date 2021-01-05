import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import AddCategory from './AddCategory';

export default class Category extends React.Component {
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
        this.loadCategory()
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

        this.loadCategory()
    }

    loadMore = () => {
        let searchDTO = this.state.searchDTO;
        searchDTO.start = searchDTO.start + searchDTO.length;//tang start len
        this.setState({ searchDTO })

        this.loadCategory()
    }

    loadCategory = async () => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const SEARCH_CATEGORY_URL = BASE_URL + "/category/search"

        try {
            let resp = await fetch(SEARCH_CATEGORY_URL,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
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
            <AddCategory reload={this.reset} />
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>

                {this.state.responseDTO.data.map(cate => {
                    return <RowItem cate={cate} reset={this.reset} />
                })}
            </table>
            <button onClick={this.loadMore}>Load more</button>
        </div>
    }

}
class RowItem extends Component {
    deleteItem = async (id) => {
        const BASE_URL = "https://learn-api.jmaster.io:8443/api";
        const DELETE_CATEGORY_URL = BASE_URL + "/admin/category/delete?id=" + id

        try {
            let token = localStorage.getItem("token")
            let resp = await fetch(DELETE_CATEGORY_URL,
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
            <td>{this.props.cate.id}</td>
            <td>{this.props.cate.name}</td>
            <td><button onClick={() => this.deleteItem(this.props.cate.id)}>Delete</button>
                <Link to={'/admin/category/edit/' + this.props.cate.id}>Edit</Link>
            </td>
        </tr>
    }
}