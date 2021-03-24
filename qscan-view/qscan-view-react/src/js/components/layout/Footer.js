import React, {Component} from 'react';
import 'css/footer.css';

class Footer extends Component{

    render() {
        return (
            <footer className="grey darken-3">
                <div className="container">
                    <div className="footer-row">
                        <div className="left grey-text">
                            Copyright © 2019 InfoScan.
                        </div>
                        <div className="right">
                            <a href="https://vk.com/spaceouter"><img src="/resources/images/vk.svg"/></a>
                            <a href="https://github.com/space0uter"><img src="/resources/images/github.svg"/></a>
                        </div>
                    </div>
                </div>
            </footer>
        );
    }

}

export default Footer;