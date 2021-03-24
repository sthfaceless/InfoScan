import React, {Component} from 'react';
import ErrorTemplate from "js/components/errors/ErrorTemplate";

class AccessDenied extends Component{
    render() {
        return (
            <ErrorTemplate code='403' describe='Доступ запрещён'
                           titleClasses='red-text text-darken-1' describeClasses='red-text text-lighten-2'/>
        );
    }
}

export default AccessDenied;