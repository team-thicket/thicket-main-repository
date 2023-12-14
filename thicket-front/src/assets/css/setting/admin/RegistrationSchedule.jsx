import React, { useState } from 'react';
import { H2 } from "./Styles1";
import DeleteSchedule from "./DeleteSchedule";

const RegistrationSchedule = ({ startDate, endDate, onConfirm, }) => {

    const [selectedDate, setSelectedDate] = useState('');
    const [selectedHour, setSelectedHour] = useState('');
    const [selectedMinute, setSelectedMinute] = useState('');
    const { setTimeSlots, } = DeleteSchedule();

    // 일정 등록 날짜 조정
    const addDays = (date, days) => {
        const result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    };

    const handleConfirm = () => {
        if (selectedDate && selectedHour !== '' && selectedMinute !== '') {
            const formattedDateTime = {
                date: selectedDate,
                times: [{ hour: selectedHour, minute: selectedMinute }],
            };

            onConfirm(formattedDateTime);

            setTimeSlots((prevTimeSlots) => {
                const updatedTimeSlots = [...prevTimeSlots];
                const existingDateIndex = updatedTimeSlots.findIndex((item) => item.date === formattedDateTime.date);

                if (existingDateIndex !== -1) {
                    updatedTimeSlots[existingDateIndex].times.sort((a, b) => {
                        const timeA = new Date(`1970-01-01T${a.hour}:${a.minute}`);
                        const timeB = new Date(`1970-01-01T${b.hour}:${b.minute}`);
                        return timeA - timeB;
                    });
                }

                return updatedTimeSlots.sort((a, b) => new Date(a.date) - new Date(b.date));
            });

            console.log(formattedDateTime);
        }
    };

    return (
        <div style={{ textAlign: 'center' }}>
            <div
                style={{
                    border: '1px solid #000',
                    borderRadius: '5px',
                    padding: '10px',
                    display: 'inline-block',
                }}
            >
                <H2>날짜 및 시간 등록</H2>
                <div>
                    <input
                        type="date"
                        style={{ width: '136px', marginBottom: '10px' }}
                        value={selectedDate}
                        onChange={(e) => setSelectedDate(e.target.value)}
                        min={startDate ? addDays(startDate, 1).toISOString().split('T')[0] : undefined}
                        max={endDate ? addDays(endDate, 1).toISOString().split('T')[0] : undefined}
                    />
                </div>
                <div>
                    <select
                        style={{
                            width: '50px',
                            height: '25px',
                            boxSizing: 'border-box',
                            marginBottom: '10px',
                        }}
                        value={selectedHour}
                        onChange={(e) => setSelectedHour(e.target.value === '24' ? '00' : e.target.value)}
                    >
                        <option value="">선택</option>
                        {Array.from({ length: 24 }, (_, i) => i.toString().padStart(2, '0')).map((hour) => (
                            <option key={hour} value={hour}>
                                {hour === '24' ? '00' : hour}
                            </option>
                        ))}
                    </select>시
                    <select
                        style={{
                            width: '50px',
                            height: '25px',
                            boxSizing: 'border-box',
                            marginBottom: '10px',
                            marginLeft: '5px',
                        }}
                        value={selectedMinute}
                        onChange={(e) => setSelectedMinute(e.target.value)}
                    >
                        <option value="">선택</option>
                        <option value="00">00</option>
                        <option value="30">30</option>
                    </select>분
                </div>
                <button type="button" onClick={handleConfirm}>
                    등록
                </button>
            </div>
        </div>
    );
};

export default RegistrationSchedule;