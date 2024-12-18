import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getCoinList } from "../server/api";
import { CardMedia } from "@mui/material";
import logo from '../assets/coins.png'



const HomePage = () => {
  const [coins, setCoins] = useState([]);
  const [selectedCoin, setSelectedCoin] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCoins = async () => {
      try {
        const coinList = await getCoinList();
        setCoins(coinList);
      } catch (error) {
        console.error("Error fetching coins:", error);
      }
    };
    fetchCoins();
  }, []);

  const handleSelect = (event) => {
    const selected = event.target.value;
    setSelectedCoin(selected);
    navigate(`/coin/${selected}`);
  };

  return (
      <div className="homepage">
         
          <h1>Coin Analiz Sitesi</h1>
          <img src={logo} className="logo2"alt="" />
      <div className="select-container">
        <select value={selectedCoin} onChange={handleSelect}>
          <option value="" disabled>
            Coin Seç
          </option>
          {coins.map((coin) => (
            <option key={coin.id} value={coin.id}>
              {coin.coin}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
};

export default HomePage;
