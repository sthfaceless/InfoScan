import React from 'react';
import 'css/profile/info-element.css';

class InfoElement extends React.Component{
    render() {
        return (
            <div className="info-element">
                <div className="name">{this.props.name}</div>
                <div className="content grey-text text-darken-3">{this.props.value}</div>
            </div>
        );
    }
}

export default InfoElement;