import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { addFoverite, getCoin, getCoinPrice } from "../server/api";
import "../App.css"; // App.css'i dahil ediyoruz
import image from "../assets/monetize.png";


const CoinDetailPage = () => {
  const { id } = useParams();
  const [coin, setCoin] = useState(null);
  const [price, setPrice] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCoin = async () => {
      try {
        const coinData = await getCoin(id);
        setCoin(coinData.data);

        const coinPriceData = await getCoinPrice(coinData.data.coin);
        setPrice(coinPriceData);
      } catch (error) {
        console.error("Error fetching coin details:", error);
      }
    };
    fetchCoin();
  }, [id]);

  const addToFavorites = async() => {
    const isLogin = localStorage.getItem("isAuthenticated");
    if (isLogin==="true") {
      try {
        var res = await addFoverite(id, localStorage.getItem("userId"));
        console.log(res);
        navigate("/favorites");
        

       } catch (error) {
        console.error(error);
     }
    } else {
      alert('Lütfen giriş yapın.');
    }
  };

  if (!coin || !price) {
    return <p>Loading...</p>;
  }

  return (
    <>
      
      <div className="coin-detail-container">
       
        <div className="coin-card">
           <img src={image} alt=""  className="logo3"/>
        <h1 className="coin-name">{coin.coin}</h1>
        <div className="coin-info">
          <p>
            <strong>Fiyat:</strong> {coin.price} TL
          </p>
          <p>
            <strong>Açıklama:</strong> {coin.description}
          </p>
          <p>
            <strong>Öngörü:</strong> {coin.prediction}
          </p>
          <p>
            <strong>Risk Skoru:</strong> {coin.riskScore}
          </p>
        </div>
        <button onClick={addToFavorites} className="favorite-button">
          Favorilere Ekle
        </button>
      </div>
    </div>
    </>
   
  );
};

export default CoinDetailPage;
