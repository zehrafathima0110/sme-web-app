import React from 'react';
import { Link } from 'react-router-dom';

const LeftMenu = () => {
    return (
        <div style={{ width: '200px', background: '#f4f4f4', padding: '20px', height: '100vh' }}>
            <h3>Menu</h3>
            <ul className="nav flex-column">
                <li className="nav-item mb-2">
                    <Link to="/employees" className="nav-link">Employees</Link>
                </li>
                <li className="nav-item mb-2">
                    <Link to="/vendors" className="nav-link">Vendors</Link>
                </li>
                <li>
                    <Link to="/emails" className="nav-link"> Emails</Link>
                </li>
            </ul>
        </div>
    );
};

export default LeftMenu;
