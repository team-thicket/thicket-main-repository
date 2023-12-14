import { useState } from 'react';

const DeleteSchedule = () => {
    const [timeSlots, setTimeSlots] = useState([]);

    // 날짜 기준 일정 삭제
    const handleRemoveDateTime = (dateToRemove) => {
        const updatedTimeSlots = timeSlots.filter((dateTime) => dateTime.date !== dateToRemove);
        setTimeSlots(updatedTimeSlots);
    };

    // 일별 시간 기준 일정 삭제
    const handleRemoveTime = (dateToRemove, timeToRemove) => {
        const dateIndex = timeSlots.findIndex((dateTime) => dateTime.date === dateToRemove);

        if (dateIndex !== -1) {
            const updatedTimeSlots = [...timeSlots];
            const timeIndex = updatedTimeSlots[dateIndex].times.findIndex(
                (time) => time.hour === timeToRemove.hour && time.minute === timeToRemove.minute
            );

            if (timeIndex !== -1) {
                updatedTimeSlots[dateIndex].times.splice(timeIndex, 1);

                if (updatedTimeSlots[dateIndex].times.length === 0) {
                    updatedTimeSlots.splice(dateIndex, 1);
                }

                setTimeSlots(updatedTimeSlots);
            }
        }
    };

    // 일정 전체 삭제
    const handleRemoveAllTimeSlots = () => {
        setTimeSlots([]);
    };

    return {
        timeSlots,
        setTimeSlots,
        handleRemoveDateTime,
        handleRemoveTime,
        handleRemoveAllTimeSlots,
    };
};

export default DeleteSchedule;