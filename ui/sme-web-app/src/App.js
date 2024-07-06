import React from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./components/login";
import Home from "./components/home";

const App = () => {
  return (
    <>
      <Router>
        <Route exact path="/" component={Login} />
        <Route path="/home" component={Home} />
      </Router>
    </>
  );
};

export default App;
