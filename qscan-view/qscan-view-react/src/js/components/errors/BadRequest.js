import React, {Component} from 'react';
import ErrorTemplate from "js/components/errors/ErrorTemplate";

class BadRequest extends Component{
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <ErrorTemplate code='400' describe='Неправильный запрос'
                           titleClasses='orange-text text-darken-2' describeClasses='oragne-text text-lighten-2'/>
        );
    }

}

export default BadRequest;