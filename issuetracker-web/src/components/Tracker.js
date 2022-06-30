import React from 'react';
import { useLocalState } from "./utils/LocalStorage";

const Tracker = () => {
    const [jwt, setJwt] = useLocalState("", "jwt");

    return (
        <div>
            <h1>Issue Tracker</h1>
            <div>
                JWT Value: {jwt}
            </div>
        </div>
    );
};

export default Tracker;