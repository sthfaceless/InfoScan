import React, {Component} from 'react';
import 'css/profile/profile.information.css';
import InfoCard from "js/components/simple/profile/InfoCard";

class Information extends Component{

    render() {
        return (
            <div className="information">
                {this.props.info.infoCards.map(card => (<InfoCard key={card.name} name={card.name} elements={card.elements}/>))}
            </div>
        );
    }
}

export default Information;