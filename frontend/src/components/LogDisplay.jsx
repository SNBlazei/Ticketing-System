import React from "react";

function LogDisplay({ logs }) {
  return (
    <div className="p-6 bg-white rounded-lg shadow-lg mt-4 max-h-64 overflow-y-auto border border-gray-300">
      <h3 className="text-2xl font-semibold text-gray-800 mb-4">System Logs</h3>
      <ul className="list-inside text-gray-700">
        {logs.length > 0 ? (
          logs.map((log, index) => (
            <li
              key={index}
              className="px-4 py-2 mb-2 bg-gray-100 rounded-lg shadow-sm border border-gray-200 hover:bg-gray-200 transition duration-150"
            >
              <p className="text-sm text-gray-800">{log}</p>
            </li>
          ))
        ) : (
          <li className="text-gray-500 text-center py-4">No logs available</li>
        )}
      </ul>
    </div>
  );
}

export default LogDisplay;
