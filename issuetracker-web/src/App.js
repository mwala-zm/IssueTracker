import React from 'react';
import { Layout } from 'antd';
import { Route, Routes } from 'react-router-dom';
import FooterNav from './components/FooterNav';
import NavBar from './components/NavBar';
import Home from './pages/Home';
import About from './pages/About';
import Login from './pages/Login';
import Tracker from './pages/Tracker';
import ViewInspection from './pages/ViewInspection';
const { Content } = Layout;

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
  }

  render() {
    return(
        <>
           <Layout>
              <NavBar/>
              <Content className="site-layout"
                style={{
                  padding: '0 50px',
                  marginTop: 64,
                }}
              >
                <br/>
                <div
                  className="site-layout-background"
                  style={{
                    padding: 24,
                    minHeight: 380,
                  }}
                >
                  <Routes>
                    <Route path ="/" element={<Login state={this.state}/>} exact/>
                    <Route path ="/home" element={<Home state={this.state}/>}/>
                    <Route path ="/about" element={About}/>
                    <Route path ="/tracker" element={<Tracker state={this.state}/>}/>
                    <Route path ="/view_inspection/:id" element={<ViewInspection state={this.state}/>}/>
                  </Routes>
                </div>
              </Content>
              <FooterNav/>
            </Layout>
        </>
    );
  }
}

export default App;
