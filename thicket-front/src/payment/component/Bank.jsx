import React, {useRef, useState} from "react";
import useDetectClose from "./UseDetectClose";
import {Dropdown} from "./Dropdown";

function Bank() {
    const dropDownRef = useRef();
    const [identify, setIdentify] = useState('선택해주세요');
    const [generatedNumber, setGeneratedNumber] = useState(null);
    const list = ['농협은행', '국민은행', '하나은행'];

    const generateNumber = (bank) => {
        switch (bank) {
            case '농협은행':
                return '12345';
            case '국민은행':
                return '45678';
            case '하나은행':
                return '11111';
            default:
                return '';
        }
    };

    const [isOpen, setIsOpen] = useDetectClose(dropDownRef, false);

    const handleBankSelection = (selectedBank) => {
        const generatedNum = generateNumber(selectedBank);
        setGeneratedNumber(generatedNum);
        setIdentify(selectedBank);
        setIsOpen(false);
    };

    return (
        <div style={{ padding: '10px', maxHeight: '80vh', width: '90%', textAlign: 'left' }}>
            <p style={{ padding: '6px', borderBottom: '2px solid darkgray' }}>
                결제 수단 입력
            </p>
            <p style={{ width: '93%', backgroundColor: 'lightgray', padding: '6px',
                        borderRadius: '5px', marginTop: '-13px', marginLeft: '1px'}}> > 무통장 입금 </p>
            <table style={{ borderBottom: '2px solid darkgray', marginTop: '-15px' }}>
                <tbody>
                <tr style={{ height: '110px' }}>
                    <th style={{ textAlign: 'center', backgroundColor: 'lightgray',
                                    width: '31%', borderRadius: '5px' }}>
                        입금액<br /><br />
                        <p style={{ fontSize: '13', padding: '3px' }}>입금 하실 은행</p>
                    </th>
                    <td style={{ textAlign: 'right', padding: '8px' }}>
                        000,000원 <br /><br />

                        <div ref={dropDownRef}>
                            <button
                                onClick={() => setIsOpen(!isOpen)}
                                style={{ padding: '5px', cursor: 'pointer',
                                            border: 'none' ,borderRadius: '5px'}}
                            >
                                {identify}
                            </button>
                            {isOpen &&
                                <div style={{cursor: 'pointer'}}>
                                    {list.map((value) => (
                                        <Dropdown
                                                  key={value}
                                                  value={value}
                                                  setIsOpen={setIsOpen}
                                                  setIdentify={handleBankSelection}
                                                  isOpen={isOpen} />
                                    ))}
                                </div>
                            }
                        </div>
                        {generatedNumber &&
                            <div style={{ marginTop: '5px', fontSize: '15px' }}>
                                계좌번호: {generatedNumber}
                            </div>}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
}

export default Bank;