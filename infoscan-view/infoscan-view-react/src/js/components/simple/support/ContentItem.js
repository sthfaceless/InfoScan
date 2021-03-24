import React from 'react';
import 'css/support/content-list-item.css';

class ContentItem extends React.Component{
    render() {
        return (
            <div className="list-item">
                <span>{this.props.name}</span>
                {this.props.items.map((item) =>
                    (<a key={item.id} href={item.id}>{item.question}</a>))}
            </div>
        );
    }
}

export default ContentItem;