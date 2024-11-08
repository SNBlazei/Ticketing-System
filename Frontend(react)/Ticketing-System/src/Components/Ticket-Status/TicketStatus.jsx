import React, { useState,useEffect } from "react";

function TicketStatus(){

    const[ticketDetails,setTicketDetails]=useState({
        totalTickets:0,
        addedTickets:0,
        removedTickets:0,
        availableTickets:0,
    });

    useEffect(()=>{

        const fetchData=()=>{
            setTicketDetails({
                totalTickets:0,
                addedTickets:0,
                removedTickets:0,
                availableTickets:0,
                

            });
        };
        fetchData();

    },[]);


    return(
        <>
        <div className="ticket-status">
            <h2 className="ticket-title">Ticket Status</h2>
            <div className="status-container">
                <p><strong>Total Tickets:</strong>{ticketDetails.totalTickets}</p>
                <p><strong>Added Tickets:</strong>{ticketDetails.addedTickets}</p>
                <p><strong>Removed Tickets:</strong>{ticketDetails.removedTickets}</p>
                <p><strong>Available Tickets:</strong>{ticketDetails.availableTickets}</p>
            </div>
        </div>
        
        </>
    )


}

export default TicketStatus;