import React from 'react';
import 'css/core/search-filters.css';

class SearchFilters extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            dropdowns: {}
        };
        this.props.filters.map(filter => {
            this.state.dropdowns[filter.name] = false;
        });
    }

    render() {
        return (
            <div className="filters grey-text">
                {this.props.filters.map(filter => (
                    <div className="filter" key={filter.name}>
                        <span>{filter.name}</span>
                        {this.state.dropdowns[filter.name] ? <div className="filter-drop grey-text">
                            {filter.elements.map(element => (
                                <div className="filter-drop-elem" key={element.name}>{element.name}</div>
                            ))}
                        </div> : ""}
                    </div>))}
            </div>
        );
    }
}

export default SearchFilters;