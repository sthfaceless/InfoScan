import React from 'react';
import 'css/core/social-network.css';

class SocialNetwork extends React.Component{
    render() {
        const {socialNetwork} = this.props;
        return (
            <a href={socialNetwork.path} className="element grey darken-2 white-text">
                <div className="sn-logo">
                    <img src={'/resources/images/'+socialNetwork.logo+'.svg'}/>
                </div>
                <div className="sn-name">
                    {socialNetwork.name}
                </div>
            </a>
        );
    }
}

export default SocialNetwork;