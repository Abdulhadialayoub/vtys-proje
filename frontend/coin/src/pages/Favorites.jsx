import React, { useEffect, useState } from 'react';
import '../FavoritePage.css';
import { getFavoriteList, deleteFavorite } from '../server/api';
import { useNavigate } from "react-router-dom";
import budget from '../assets/budget.png';

const Favorites = () => {
  const [favoriteCoins, setFavoriteCoins] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchFavoriteCoins = async () => {
      try {
        const userId = localStorage.getItem("userId"); // Kullanıcının ID'sini al
        const response = await getFavoriteList(userId);
        setFavoriteCoins(response.data); // Favori coinleri state'e set et
      } catch (error) {
        console.error('Hata:', error);
      }
    };

    fetchFavoriteCoins();
  }, []);

  const handleRemoveFavorite = async (coinId) => {
    try {
      await deleteFavorite(coinId); // API çağrısını gerçekleştir
      setFavoriteCoins(favoriteCoins.filter(coin => coin.id !== coinId)); // UI'da coin'i kaldır
    } catch (error) {
      console.error('Hata:', error);
    }
  };

  const goToCoinDetails = (coinId) => {
    navigate(`/coin/${coinId}`); // Coin detay sayfasına yönlendir
  };

  return (
    <>
      <img src={budget} alt="" className='logo2' />
      <div className="favorite-container">
        <h1 className="favorite-header">Favori Coinlerim</h1>
        <div className="favorite-list">
          <table>
            <thead>
              <tr>
                <th>Coin Adı</th>
                <th>Risk Skoru</th>
                <th>İşlemler</th>
              </tr>
            </thead>
            <tbody>
              {favoriteCoins.map((favoriteCoin) => (
                <tr key={favoriteCoin.coin.id}>
                  <td onClick={() => goToCoinDetails(favoriteCoin.coin.id)}>{favoriteCoin.coin.coin}</td>
                  <td>{favoriteCoin.coin.riskScore}</td>
                  <td>
                    <button onClick={() => handleRemoveFavorite(favoriteCoin.id)}>Favoriden Çıkar</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default Favorites;
