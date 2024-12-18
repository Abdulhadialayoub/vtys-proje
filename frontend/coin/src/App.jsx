import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from './pages/HomePage';
import CoinListPage from './pages/CoinListPage';
import CoinDetailPage from './pages/CoinDetailPage';
import Layout from './compenents/Layout';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import Favorites from './pages/Favorites';
import UserPage from './pages/UserPage';


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      
      <Router>
      
      <Layout>
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/coins" element={<CoinListPage />} />
            <Route path="/coin/:id" element={<CoinDetailPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/favorites" element={<Favorites />} />
            <Route path="/users" element={<UserPage />} />
        </Routes>
      </Layout>
    </Router>
    </>
  )
}

export default App
