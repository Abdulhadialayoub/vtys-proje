import { TextField, Button } from '@mui/material';
import React, { useState } from 'react';
import { getCoinPrice } from '../server/api';
import rightside from '../assets/leftside.webp';
import resim2 from '../assets/dene.webp';
import logo from '../assets/logo.jpeg';
import "../RightBar.css"; // Yeni CSS dosyasını dahil ediyoruz
import bitcoin from '../assets/increase.png';
import moment from 'moment'; // Tarih kütüphanesini dahil ediyoruz

const RightBar = () => {
  const [coinName, setCoinName] = useState('bitcoin'); // Default coin
  const [price, setPrice] = useState(null);

  const handleSearch = async () => {
    try {
      const coinPrice = await getCoinPrice(coinName);
      setPrice(coinPrice);
    } catch (error) {
      console.error("Error fetching coin price:", error);
    }
  };

  return (
    <div className="right-bar-container">
      <img src={bitcoin} alt="Right Side" className="logo2" />
      <img src={rightside} alt="Right Side" className="right-image" />
      <div className="search-container">
        <TextField className='text-field'
          label="Coin Adı"
          variant="outlined"
          color="primary" 
          InputProps={{
            style: {
              color: 'white', 
            },
          }}
          value={coinName}
          onChange={(e) => setCoinName(e.target.value)}
          fullWidth
        />
        <Button onClick={handleSearch} variant="contained" color="primary" fullWidth>
          Search
        </Button>
      </div>
      {price && (
        <div className="price-info">
          <h2>{coinName.charAt(0).toUpperCase() + coinName.slice(1)}</h2>
          <p>Price: {price} TL</p>
        </div>
      )}
      <img src={resim2} alt="Right Side" className="right-image" />
      <footer className="footer">
        <p>Tüm hakları saklıdır ©2024</p>
      </footer>
    </div>
  );
};

export default RightBar;
