import './App.css';
import React from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import CreateAccount from './CreateAccount';
import DeployAccount from './DeployAccount';


function App() {
  return (
      <Router>
          <div className="App">
              <nav>
                  <ul>
                      <li>
                          <Link to="/">Create Account</Link>
                      </li>
                      <li>
                          <Link to="/deploy">Deploy Account</Link>
                      </li>
                  </ul>
              </nav>

              <Route path="/" exact component={CreateAccount} />
              <Route path="/deploy" component={DeployAccount} />
          </div>
      </Router>


    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    //
    //   <hr/>
    //   <CreateAccount/>
    // </div>
  );
}

export default App;
