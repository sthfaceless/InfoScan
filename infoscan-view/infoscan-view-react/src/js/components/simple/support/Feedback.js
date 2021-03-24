import React from 'react';
import 'css/support/feedback.css';

class Feedback extends React.Component{
    render() {
        return (
            <div className="row back-connect">
                <h4 className="grey-text text-darken-2">Обратная связь</h4>
                <div className="connect_suggestion grey-text text-darken-1">
                    Остались вопросы? Задайте вопрос и ответ придёт к вам на почту.
                </div>
                <div onClick={this.props.feedback} className="btn blue">Задать вопрос</div>
            </div>
        );
    }
}

export default Feedback;