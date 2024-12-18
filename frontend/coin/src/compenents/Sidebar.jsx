import { CardMedia } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";
import { logout } from "../server/api";


const Sidebar = () => {
  const navigate = useNavigate();

  const isAuthenticated = () => {
    return localStorage.getItem("isAuthenticated") === "true";
  };
  const userLogout = async() => {
    try {
       const response = await logout();
        console.log('Başarılı Giriş:', response);
         localStorage.setItem("isAuthenticated", "false");
          navigate("/login");
   
     } catch (error) {
       console.error('Hata:', error);
     }
  }

  return (
    
      <div className="sidebar">
          
      {isAuthenticated() ? (
        <>
          <ul>
            <li onClick={() => navigate("/")}>Anasayfa</li>
            <li onClick={() => navigate("/favorites")}>Favoriler</li>
            <li onClick={() => navigate("/coins")}>Coin Listesi</li>
            <li onClick={() => navigate("/users")}>Kullanıcı</li>
            <li onClick={userLogout}>Çıkış Yap</li>
          </ul>
        </>
      ) : (
        <>
          <ul>
            <li onClick={() => navigate("/")}>Anasayfa</li>
            <li onClick={() => navigate("/coins")}>Coin Listesi</li>
            <li onClick={() => navigate("/login")}>Giriş Yap</li>
            <li onClick={() => navigate("/register")}>Kayıt Ol</li>
          </ul>
        </>
      )}
      <footer className="footer">
        <p>Tüm hakları saklıdır ©2024</p>
      </footer>
    </div>
  );
};

export default Sidebar;
