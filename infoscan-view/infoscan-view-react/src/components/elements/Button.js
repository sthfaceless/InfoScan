import React, {Component} from 'react';

class Button extends Component{

    constructor(props) {
        super(props);
        this.state = {
            title: props.title,
            callback: props.callback
        }
    }

    render() {
        return (
            <button className="btn" onClick={this.state.callback}>
                {this.state.title}
            </button>
        );
    }

}

export default Button;