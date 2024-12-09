import React, { useState } from 'react';

function ConfigurationForm({ socket }) {
  const [totalTickets, setTotalTickets] = useState('');
  const [ticketReleaseRate, setTicketReleaseRate] = useState('');
  const [customerRetrievalRate, setCustomerRetrievalRate] = useState('');
  const [maxTicketCapacity, setMaxTicketCapacity] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [submittedValues, setSubmittedValues] = useState(null);

  const validateTicketRange = (value) => {
    return value >= 5 && value <= 5000;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validateTicketRange(totalTickets)) {
      setErrorMessage('Total tickets must be between 5 and 5000.');
      return;
    }
    if (!validateTicketRange(maxTicketCapacity)) {
      setErrorMessage('Max ticket capacity must be between 5 and 5000.');
      return;
    }

    setErrorMessage('');
    const config = {
      totalTickets: parseInt(totalTickets, 10),
      ticketReleaseRate: parseInt(ticketReleaseRate, 10),
      customerRetrievalRate: parseInt(customerRetrievalRate, 10),
      maxTicketCapacity: parseInt(maxTicketCapacity, 10),
    };
    socket.emit('configureSystem', config);
    setSubmittedValues(config);
  };

  return (
    <div className="p-12 bg-gradient-to-b from-white to-gray-100 border border-gray-200 rounded-xl shadow-md max-w-3xl mx-auto">
      <h2 className="text-2xl font-bold text-gray-800 mb-6 text-center">
        Configure Ticketing System
      </h2>

      <form onSubmit={handleSubmit} className="space-y-6">
        {errorMessage && (
          <div className="text-red-500 text-sm font-medium text-center">
            {errorMessage}
          </div>
        )}

        <div>
          <label htmlFor="totalTickets" className="block text-sm font-medium text-gray-700">
            Total Tickets
          </label>
          <input
            id="totalTickets"
            type="number"
            value={totalTickets}
            onChange={(e) => setTotalTickets(e.target.value)}
            placeholder="Enter total tickets"
            required
            className="mt-1 w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <div>
          <label htmlFor="ticketReleaseRate" className="block text-sm font-medium text-gray-700">
            Ticket Release Rate
          </label>
          <input
            id="ticketReleaseRate"
            type="number"
            value={ticketReleaseRate}
            onChange={(e) => setTicketReleaseRate(e.target.value)}
            placeholder="Enter release rate"
            required
            className="mt-1 w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <div>
          <label htmlFor="customerRetrievalRate" className="block text-sm font-medium text-gray-700">
            Customer Retrieval Rate
          </label>
          <input
            id="customerRetrievalRate"
            type="number"
            value={customerRetrievalRate}
            onChange={(e) => setCustomerRetrievalRate(e.target.value)}
            placeholder="Enter retrieval rate"
            required
            className="mt-1 w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <div>
          <label htmlFor="maxTicketCapacity" className="block text-sm font-medium text-gray-700">
            Max Ticket Capacity
          </label>
          <input
            id="maxTicketCapacity"
            type="number"
            value={maxTicketCapacity}
            onChange={(e) => setMaxTicketCapacity(e.target.value)}
            placeholder="Enter max capacity"
            required
            className="mt-1 w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        <button
          type="submit"
          className="w-full bg-blue-600 text-white font-semibold py-3 rounded-lg hover:bg-blue-700 transition-all focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          Submit Configuration
        </button>
      </form>

      {submittedValues && (
        <div className="mt-8 bg-gray-50 p-6 rounded-lg shadow-inner">
          <h3 className="text-lg font-semibold text-gray-800 mb-4">Submitted Values</h3>
          <p className="text-gray-700">
            <strong>Total Tickets:</strong> {submittedValues.totalTickets}
          </p>
          <p className="text-gray-700">
            <strong>Ticket Release Rate:</strong> {submittedValues.ticketReleaseRate}
          </p>
          <p className="text-gray-700">
            <strong>Customer Retrieval Rate:</strong> {submittedValues.customerRetrievalRate}
          </p>
          <p className="text-gray-700">
            <strong>Max Ticket Capacity:</strong> {submittedValues.maxTicketCapacity}
          </p>
        </div>
      )}
    </div>
  );
}

export default ConfigurationForm;
