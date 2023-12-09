import React, { useState, useRef } from 'react';
import DatePicker, {registerLocale} from 'react-datepicker';
import ko from 'date-fns/locale/ko';
import 'react-datepicker/dist/react-datepicker.css';
import ReactDOM from 'react-dom';
import styled from "styled-components";

const createContainerStyle = {
    padding: '10px',
    overflowY: 'auto', // Add overflow property for vertical scrollbar
    maxHeight: '80vh', // Set a max height to trigger scrollbar
};

const customDivStyle = {
    padding: '20px 0px 60px 0',
};

const infoTableStyle = {
    width: '100%',
    borderCollapse: 'collapse',
};

const customH1Style = {
    margin: '0 0 20px 0',
    paddingLeft: '5px',
};

const customThStyle = {
    textAlign: 'left',
    height: '29px',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: '#f7f7f7',
    minWidth: '180px', // Use minWidth instead of width
    maxWidth: '180px', // Use maxWidth to limit the width
    whiteSpace: 'nowrap', // Prevent text from wrapping
    overflow: 'hidden', // Hide overflow text
    textOverflow: 'ellipsis', // Display ellipsis for overflow text
    borderTop: '1px solid #000',
};

const customTdStyle = {
    textAlign: 'left',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: 'white',
    borderTop: '1px solid #000',
};

const customInputStyle = {
    width: '300px',
    height: '25px',
    boxSizing: 'border-box',
};

const customInputTimeStyle = {
    width: '50px',
    height: '25px',
    boxSizing: 'border-box',
    marginBottom: '10px',
};

const customButtonStyle = {
    marginLeft: '10px',
    cursor: 'pointer',
    padding: '4px 8px',
    border: '1px solid #000',
    borderRadius: '5px',
};

const customButton_1Style = {
    cursor: 'pointer',
    padding: '4px 8px',
    border: '1px solid #000',
    borderRadius: '5px',
};

const xButtonStyle = {
    position: 'absolute',
    right: '10px',
    padding: '0px 3px',
};

const StyledDatePicker = styled(DatePicker)`
  width: 300px;
  height: 25px;
  box-sizing: border-box;
`;

