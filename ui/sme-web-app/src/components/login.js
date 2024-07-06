// src/components/Login.js
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const handleSubmit = (e) => {
        e.preventDefault();
        // Mock login validation
        if (username === 'admin' && password === 'password') {
            history.push('/home');
        } else {
            alert('Invalid credentials');
        }
    };

    return (
        <div >
            
            <div class="container align-items-center justify-content-center d-flex"  style={{'min-height':'75vh'}}>
                <div className="row align-items-center w-50 mx-auto">
                    
                    <div className="col">
                   
                    <h2>Login</h2>
                    <form onSubmit={handleSubmit}>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Username</label>
                        <input type="text" class="form-control" id="exampleInputEmail1"  placeholder="Enter username"
                            value={username} onChange={(e) => setUsername(e.target.value)}/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                             value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Login</button>
                    </form>

                    </div>
                    
                </div>
                </div>
        </div>
    );
};

export default Login;
