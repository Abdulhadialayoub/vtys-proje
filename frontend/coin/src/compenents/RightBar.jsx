import { TextField, Button } from '@mui/material';
import React, { useState } from 'react';
import { getCoinPrice, getCoinChart } from '../server/api'; // Grafik verisi için ek API
import { Line } from 'react-chartjs-2';
import bitcoin from '../assets/increase.png';
import rightside from '../assets/leftside.webp';
import resim2 from '../assets/dene.webp';
import "../RightBar.css"; // Yeni CSS dosyasını dahil ediyoruz
import 'chart.js/auto';

const RightBar = () => {
  const [coinName, setCoinName] = useState('bitcoin'); // Default coin
  const [price, setPrice] = useState(null);
  const [chartData, setChartData] = useState(null); // Grafik verileri
  const [isImageVisible, setIsImageVisible] = useState(true);

  const handleSearch = async () => {
    try {
      // Fiyatı çek
      const coinPrice = await getCoinPrice(coinName);
      setPrice(coinPrice);

      // Grafik verilerini çek
      const chartResponse = await getCoinChart(coinName);
      const prices = chartResponse.prices.map((entry) => entry[1]);
      const labels = chartResponse.prices.map((entry) =>
        new Date(entry[0]).toLocaleDateString()
      );

      setChartData({
        labels,
        datasets: [
          {
            label: `${coinName.charAt(0).toUpperCase() + coinName.slice(1)} Fiyat Grafiği`,
            data: prices,
            borderColor: 'rgb(233, 241, 241)',
            backgroundColor: 'rgba(75,192,192,0.2)',
            borderWidth: 1, // Çizgi kalınlığı azaltıldı
            lineTension: 0.1, // Çizginin gerginliği ayarlandı
          },
        ],
      });

      setIsImageVisible(false); // Grafik bilgisi geldikten sonra altındaki resim gizlenir
    } catch (error) {
      console.error("Error fetching coin data:", error);
    }
  };

  return (
    <div className="right-bar-container">
      <img src={bitcoin} alt="Right Side" className="logo2" />
      {isImageVisible && <img src={rightside} alt="Right Side" className="right-image" />}
      <div className="search-container">
        <TextField
          className="text-field"
          label="Coin Adı"
          variant="outlined"
          color="primary"
          InputProps={{
            style: { color: 'white' },
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
      {chartData && (
        <div className="chart-container">
          <Line data={chartData} />
        </div>
      )}
      {!isImageVisible && <img src={resim2} alt="Right Side" className="right-image" />}
      
    </div>
  );
};

export default RightBar;
