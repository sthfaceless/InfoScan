import React, {Component} from 'react';
import ErrorTemplate from "js/components/errors/ErrorTemplate";

class NotFound extends Component{

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <ErrorTemplate code='404' describe='Страница не найдена'
                           titleClasses='blue-text text-lighten-2' describeClasses='blue-text text-lighten-2'/>
        );
    }

}

export default NotFound;