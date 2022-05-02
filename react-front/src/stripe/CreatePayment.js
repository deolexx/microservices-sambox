import React from "react";
import axios from "axios";

class CreatePayment extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {

        axios.get('http://localhost:8000/stripe-integration/secret', {params: {amount: this.state.value}}).then((response) => {
            this.props.childToParent(response.data.client_secret)

        });

        alert('Создан платеж: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    <div> Amount in USD:
                    </div>
                    <input type="text" placeholder="0" value={this.state.value} onChange={this.handleChange}/>
                </label>
                <button type="submit">CreateIntent</button>
                {/*<input type="submit" value="Отправить"/>*/}
            </form>
        );
    }
}

export default CreatePayment