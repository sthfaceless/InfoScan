import React from 'react';
import ContentItem from "js/components/simple/support/ContentItem";
import 'css/support/content-list.css';

class Content extends React.Component{
    render() {
        return (
            <div className="row">
                <h4 className="grey-text text-darken-2">Содержание</h4>
                <div className="content_list">
                    {this.props.items.map(item => (<ContentItem key={item.name} name={item.name} items={item.questions}/>))}
                </div>
            </div>
        );
    }
}

export default Content;