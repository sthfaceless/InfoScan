import React, {Component} from 'react';
import {connect} from 'react-redux';
import 'css/core/modal.css';
import {closeModal} from "js/store/actions/modalActions";

class Modal extends Component{
    constructor(props) {
        super(props);
        console.log('Modal initiated');
    }

    render() {
        console.log('model rendered!');
        return this.props.modal.isOpen &&
            (<div className="modal-cover">
                <div className="modal-window">
                    <div className="modal-head blue lighten-2 white-text">
                        {this.props.modal.options.title}
                    </div>
                    <div className="modal-body">
                        {this.props.modal.content}
                    </div>
                    <div className="modal-footer">
                        <div onClick={() => this.props.dispatch(closeModal())} className="close-btn grey lighten-2 grey-text text-darken-3">Закрыть</div>
                        <div onClick={() => this.props.dispatch(closeModal())} className="active-btn green white-text">Активировать</div>
                    </div>
                </div>
            </div>);
    }

}

const mapStateToProps = state => ({
    modal: state.modal
});

export default connect(mapStateToProps)(Modal);