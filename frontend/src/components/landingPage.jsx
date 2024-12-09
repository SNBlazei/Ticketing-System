import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import startingPg from '../assets/landingbg2.jpg';

function LandingPage() {
  const navigate = useNavigate();

  return (
    <div
      className="relative h-screen bg-cover bg-center"
      style={{
        backgroundImage: `url(${startingPg})`,
      }}
    >
      {/* Background overlay */}
      <div className="absolute inset-0 bg-gradient-to-t from-black via-transparent to-black opacity-80"></div>
      
      {/* Main Content */}
      <div className="relative z-10 flex flex-col items-center justify-center h-full text-center text-white px-6">
        <div className="bg-black bg-opacity-50 p-10 rounded-3xl shadow-2xl max-w-md">
          <h1 className="text-4xl font-extrabold mb-6 leading-tight tracking-wide">
            Welcome to the <span className="text-blue-400">Ticketing System</span> 
          </h1>
          <p className="text-lg mb-8 text-gray-300">
            Real Time Event
          </p>
          <button
            onClick={() => navigate('/dashboard')}
            className="w-full py-3 bg-blue-500 hover:bg-blue-600 text-white font-bold text-lg rounded-full shadow-lg transition-transform transform hover:scale-105 focus:ring-4 focus:ring-blue-400 focus:outline-none"
          >
            Get Started
          </button>
        </div>
      </div>
    </div>
  );
}

export default LandingPage;