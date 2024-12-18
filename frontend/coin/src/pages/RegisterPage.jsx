import React, { useState } from 'react';
import '../LoginPage.css';
import register from '../assets/login.png';
import { registerUser } from '../server/api';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState('');

  const handleRegister = async () => {
    const body = {
      name,
      email,
      password,
    };
    try {
      const response = await registerUser(body);
      console.log('Kayıt başarılı:', response);
      alert("Kayıt başarılı");
      navigate("/login");
    } catch (error) {
      // Hata durumunda mesajı state'e kaydet
      console.log("error: " + error.response);
      const errorMsg = error.response?.data?.message || 'Bilinmeyen bir hata oluştu.';
      setErrorMessage(errorMsg);
      console.error('Hata:', errorMsg);
    }

    console.log('Kayıt Bilgileri:', { name, email, password });
  };

  return (
    <div className="login-container">
      <img src={register} alt="" className='logo2'/>
      <h1 className="login-header">Kayıt Ol</h1>
      <div className="login-form">
        <div className="input-group">
          <label htmlFor="username">Kullanıcı Adı</label>
          <input
            type="text"
            id="username"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="input-field"
          />
        </div>
        <div className="input-group">
          <label htmlFor="email">E-posta</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="input-field"
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Şifre</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="input-field"
          />
        </div>
        <button onClick={handleRegister} className="login-button">Kayıt Ol</button>
        {errorMessage && <div className="error-message">{errorMessage}</div>}
      </div>
    </div>
  );
};

export default RegisterPage;
