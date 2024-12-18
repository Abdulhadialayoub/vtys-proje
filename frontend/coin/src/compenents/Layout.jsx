import React from "react";
import '../Layout.css'
import Sidebar from "./SideBar";
import RightBar from "./RightBar";
import logo from '../assets/logo2.png'

const Layout = ({ children }) => {
  return (
    <div className="layout">
          <div className="sidebar left">
              <img src={logo} className='logo'></img>
              <Sidebar />
      </div>
      <div className="content">{children}</div>
          <div className="sidebar right">
              <RightBar/>
      </div>
    </div>
  );
};

export default Layout;