import React from 'react';
import {
  BrowserRouter as Router,
  Route,
} from "react-router-dom";
import Tracker from './Tracker';
import About from './About';



const Component = () => {
    return(
        <Router>
            <Route exact path="/" component= {Tracker} />
            <Route path = "/About" component = {About} />
        </Router>
    );
};

export default Component;
