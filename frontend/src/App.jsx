import React, { useState, useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import io from "socket.io-client";
import ConfigurationForm from "./components/ConfigurationForm";
import TicketStatus from "./components/TicketStatus";
import ControlPanel from "./components/ControlPanel";
import LandingPage from "./components/landingPage";
import LogDisplay from "./components/LogDisplay";

const socket = io("http://localhost:5000");

function App() {
  const [ticketStatus, setTicketStatus] = useState(0);
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    socket.on("ticketUpdate", (status) => {
      const timestamp = new Date().toLocaleTimeString();
      const ticketsChange = status - ticketStatus;
      let logMessage;

      if (ticketsChange > 0) {
        logMessage = `[${timestamp}] Vendor added ${ticketsChange} ticket(s). Available tickets: ${status}`;
      } else if (ticketsChange < 0) {
        logMessage = `[${timestamp}] Customer purchased ${Math.abs(
          ticketsChange
        )} ticket(s). Available tickets: ${status}`;
      } else {
        logMessage = `[${timestamp}] No change in tickets. Available tickets: ${status}`;
      }

      setLogs((prevLogs) => [...prevLogs, logMessage]);
      setTicketStatus(status);
    });

    return () => {
      socket.off("ticketUpdate");
    };
  }, [ticketStatus]);

  return (
    <Router>
      <Routes>
        {/* Landing Page */}
        <Route path="/" element={<Navigate to="/LandingPage" />} />
        <Route path="/LandingPage" element={<LandingPage />} />

        {/* Vendor Dashboard */}
        <Route
          path="/dashboard"
          element={
            <div className="min-h-screen flex flex-col bg-gray-100">
              <div className="relative flex-grow flex items-center justify-center bg-cover bg-center bg-no-repeat">
                <div className="absolute inset-0 bg-black bg-opacity-70"></div>
                <div className="relative w-full max-w-6xl mx-auto p-6 bg-white bg-opacity-90 rounded-lg shadow-2xl">
                  <h1 className="text-4xl font-extrabold text-center mb-6 text-indigo-300 shadow-md">
                    Real-Time Ticketing System
                  </h1>
                  <div className="p-6 bg-white shadow-lg rounded-lg mt-4 max-w-[800px] mx-auto">
                    <h2 className="text-2xl font-semibold text-blue-600 mb-6 text-center border-b-2 border-blue-300 pb-2">
                      Vendor Dashboard
                    </h2>
                    <div className="space-y-6">
                      <ConfigurationForm socket={socket} />
                      <TicketStatus status={ticketStatus} />
                      <ControlPanel socket={socket} />
                      <div className="p-6 bg-gray-200 rounded-lg shadow-inner mt-6">
                        <h3 className="text-lg font-medium text-gray-800 mb-4">
                          System Logs
                        </h3>
                        <LogDisplay logs={logs} />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
