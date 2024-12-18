import React, { useState, useEffect } from "react";
import { getUser } from "../server/api";
import { useNavigate } from "react-router-dom";
import profil from '../assets/man.png'

const UserPage = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await getUser(userId);
        setUser(response.data.data);
      } catch (error) {
        console.error("Error fetching user:", error);
      }
    };

    fetchUser();
  }, [userId]);

  if (!user) {
    return <div>Yükleniyor...</div>;
  }

    return (
        <>
            <img src={profil} alt="" className="logo2"></img>
            <h2 className="title1">Profil Bilgilerim</h2>
            <div className="user-page">
      
      <p><strong>İd:</strong> {user.id}</p>
      <p><strong>İsim:</strong> {user.name}</p>
      <p><strong>Email:</strong> {user.email}</p>
    </div>
      </>
    
  );
};

export default UserPage;
