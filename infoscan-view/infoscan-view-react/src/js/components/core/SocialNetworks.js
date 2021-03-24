import React from 'react';
import 'css/core/social-networks.css';
import SocialNetwork from "js/components/core/SocialNetwork";

class SocialNetworks extends React.Component{
    render() {
        return (
            <div className="social-networks">
                <div className="head grey-text text-darken-3">Социальные сети
                    <div className="choose-social-network grey-text text-darken-2">Добавить соцсеть</div></div>

                <div style={{display: 'none '}} className="empty grey-text">Нет информации</div>
                {this.props.networks.map(network => (
                    <SocialNetwork key={network.logo} socialNetwork={network}/>
                ))}
            </div>
        );
    }
}

export default SocialNetworks;