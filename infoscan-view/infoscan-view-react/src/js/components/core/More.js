import React, {Component} from 'react';
import 'css/core/more.css';

class More extends Component{

    constructor(props){
        super(props);
    }

    render() {
        return (
            <div className="more">
                <div onClick={this.props.action} className="more-btn grey darken-3 white-text">Больше</div>
            </div>
        );
    }
}

export default More;