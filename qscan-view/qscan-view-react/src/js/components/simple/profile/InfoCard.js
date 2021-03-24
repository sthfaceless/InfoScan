import React from 'react';
import 'css/profile/info-card.css';
import InfoElement from "js/components/simple/profile/InfoElement";

class InfoCard extends React.Component{
    render() {
        return (
            <div className="info-card grey-text grey lighten-4">
                <div className="head white-text grey darken-3">
                    {this.props.name}
                </div>
                {this.props.elements.map(element => (
                    <InfoElement key={element.name} name={element.name} value={element.value}/>
                ))}
            </div>
        );
    }
}

export default InfoCard;