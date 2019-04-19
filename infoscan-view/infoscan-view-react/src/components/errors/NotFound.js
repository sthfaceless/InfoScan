import React, {Component} from 'react';

class NotFound extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="not-found m12">
                        <h1>Not found</h1>
                    </div>
                </div>
            </div>
        );
    }

}

export default NotFound;