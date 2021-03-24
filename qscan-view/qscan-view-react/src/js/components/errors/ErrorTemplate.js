import React from 'react';
import {Link} from "react-router-dom";
import 'css/error-template.css';

class ErrorTemplate extends React.Component{
    render() {
        return (
            <div className="error-template">
                <div className="content container">
                    <div className="error-card white">
                        <h1 className={"error-code "+this.props.titleClasses}>
                            {this.props.code}
                        </h1>
                        <h4 className={"describe "+this.props.describeClasses}>
                            {this.props.describe}
                        </h4>
                        <Link className='back grey darken-3 white-text' to='/'>На главную</Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default ErrorTemplate;