const AdminCreateStage = () => {

    const [startDate, setStartDate] = useState(null);   // 전체 시작일
    const [endDate, setEndDate] = useState(null);       // 전체 종료일
    const datePickerRef = useRef(null);     // 전체 시작일 달력
    const endDatePickerRef = useRef(null);  // 전체 종료일 달력
    const [timeSlots, setTimeSlots] = useState([]);        // 일별 시작 시간
    const [selectedPerformanceType, setSelectedPerformanceType] = useState('');     // 공연 종류
    const [selectedPerformanceStatus, setSelectedPerformanceStatus] = useState(''); // 공연 상태
    const [seatValues, setSeatValues] = useState([]);   // 좌석

    // 달력 한글화
    registerLocale('ko', ko);

    // 달력 유효성 검사
    const setStartDateWithValidation = (date) => {
        if (endDate && date > endDate) {
            alert('시작일은 종료일 이후일 수 없습니다.');
        } else {
            setStartDate(date);
        }
    };

    // 일별 시작 시간 등록
    const TimeSelection = ({ onConfirm }) => {
        const [selectedDate, setSelectedDate] = useState('');
        const [selectedHour, setSelectedHour] = useState('');
        const [selectedMinute, setSelectedMinute] = useState('');

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
                    <h2 style={customH1Style}>날짜 및 시간 선택</h2>
                    <div>
                        <input
                            type="date"
                            style={{ width: '136px', marginBottom: '10px' }}
                            value={selectedDate}
                            onChange={(e) => setSelectedDate(e.target.value)}
                        />
                    </div>
                    <div>
                        <select
                            style={customInputTimeStyle}
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
                            style={{ ...customInputTimeStyle, marginLeft: '5px' }}
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

    // 일별 시작 시간 등록 버튼
    const handleAddTimeButtonClick = () => {
        const newWindow = window.open('', '_blank', 'width=240,height=190,left=100,top=100');

        ReactDOM.render(
            <TimeSelection
                onConfirm={(selectedDateTime) => {

                    const existingDateIndex = timeSlots.findIndex((item) => item.date === selectedDateTime.date);

                    if (existingDateIndex !== -1) {

                        const isDuplicateTime = timeSlots[existingDateIndex].times.some(
                            (time) =>
                                time.hour === selectedDateTime.times[0].hour &&
                                time.minute === selectedDateTime.times[0].minute
                        );

                        if (isDuplicateTime) {
                            alert('이미 동일한 시간이 추가되었습니다.');
                        } else {
                            setTimeSlots((prevTimeSlots) => {
                                const updatedTimeSlots = [...prevTimeSlots];
                                updatedTimeSlots[existingDateIndex].times.push({
                                    hour: selectedDateTime.times[0].hour,
                                    minute: selectedDateTime.times[0].minute,
                                });
                                return updatedTimeSlots;
                            });
                        }
                    } else {
                        const isDuplicateDate = timeSlots.some((item) => item.date === selectedDateTime.date);

                        if (isDuplicateDate) {
                            alert('이미 동일한 날짜가 추가되었습니다.');
                        } else {
                            setTimeSlots((prevTimeSlots) => [...prevTimeSlots, selectedDateTime]);
                        }
                    }

                    newWindow.close();
                }}
            />,
            newWindow.document.body
        );
    };

    // 날짜 기준 일정 삭제
    const handleRemoveDateTime = (dateToRemove) => {
        // Filter out the selected date
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

    // 좌석 추가
    const SeatModalContent = ({ onSubmit }) => {
        const [inputValues, setInputValues] = useState([
            { label: '타입', value: '' },
            { label: '개수', value: '' },
            { label: '가격', value: '' },
        ]);

        const newWindowRef = useRef(null);

        const handleInputChange = (index, value) => {
            if ((inputValues[index].label === '개수' || inputValues[index].label === '가격')) {
                if (!/^\d+$/.test(value)) {
                    // 숫자로만 구성되지 않은 경우, 입력값을 '0'으로 설정
                    value = '0';
                }
            }

            setInputValues((prevInputValues) => {
                const newValues = [...prevInputValues];
                newValues[index].value = value;
                return newValues;
            });
        };

        const handleSubmit = (e) => {
            e.preventDefault();

            const hasEmptyValues = inputValues.some((field) => field.value === '');

            if (hasEmptyValues) {
                // If any values are empty, close the window using the ref
                newWindowRef.current && newWindowRef.current.close();
            } else {
                // Process the entered values
                const formattedValues = inputValues.reduce((acc, curr) => {
                    const formattedValue =
                        curr.label === '타입' ? String(curr.value) : parseInt(curr.value, 10) || 0;
                    acc.push({ label: curr.label, value: formattedValue });
                    return acc;
                }, []);

                onSubmit(formattedValues);
            }
        };

        return (
            <div style={{ textAlign: 'center' }}>
                <div style={{ border: '1px solid #000', borderRadius: '5px', padding: '10px', display: 'inline-block' }}>
                    <h2 style={customH1Style} >좌석 등록</h2>
                    <form onSubmit={handleSubmit} style={{ margin: 0 }}>
                        {inputValues.map((field, index) => (
                            <div key={index} style={{ marginBottom: '10px' }}>
                                <label>{`${field.label} : `}</label>
                                <input style={customInputStyle}
                                       type="text"
                                       value={field.value}
                                       onChange={(e) => handleInputChange(index, e.target.value)}
                                       placeholder={`${
                                           field.label === '타입'
                                               ? '좌석 이름을 입력하세요'
                                               : field.label === '개수'
                                                   ? '숫자만 입력하세요'
                                                   : '숫자만 입력하세요'
                                       }`}
                                />
                                <br />
                            </div>
                        ))}
                        <button type="submit" >등록</button>
                    </form>
                </div>
            </div>
        );
    };

    // 좌석 추가 버튼
    const handleAddButtonClick = () => {
        const newWindow = window.open('', '_blank', 'width=400,height=220,left=100,top=100');

        ReactDOM.render(
            <SeatModalContent
                onSubmit={(enteredValues) => {
                    const formattedValues = enteredValues.reduce((acc, curr) => {
                        const formattedValue =
                            curr.label === '타입' ? String(curr.value) : parseInt(curr.value, 10) || 0;
                        acc.push({ label: curr.label, value: formattedValue });
                        return acc;
                    }, []);

                    const isDuplicateType = seatValues.some((values) => values[0].value === formattedValues[0].value);

                    if (isDuplicateType) {
                        alert('이미 동일한 타입의 좌석이 추가되었습니다.');
                        newWindow.close(); // Close the window only if it's a duplicate type
                    } else {
                        setSeatValues((prevSeatValues) => [...prevSeatValues, formattedValues]);
                        setEndDate(null); // Clear end date when adding a new seat
                        newWindow.close(); // Close the window when it's not a duplicate type
                    }
                }}
            />,
            newWindow.document.body
        );
    };

    // 좌석 삭제
    const handleRemoveSeat = (typeToRemove) => {
        const updatedSeatValues = seatValues.filter((seat) => seat[0].value !== typeToRemove);
        setSeatValues(updatedSeatValues);
    };

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    return (
        <div style={createContainerStyle} >
            <div>
                <h1 style={customH1Style} >
                    공연 등록
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>제목</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  제목을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연장 주소</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  주소를 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>전체 시작일</th>
                        <td style={customTdStyle}>
                            <div style={{ position: 'relative' }}>
                                <StyledDatePicker
                                    ref={datePickerRef}
                                    selected={startDate}
                                    onChange={(date) => setStartDateWithValidation(date)}
                                    dateFormat="yyyy년 MM월 dd일"
                                    placeholderText="  날짜를 선택하세요."
                                    locale="ko"
                                />
                                <div
                                    style={{
                                        position: 'absolute',
                                        left: '280px',
                                        top: '5px',
                                        cursor: 'pointer',
                                    }}
                                    onClick={() => datePickerRef.current && datePickerRef.current.setOpen(true)}
                                >
                                    {/* Calendar Icon */}
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 24 24"
                                        width="16"
                                        height="16"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    >
                                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>전체 종료일</th>
                        <td style={customTdStyle}>
                            <div style={{ position: 'relative' }}>
                                <StyledDatePicker
                                    ref={endDatePickerRef}
                                    selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    dateFormat="yyyy년 MM월 dd일"
                                    placeholderText="  날짜를 선택하세요."
                                    locale="ko"
                                    minDate={startDate} // Set minDate to start date
                                />
                                <div
                                    style={{
                                        position: 'absolute',
                                        left: '280px', // Adjust the left position as needed
                                        top: '5px',
                                        cursor: 'pointer',
                                    }}
                                    onClick={() => endDatePickerRef.current && endDatePickerRef.current.setOpen(true)}
                                >
                                    {/* Calendar Icon */}
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 24 24"
                                        width="16"
                                        height="16"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    >
                                        <rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
                                        <line x1="16" y1="2" x2="16" y2="6"></line>
                                        <line x1="8" y1="2" x2="8" y2="6"></line>
                                        <line x1="3" y1="10" x2="21" y2="10"></line>
                                    </svg>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 시간</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  시간을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>관람 연령</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  연령제한을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 종류</th>
                        <td style={customTdStyle}>
                            <select
                                style={customInputStyle}
                                value={selectedPerformanceType}
                                onChange={(e) => setSelectedPerformanceType(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="뮤지컬">뮤지컬</option>
                                <option value="연극">연극</option>
                                <option value="콘서트">콘서트</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연 상태</th>
                        <td style={customTdStyle}>
                            <select
                                style={customInputStyle}
                                value={selectedPerformanceStatus}
                                onChange={(e) => setSelectedPerformanceStatus(e.target.value)}
                            >
                                <option value="">선택</option>
                                <option value="공연전">공연전</option>
                                <option value="공연중">공연중</option>
                                <option value="공연완료">공연완료</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연포스터 이미지 링크</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  이미지 url 링크를 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>상세페이지 이미지 링크</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  이미지 url 링크를 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>상세페이지 텍스트 </th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  이미지 외 추가설명을 입력하세요." />
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div style={customDivStyle}>
                <button style={customButtonStyle}>공연 등록</button>
            </div>
            <div>
                <h1 style={customH1Style} >
                    일정 등록
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>일별 시작 시간</th>
                        <td style={customTdStyle}>
                            <div>
                                <button style={customButton_1Style} onClick={handleAddTimeButtonClick}>
                                    일정추가
                                </button>
                            </div>
                        </td>
                    </tr>
                    {timeSlots.map((dateTime, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={customThStyle}>
                                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                        <div>
                                            {new Date(dateTime.date).toLocaleDateString('ko-KR', {
                                                year: 'numeric',
                                                month: '2-digit',
                                                day: '2-digit',
                                            })}
                                        </div>
                                        {/* Add the remove button */}
                                        <button style={{ padding: '0px 3px' }} onClick={() => handleRemoveDateTime(dateTime.date)}>
                                            ✕
                                        </button>
                                    </div>
                                </th>
                                <td style={customTdStyle}>
                                    <div style={{ display: 'flex' }}>
                                        {dateTime.times.map((time, timeIndex) => (
                                            <div key={timeIndex} style={{ marginRight: '20px' }}>
                                                {`${time.hour}:${time.minute}`}
                                                {/* Add the remove button for time */}
                                                <button style={{ marginLeft: '5px', padding: '0px 3px' }} onClick={() => handleRemoveTime(dateTime.date, time)}>
                                                    ✕
                                                </button>
                                            </div>
                                        ))}
                                    </div>
                                </td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
            <div style={customDivStyle}>
                <button style={customButtonStyle}>일정 등록</button>
            </div>
            <div>
                <h1 style={customH1Style} >
                    좌석 등록
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '180px' }}>좌석</th>
                        <td style={customTdStyle}>
                            <button style={customButton_1Style} onClick={handleAddButtonClick}>
                                추가
                            </button>
                        </td>
                    </tr>
                    {seatValues.map((value, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={{ ...customThStyle, position: 'relative' }}>{`타입 : ${value[0].value}석`}
                                    <button
                                        style={xButtonStyle}
                                        onClick={() => handleRemoveSeat(value[0].value)}
                                    >
                                        ✕
                                    </button>
                                </th>
                                <td style={customTdStyle}>
                                    <div>{`개수 : ${value[1].value}개`}</div>
                                    <div>{`가격 : ${value[2].value}원`}</div>
                                </td>
                            </tr>
                        </React.Fragment>
                    ))}
                    </tbody>
                </table>
            </div>
            <div style={{ padding: '20px 0 0 0' }}>
                <button style={customButtonStyle}>좌석 등록</button>
            </div>
        </div>
    );
};

export default AdminCreateStage;