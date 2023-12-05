import React, { useState, useRef } from 'react';
import DatePicker, {registerLocale} from 'react-datepicker';
import ko from 'date-fns/locale/ko';
import 'react-datepicker/dist/react-datepicker.css';
import ReactDOM from 'react-dom';
import styled from "styled-components";

const createContainerStyle = {
    padding: '10px',
};

const customDivStyle = {
    paddingTop: '40px',
};

const infoTableStyle = {
    width: '100%',
    borderCollapse: 'collapse',
};

const customH1Style = {
    margin: '0 0 10px 0',
    paddingLeft: '5px',
};

const customThStyle = {
    textAlign: 'left', // Changed to left alignment
    height: '29px',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: '#f7f7f7',
    width: '180px',
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

const StyledDatePicker = styled(DatePicker)`
    width: 300px;
    height: 25px;
    box-sizing: border-box;
`;

const AdminCreateStage = () => {
    const [seatValues, setSeatValues] = useState([]);
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [selectedPerformanceType, setSelectedPerformanceType] = useState('');
    const [selectedPerformanceStatus, setSelectedPerformanceStatus] = useState('');
    const datePickerRef = useRef(null);
    const endDatePickerRef = useRef(null);
    const [timeSlots, setTimeSlots] = useState([]);

    registerLocale('ko', ko);

    const SeatModalContent = ({ onSubmit }) => {
        const [inputValues, setInputValues] = useState([
            { label: '타입', value: '' },
            { label: '개수', value: '' },
            { label: '가격', value: '' },
        ]);

        const handleInputChange = (index, value) => {
            setInputValues((prevInputValues) => {
                const newValues = [...prevInputValues];
                newValues[index].value = value;
                return newValues;
            });
        };

        const handleSubmit = (e) => {
            e.preventDefault();

            const formattedValues = inputValues.reduce((acc, curr) => {
                const formattedValue =
                    curr.label === '타입' ? String(curr.value) : parseInt(curr.value, 10) || 0;
                acc.push({ label: curr.label, value: formattedValue });
                return acc;
            }, []);

            onSubmit(formattedValues);
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
                        <button type="submit" >확인</button>
                    </form>
                </div>
            </div>
        );
    };

    const handleAddButtonClick = () => {
        const newWindow = window.open('', '_blank', 'width=400,height=202,left=100,top=100');

        ReactDOM.render(
            <SeatModalContent
                onSubmit={(enteredValues) => {
                    const formattedValues = enteredValues.reduce((acc, curr) => {
                        const formattedValue =
                            curr.label === '타입' ? String(curr.value) : parseInt(curr.value, 10) || 0;
                        acc.push({ label: curr.label, value: formattedValue });
                        return acc;
                    }, []);

                    setSeatValues((prevSeatValues) => [...prevSeatValues, formattedValues]);
                    setEndDate(null); // Clear end date when adding a new seat
                    newWindow.close();
                }}
            />,
            newWindow.document.body
        );
    };

    const TimeSelection = ({ onConfirm }) => {
        const [selectedHour, setSelectedHour] = useState('');
        const [selectedMinute, setSelectedMinute] = useState('');

        const handleConfirm = () => {
            if (selectedHour !== '' && selectedMinute !== '') {
                onConfirm(`${selectedHour}시 ${selectedMinute}분`);
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
                    <h2 style={customH1Style}>시간 선택</h2>
                    <div>
                        <select
                            style={customInputTimeStyle}
                            value={selectedHour}
                            onChange={(e) => setSelectedHour(e.target.value)}
                        >
                            <option value="">선택</option>
                            {Array.from({ length: 24 }, (_, i) => i.toString().padStart(2, '0')).map((hour) => (
                                <option key={hour} value={hour}>
                                    {hour}
                                </option>
                            ))}
                        </select>시
                        <select
                            style={customInputTimeStyle}
                            value={selectedMinute}
                            onChange={(e) => setSelectedMinute(e.target.value)}
                        >
                            <option value="">선택</option>
                            <option value="00">00</option>
                            <option value="30">30</option>
                        </select>분
                    </div>
                    <button type="button" onClick={handleConfirm}>
                        확인
                    </button>
                </div>
            </div>
        );
    };

    const handleAddTimeButtonClick = () => {
        const newWindow = window.open('', '_blank', 'width=400,height=202,left=100,top=100');

        ReactDOM.render(
            <TimeSelection
                onConfirm={(selectedTime) => {
                    setTimeSlots((prevTimeSlots) => [...prevTimeSlots, selectedTime]);
                    newWindow.close();
                }}
            />,
            newWindow.document.body
        );
    };


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
                        <th style={customThStyle}>공연장주소</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  주소를 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>시작일 ver 1</th>
                        <td style={customTdStyle}>
                            <input
                                type="date"
                            />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>시작일</th>
                        <td style={customTdStyle}>
                            <div style={{ position: 'relative' }}>
                                <StyledDatePicker
                                    ref={datePickerRef}
                                    selected={startDate}
                                    onChange={(date) => setStartDate(date)}
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
                        <th style={customThStyle}>종료일</th>
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
                        <th style={customThStyle}>시작시간</th>
                        <td style={customTdStyle}>
                            <div>
                                <button style={customButton_1Style} onClick={handleAddTimeButtonClick}>
                                    시간 추가
                                </button>
                            </div>
                        </td>
                    </tr>
                    {timeSlots.map((time, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={customThStyle}>{`선택${index + 1}`}</th>
                                <td style={customTdStyle}>{time}</td>
                            </tr>
                        </React.Fragment>
                    ))}
                    <tr>
                        <th style={customThStyle}>공연시간</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  시간을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>관람연령</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  연령제한을 입력하세요." />
                        </td>
                    </tr>
                    <tr>
                        <th style={customThStyle}>공연종류</th>
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
                        <th style={customThStyle}>공연 상세 이미지 링크</th>
                        <td style={customTdStyle}>
                            <input style={customInputStyle} placeholder="  이미지 url 링크를 입력하세요." />
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
                        <th style={customThStyle}>좌석</th>
                        <td style={customTdStyle}>
                            <button style={customButton_1Style} onClick={handleAddButtonClick}>
                                추가
                            </button>
                        </td>
                    </tr>
                    {seatValues.map((value, index) => (
                        <React.Fragment key={index}>
                            <tr>
                                <th style={customThStyle}>{`타입 : ${value[0].value}석`}</th>
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
            <div style={customDivStyle}>
                <button style={customButtonStyle}>등록</button>
            </div>
        </div>
    );
};

export default AdminCreateStage;