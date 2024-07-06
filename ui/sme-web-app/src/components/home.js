// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LeftMenu from './leftmenu';
import Employee from './employee';
import Vendor from './vendor';
import Email from './emails';

const Home = () => {
    return (
        <Router>
            <div className="d-flex">
                <LeftMenu />
                <div style={{ width: '80%', padding: '20px' }}>
                    <Switch>
                        <Route path="/employees" component={Employee} />
                        <Route path="/vendors" component={Vendor} />
                        <Route path="/emails" component={Email} />
                    </Switch>
                </div>
            </div>
        </Router>
    );
};

export default Home;
