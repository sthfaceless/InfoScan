import React from 'react';
import 'css/support/support-answers-item.css';

class AnswersItem extends React.Component{
    render() {
        return (
            <div className="question">
                <h6 className="grey-text" id="forgot_password">{this.props.question}</h6>
                <div className="help grey-text text-darken-2">
                    {this.props.answer}
                </div>
            </div>
        );
    }
}

export default AnswersItem;