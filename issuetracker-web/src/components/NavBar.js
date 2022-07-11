import React from 'react';
import { Layout, Menu } from 'antd';
import { Link } from 'react-router-dom';
const { Header } = Layout;


function NavBar() {
  return (
     <Header
      style={{
        position: 'fixed',
        zIndex: 1,
        width: '100%',
      }}
    >
      <div className="logo" />
      <Menu theme="dark" mode="horizontal">
            <Menu.Item>
              <Link to="/">Home</Link>
            </Menu.Item> 
            <Menu.Item>
              <Link to="/tracker">Tracker</Link>
            </Menu.Item> 
            <Menu.Item>
              <Link to="/about">About</Link>
            </Menu.Item>      
      </Menu>
    </Header>
  );
}

export default NavBar;
