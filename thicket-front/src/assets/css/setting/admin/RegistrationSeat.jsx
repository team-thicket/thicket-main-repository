import React, { useState, useRef } from 'react';
import { H2, Input, } from './StylesOfCreate';

const RegistrationSeat = ({ onSubmit }) => {
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
            newWindowRef.current && newWindowRef.current.close();
        } else {
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
                <H2>좌석 등록</H2>
                <form onSubmit={handleSubmit} style={{ margin: 0 }}>
                    {inputValues.map((field, index) => (
                        <div key={index} style={{ marginBottom: '10px' }}>
                            <label>{`${field.label} : `}</label>
                            <Input
                                type="text"
                                value={field.value}
                                onChange={(e) => handleInputChange(index, e.target.value)}
                                placeholder={`${
                                    field.label === '타입'
                                        ? '좌석 이름을 입력하세요.'
                                        : field.label === '개수'
                                            ? '숫자만 입력하세요.'
                                            : '숫자만 입력하세요.'
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

export default RegistrationSeat;