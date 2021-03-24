import React from 'react';

class QiwiContentItem extends React.Component{
    render() {
        return (
            <div className={'qiwi-elem '+this.props.class}>
                                    <span className="grey-text text-darken-3">
                                        <span className="num blue white-text">{this.props.step}</span>
                                        {this.props.name}
                                    </span>
                <input placeholder={this.props.placeholder} type="text"/>
            </div>
        );
    }
}

export default QiwiContentItem;