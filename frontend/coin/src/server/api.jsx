import axios from "axios";

var url = "/api/v1";



// coin getirme 
export const getCoin = async (id) => {
  try {
      const response = await axios.get(`${url}/coin/${id}`);
      console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error fetching coin:", error);
    throw error;
  }
};

// Coin listesini getirme
export const getCoinList = async () => {
  try {
    const response = await axios.get(`${url}/coin/list`);
    return response.data;
  } catch (error) {
    console.error("Error fetching coin list:", error);
    throw error;
  }
};

// Risk skoruna göre sıralı coinleri getirme
export const getCoinsByRiskScoreAsc = async () => {
  try {
    const response = await axios.get(`${url}/coin/sorted/asc`);
    return response.data;
  } catch (error) {
    console.error("Error fetching coins by risk score:", error);
    throw error;
  }
};

// Coin fiyatını getirme
export const getCoinPrice = async (name) => {
  try {
    const response = await axios.get(`${url}/coin/price/${name}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
};

// giriş
export const login = async (body) => {
  try {
    const response = await axios.post(`${url}/auth/login`,body);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
};

// çıkış 
export const logout = async () => {
  try {
    const response = await axios.delete(`${url}/auth/logout`);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
};
// favoriye coin ekle 
export const addFoverite = async (coinId, userId) => {
  try { 
    const response = await axios.post(`${url}/favorite/add/${coinId}/${userId}`);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
}
// favoriden çıkar
export const deleteFavorite = async (coinId) => {
  try { 
    const response = await axios.delete(`${url}/favorite/delete/${coinId}`);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
}
// kullanıcıya ait favori listesini getir
export const getFavoriteList = async (userId) => {
  try { 
    const response = await axios.get(`${url}/favorite/list/${userId}`);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
  
 

}
// kullanici bilgisini döndür 
export const getUser = async (userId) => {
  try { 
    const response = await axios.get(`${url}/user/${userId}`);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
  
}
// kullanıcı kayıt
export const registerUser = async (body) => {
  try { 
    const response = await axios.post(`${url}/user/register`,body);
    return response;
  } catch (error) {
    console.error("Error fetching coin price:", error);
    throw error;
  }
  
}
