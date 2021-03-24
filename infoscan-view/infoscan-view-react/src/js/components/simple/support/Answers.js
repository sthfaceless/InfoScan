import React from 'react';
import AnswersItem from "js/components/simple/support/AnswersItem";
import 'css/support/support-answers.css';

class Answers extends React.Component{
    render() {
        return (
            <div className="row">
                <h4 className="grey-text text-darken-2">Ответы на вопросы</h4>
                <div className="support_content">
                    {this.props.items.map(item => (
                        <div key={item.name} className="question-group">
                            <span className="head grey-text text-darken-2">{item.name}</span>
                            {item.questions.map(question => (
                                <AnswersItem key={question.id} question={question.question} answer={question.answer}/>
                            ))}
                        </div>
                    ))}
                </div>
            </div>
        );
    }
}

export default Answers;