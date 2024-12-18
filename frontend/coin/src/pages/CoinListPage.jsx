import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getCoinsByRiskScoreAsc, getCoinList } from "../server/api";
import "../CoinListPage.css";
import menu from "../assets/menu.png"

const CoinListPage = () => {
  const [coins, setCoins] = useState([]);
  const [sortOption, setSortOption] = useState("default"); // Default sort by risk score
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCoins = async () => {
      try {
        let sortedCoins;
        if (sortOption === "riskScore") {
          sortedCoins = await getCoinsByRiskScoreAsc();
        } else {
          sortedCoins = await getCoinList(); // Assuming getCoins() fetches unsorted coin data
        }
        setCoins(sortedCoins);
      } catch (error) {
        console.error("Error fetching coins:", error);
      }
    };
    fetchCoins();
  }, [sortOption]); // Re-run effect when sortOption changes

  return (
    <div className="container">
      <h1 className="header">Coin Listesi</h1>
      
      {/* Sorting Options */}
      <div className="sort-options">
        <label htmlFor="sort-options"></label>
        <select
          id="sort-options"
          value={sortOption}
          onChange={(e) => setSortOption(e.target.value)}
        >
          <option value="riskScore">Risk Skoruna göre Sıralama</option>
          <option value="default">Default Sıralama</option>
        </select>
      </div>

      {/* Scrollable Table */}
      <div className="scrollable-table">
        <table className="table">
          <thead>
            <tr>
              <th>Coin Name</th>
              <th>Risk Score</th>
            </tr>
          </thead>
          <tbody>
            {coins.map((coin) => (
              <tr
                key={coin.id}
                onClick={() => navigate(`/coin/${coin.id}`)}
              >
                <td>{coin.coin}</td>
                <td>{coin.riskScore}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default CoinListPage;
