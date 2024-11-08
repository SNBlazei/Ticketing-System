import React from "react";
import { useState } from "react";
import './Configuration.css';

function Configuration() {



    const [config, setConfig] = useState({
        totalTickets: 0,
        ticketReleaseRate: 0,
        customerRetrievalRate: 0,
        maxTicketCapacity: 0,

    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setConfig({
            config,
            [name]: [parseInt](value, 10),
        });

    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onsubmit(config);


    }

    return (
        <>

            <section className="configure section" id="configure">
                <h1 className="section__title" id="title">Ticket System Configuration</h1>




                <div className="configure__content">
                    

                    <form onSubmit={handleSubmit} className="configure__form">
                        <div className="configure__form-div">
                            <label className="configure__form-tag">Total Tickets</label>
                            <input
                                type="number"
                                value={config.totalTickets}
                                className="configure__form-input"
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="configure__form-div">
                            <label className="configure__form-tag">Ticket Release Rate</label>
                            <input
                                type="number"
                                value={config.ticketReleaseRate}
                                className="configure__form-input"
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="configure__form-div">
                            <label className="configure__form-tag">Customer Retrieval Rate</label>
                            <input
                                type="number"
                                value={config.customerRetrievalRate}
                                className="configure__form-input"
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="configure__form-div">
                            <label className="configure__form-tag">Max Ticket Capacity</label>
                            <input
                                type="number"
                                value={config.maxTicketCapacity}
                                className="configure__form-input"
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <button className="button button--flex">
                            Submit
                        </button>

                        


                    </form>
                </div>

            </section>

        </>
    )

}

export default Configuration;