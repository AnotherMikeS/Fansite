import { useState, useEffect } from "react";

function Favorites(){
    const [favorites, setFavorites] = useState([]);

    useEffect(() => {
        fetchFavorites();
    }, []);

    const fetchFavorites = () => {
        fetch("http://localhost:8080/api/favorite")
            .then(resp => resp.json())
            .then(data => {
                setFavorites(data);
            })
    }

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


    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Driver Index: </th>
                        <th>Name: </th>
                        <th>Actions: </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        favorites.map(favorite => {
                            return (
                                <tr key={favorite.position}>
                                    <td>{favorite.position}</td>
                                    <td>{favorite.name}</td>
                                    <td><button className="btn" onClick={(e) => RemoveFromFavorites(favorite)}>Remove</button></td>
                                </tr>
                            );
                        })
                    }
                </tbody>
            </table>
        </div>
    )

}

export default Favorites;