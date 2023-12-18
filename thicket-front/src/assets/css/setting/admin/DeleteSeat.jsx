import { useState } from 'react';

const DeleteSeat = () => {
    const [seatValues, setSeatValues] = useState([]);

    // 좌석 삭제
    const handleRemoveSeat = (typeToRemove) => {
        const updatedSeatValues = seatValues.filter((seat) => seat[0].value !== typeToRemove);
        setSeatValues(updatedSeatValues);
    };

    return {
        seatValues,
        setSeatValues,
        handleRemoveSeat,
    };
};

export default DeleteSeat;