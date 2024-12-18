import React, { useState } from 'react';
import '../LoginPage.css';
import { login } from '../server/api';
import { useNavigate } from "react-router-dom";
import bitcoin from '../assets/user.png'

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState(''); // Hata mesajı için state
  const navigate = useNavigate();

  const handleLogin = async () => {
    const body = {
      email,
      password,
    };

    try {
      const response = await login(body);
      console.log('Başarılı Giriş:', response);

      // Giriş başarılı olduğunda localStorage'a veri ekleyip yönlendirme yap
      localStorage.setItem("isAuthenticated", "true");
      localStorage.setItem("userId", response.data.data.user.id);
      navigate("/");
    } catch (error) {
      // Hata durumunda mesajı state'e kaydet
      const errorMsg = error.response?.data?.message || 'Bilinmeyen bir hata oluştu.';
      setErrorMessage(errorMsg);
      console.error('Hata:', errorMsg);
    }
  };

  return (
    <div className="login-container">
      <img src={bitcoin} alt="" className='logo2'/>
      <h1 className="login-header">Giriş Yap</h1>
      <div className="login-form">
        <div className="input-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Şifre</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button onClick={handleLogin} className="login-button">Giriş Yap</button>
        {errorMessage && ( // Hata mesajını ekrana bastır
          <div className="error-message">
            <p>{errorMessage}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default LoginPage;
