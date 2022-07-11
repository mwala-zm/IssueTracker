import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'antd/dist/antd.min.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import {BrowserRouter as Router}  from "react-router-dom";
const AppContext = React.createContext();

ReactDOM.render(
  <AppContext.Provider >
    <React.StrictMode>
    <Router>
      <App />
    </Router>
  </React.StrictMode>
  </AppContext.Provider>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
