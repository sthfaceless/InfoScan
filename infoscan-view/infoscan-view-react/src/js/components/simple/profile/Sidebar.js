import React, {Component} from 'react';
import 'css/profile/profile.sidebar.css';

class Sidebar extends Component{

    constructor(props) {
        super(props);
        this.state = {
            elements: this.props.elements,
        }
    }

    render() {
        return (
            <div className="sidebar">
                {this.state.elements.map((e, i) => (
                    <div key={i} onClick={() => this.props.updateWindow(i)}
                         className={'sidebar-element ' + (this.props.window === i ? 'active' : '')}>
                        {e.text}
                    </div>
                ))}
            </div>
        );
    }
}

export default Sidebar;