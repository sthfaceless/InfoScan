import React, {Component} from 'react';
import BadRequest from "../../errors/BadRequest";

class PersonInformation extends Component{

    constructor(props) {
        super(props);
        this.state = {
            id: props.match.params.id
        }
    }

    render() {
        if(!/^\d+$/.test(this.state.id))
            return <BadRequest/>;
        return (
            <div>
                PersonInformation
            </div>
        );
    }

}

export default PersonInformation;