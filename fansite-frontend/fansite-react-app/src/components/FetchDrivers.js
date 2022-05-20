import React, { useState, useEffect } from "react";

function FetchDrivers() {
    const [drivers, setDrivers] = useState([]);
    const [position, setPosition] = useState(null);
    const [name, setName] = useState('');

    useEffect(() => {
        // Make a get all request
        fetch("https://mario-kart-tour-api.herokuapp.com/api/v1/drivers/normal")
            .then(resp => resp.json())
            .then(data => {
                setDrivers(data);
            })
    }, []);

    return (
        <div>
        <table>
            <thead>
                <tr>
                    <th>Position: </th>
                    <th>Name: </th>
                    <th>Actions: </th>
                </tr>
            </thead>
            <tbody>
                {
                    drivers.map(driver => {
                    return(
                        <tr>
                            <td>{driver.position}</td>
                            <td>{driver.name}</td>
                            <td><button className="btn">Like</button></td>
                        </tr>
                    );})
                }
            </tbody>
            </table>
            </div>



    )
}


export default FetchDrivers;