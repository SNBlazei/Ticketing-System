import React from 'react';

function TicketStatus({ status }) {
  return (
    <div className="bg-gradient-to-br from-blue-50 to-white p-4 border border-blue-200 rounded-lg shadow-md max-w-sm mx-auto">
      <h3 className="text-lg font-semibold text-blue-800 mb-3 text-center uppercase tracking-wide">
        Ticket Pool Status
      </h3>

      <div className="flex items-center justify-center bg-blue-100 border border-blue-300 rounded-full w-36 h-36 mx-auto shadow-inner">
        <p className="text-3xl font-bold text-blue-900">{status}</p>
      </div>

      <div className="mt-4 bg-white p-3 rounded-lg shadow-sm">
        <p className="text-sm text-gray-600 text-center">
          <strong className="text-blue-800">Total Tickets Available:</strong> {status}
        </p>
      </div>
    </div>
  );
}

export default TicketStatus;
