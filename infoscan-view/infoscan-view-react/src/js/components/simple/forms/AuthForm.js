import React, {Component} from 'react';
import 'css/auth/auth_form.css';

class AuthForm extends Component{
    render() {
        return (
            <form className="auth_form col s12 m8 l6 offset-l3 offset-m2 white grey-text">
                {this.props.title ? (<div className="title">{this.props.title}</div>) : null}
                {this.props.subtitle ? (<div className="subtitle grey-text text-lighten-1">{this.props.subtitle}</div>) : null}
                {this.props.children}
            </form>
        );
    }
}

export default AuthForm;