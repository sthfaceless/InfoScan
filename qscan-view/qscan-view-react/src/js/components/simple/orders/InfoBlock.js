import React from 'react';
import 'css/orders/info-block.css';

class InfoBlock extends React.Component{
    render() {
        return (
            <div className="info-block grey lighten-4">
                <div className="info-name grey darken-3 white-text">
                    {this.props.name}
                </div>
                <div className="info-content white-text">
                    {this.props.items.map(item => (
                        <div key={item} className="may-value grey darken-2">{item}</div>
                    ))}
                </div>
            </div>
        );
    }
}

export default InfoBlock;