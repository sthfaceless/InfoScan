import React from 'react';
import 'css/core/search-bar.css';
import SearchFilters from "js/components/core/SearchFilters";

class SearchBar extends React.Component{

    render() {
        return (
            <div className="search">
                <div className="search-bar">
                    <input placeholder="Поиск..." type="text"/>
                    <div className="find">Найти</div>
                </div>
                <SearchFilters filters={this.props.searchFilters}/>
            </div>
        );
    }
}

export default SearchBar;