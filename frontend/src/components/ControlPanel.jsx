import React from 'react';

function ControlPanel({ socket }) {
  const startSystem = () => {
    socket.emit('startSystem');
  };

  const stopSystem = () => {
    socket.emit('stopSystem');
  };

  const resetSystem = () => {
    socket.emit('resetSystem');
  };

  return (
    <div className="flex flex-col md:flex-row justify-center items-center gap-6 p-8 bg-gradient-to-tr from-gray-100 to-gray-50 border border-gray-300 rounded-xl shadow-xl max-w-2xl mx-auto">
      {/* Start Button */}
      <button
        onClick={startSystem}
        className="flex-1 py-3 px-8 text-lg font-semibold text-white bg-gradient-to-r from-teal-400 to-blue-500 hover:from-teal-500 hover:to-blue-600 rounded-full shadow-md transform transition-transform hover:scale-110 focus:ring focus:ring-teal-300 focus:outline-none"
        aria-label="Start System"
      >
        Start
      </button>

      {/* Stop Button */}
      <button
        onClick={stopSystem}
        className="flex-1 py-3 px-8 text-lg font-semibold text-white bg-gradient-to-r from-pink-500 to-red-500 hover:from-pink-600 hover:to-red-600 rounded-full shadow-md transform transition-transform hover:scale-110 focus:ring focus:ring-pink-300 focus:outline-none"
        aria-label="Stop System"
      >
        Stop
      </button>

      {/* Reset Button */}
      <button
        onClick={resetSystem}
        className="flex-1 py-3 px-8 text-lg font-semibold text-white bg-gradient-to-r from-yellow-400 to-orange-500 hover:from-yellow-500 hover:to-orange-600 rounded-full shadow-md transform transition-transform hover:scale-110 focus:ring focus:ring-yellow-300 focus:outline-none"
        aria-label="Reset System"
      >
        Reset
      </button>
    </div>
  );
}

export default ControlPanel;
