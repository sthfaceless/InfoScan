import React from 'react';
import 'css/orders/radio-gender.css';

class RadioGender extends React.Component{
    render() {
        return (
            <div className={this.props.class}>
                <span className="name grey-text">{this.props.name}</span>
                <div className="right">
                    {this.props.items.map(item => (
                        <label key={item.value}>
                            <input onChange={(event) => this.props.onUpdate(event.target.value)} name={this.props.fieldName} value={item.value} type="radio" />
                            <span>{item.name}</span>
                        </label>
                    ))}
                </div>
            </div>
        );
    }
}

export default RadioGender;