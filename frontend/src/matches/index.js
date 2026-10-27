import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";

export default function MatchesListing() {
    const [matches, setMatches] = useState([]);


    useEffect(() => {
        fetch("/api/v1/matches")
            .then((response) => response.json())
            .then((data) => setMatches(data))
            .catch((error) => console.error("Error fetching matches:", error));
    }, []);

    return (
        <div>
            matches.length == 0 ? (
                <h2>NO MATCHES </h2>
            ) : (
                <Table striped>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Start</th>
                            <th>Finish</th>
                            <th>White</th>
                            <th>Black</th>
                            <th>Winner  </th>
                        </tr>
                    </thead>
                    <tbody>
                        {matches.map((match) => (
                            <tr key={match.id}>
                                <td>{match.name}</td>
                                <td>{match.start}</td>
                                <td>{match.finish}</td>
                                <td>{match.white.username}</td>
                                <td>{match.black.username}</td>
                                <td>{match.winner?.username || "Not decided"}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )
        </div>
    );
}
