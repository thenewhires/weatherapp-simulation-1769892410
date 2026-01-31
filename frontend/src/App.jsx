import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Header from './components/Header';

function App() {
  const [city, setCity] = useState('London');
  const [weatherData, setWeatherData] = useState(null);

  const apiKey = 'YOUR_API_KEY'; // Replace with your actual API key
  const backendUrl = 'http://localhost:8080/weather';

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${backendUrl}?city=${city}`);
        setWeatherData(response.data);
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    fetchData();
  }, [city]);

  const handleCityChange = (event) => {
    setCity(event.target.value);
  };

  return (
    <div className="App">
      <Header />
      <h1>Weather App</h1>
      <input
        type="text"
        value={city}
        onChange={handleCityChange}
        placeholder="Enter city"
      />
      {weatherData && (
        <div>
          <h2>Weather in {weatherData.name}</h2>
          <p>Temperature: {Math.round(weatherData.main.temp)}°C</p>
          <p>Description: {weatherData.weather[0].description}</p>
        </div>
      )}
    </div>
  );
}

export default App;