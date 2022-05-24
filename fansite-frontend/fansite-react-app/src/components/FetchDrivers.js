import React, { useState, useEffect } from "react";

function FetchDrivers() {
    const [drivers, setDrivers] = useState([]);
    const [favorites, setFavorites] = useState([]);
    const [position, setPosition] = useState(null);
    const [name, setName] = useState('');

    const fetchFavorites = () => {
        fetch("http://localhost:8080/api/favorite")
            .then(resp => resp.json())
            .then(data => {
                setFavorites(data);
            })
    }


    useEffect(() => {
        // Make a get all request
        fetch("https://mario-kart-tour-api.herokuapp.com/api/v1/drivers/normal")
            .then(resp => resp.json())
            .then(data => {
                setDrivers(data);
            })


        fetchFavorites();
    }, []);

    const RemoveFromFavorites = (driver) => {
        fetch(`http://localhost:8080/api/favorite/${driver.position}`, { method: "DELETE" })
                .then(response => {
                    if (response.status === 204) { // status NO_CONTENT
                        fetchFavorites();
                    } else {
                        return Promise.reject("DELETE favorite status was not 204.");
                    }

                })
                .catch(console.error);
    }

    const AddToFavorites = (driver) => {
        const newFavorite = {
            position: driver.position,
            name: driver.name
        };

        const init = { // initialize the POST request
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(newFavorite)
        };

        fetch("http://localhost:8080/api/favorite", init) // Perform the POST request
            .then(response => {
                if (response.status > 299) {
                    alert("Error: cannot add to favorites.");
                } else {
                    console.log(`Favorite add succeeded with status ${response.status}`);

                    fetchFavorites();
                }

            });

    }

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Driver Index: </th>
                        <th>Name: </th>
                        <th>Special Skill: </th>
                        <th>Actions: </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        drivers.map(driver => {
                            return (
                                <tr key={driver.position}>
                                    <td>{driver.position}</td>
                                    <td>{driver.name}</td>
                                    <td>{driver.special_skill}</td>
                                    <td>{favorites.find(f => f.position === driver.position) ? <button className="btn btn-outline-warning" onClick={(e) => RemoveFromFavorites(driver)}>Dislike</button>
                                        : <button className="btn btn-outline-warning" onClick={(e) => AddToFavorites(driver)}>Like</button>}</td>
                                </tr>
                            );
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}


export default FetchDrivers;

