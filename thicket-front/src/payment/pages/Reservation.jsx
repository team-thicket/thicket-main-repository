import React from 'react';
import styled from "styled-components";

function Reservation() {
    return (
    
        <>

            {/* Header */}
            <div
                style={{
                    backgroundColor: 'lightgray',
                    color: 'black',
                    padding: '5px',
                    textAlign: 'center',
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    position: 'relative'
                }}
            >
                <div style={{ width: '19%' }}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black', height: '80%', top: '10%', position: 'absolute', left: '20%' }}></div>
                        <h1>예매 완료</h1>
                    </div>
                </div>

                <div style={{ width: '44%', textAlign: 'left' }}>
                    <p>[청소년극] 발가락 육상천재 보다 제목이 더 긴 공연이 있을 수도 있음</p>
                </div>

                <div style={{ width: '32%', textAlign: 'left'}}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black', height: '80%',top: '10%', position: 'absolute', left: '66.7%'}}></div>
                    <p>국립극단 소극장 판보다 더 이름이 긴 공연장이 있다면</p>
                    </div>
                </div>
            </div>

            {/* Content */}
            <div
                style={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '80vh',
                    backgroundColor: 'white',
                }}
            >
                <div style={{ padding: '5px', maxHeight: '80vh', width: '31%', textAlign: 'left'}}>
                    <h1 style={{fontSize: '20px'}}>My 예매 정보</h1>
                    <hr />
                    <table>
                        <tbody>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>예매 일시</th>
                            <td style={{ textAlign: 'right', width: '66%'}}>2023년 11월 25일(금)<br /> 19:30</td>
                        </tr>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>선택 좌석</th>
                            <td style={{ textAlign: 'right'}}>VIP석 2매</td>
                        </tr>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>티켓 금액</th>
                            <td style={{ textAlign: 'right'}}>198,000원</td>
                        </tr>
                        <hr style={{width: '315%'}} />
                        <tr style={{height: '30px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>취소 기한</th>
                            <td style={{ textAlign: 'right', color: 'red', fontSize: '14px'}}>2023년 11월 23일(수) 24:00</td>
                        </tr>
                        <tr style={{height: '30px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray', fontSize: '15px'}}>취소 수수료</th>
                            <td style={{ textAlign: 'right', color: 'red', fontSize: '14px'}}>티켓 금액의 0~30%</td>
                        </tr>
                        <hr style={{width: '315%'}} />
                        <tr style={{backgroundColor: 'lightgray', height: '40px'}}>
                            <th style={{ textAlign: 'left'}}>총 결제 금액 |</th>
                            <td style={{ textAlign: 'right'}}>198,000 원</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <br />
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '1%',
                    }}
                >
                </div>
                <div style={{ padding: '5px', width: '33%', textAlign: 'center', justifyContent: 'center'}}>
                    <h1 style={{fontSize: '20px'}}>예매가 정상적으로 완료되었습니다.</h1>
                    <p> 다음날 자정까지 결제하지 않으면 어쩌구 저쩌구 됩니다!!!</p>
                </div>
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '1%',
                    }}
                ></div>
                <div style={{ padding: '10px', maxHeight: '80vh', width: '30%', textAlign: 'left'}}>
                   <p style={{borderBottom: '1px solid black'}}> 예약 번호 | T123456789 (총 2매)</p>
                    <table>
                        <tbody>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray', width: '23%'}}>공연</th>
                            <td style={{ textAlign: 'center', width: '80%'}}>[청소년극] 발가락 육상천재보다 제목이 긴 공연</td>
                        </tr>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>장소</th>
                            <td style={{ textAlign: 'center'}}>국립극단 소극장 판</td>
                        </tr>
                        <tr style={{height: '50px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray'}}>좌석</th>
                            <td style={{ textAlign: 'center'}}>VIP석 2매</td>
                        </tr>
                        </tbody>
                    </table>
                    <br />
                    <br />
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <button
                        style={{
                            padding: '10px 12px',
                            backgroundColor: '#ff9898',
                            color: '#ffffff',
                            border: 'none',
                            borderRadius: '12px',
                            width: '250px',
                            height: '45px',
                            fontSize: '15px',
                            cursor: 'pointer',
                        }}
                        onMouseOver={(e) => {
                            e.target.style.backgroundColor = '#ff7b7b';
                        }}
                        onMouseOut={(e) => {
                            e.target.style.backgroundColor = '#ff9898';
                        }}
                    >지금 결제하기</button>
                    <br />
                    <button
                        style={{
                            padding: '10px 12px',
                            backgroundColor: '#b0b0b0',
                            color: '#ffffff',
                            border: 'none',
                            borderRadius: '12px',
                            width: '250px',
                            height: '45px',
                            fontSize: '15px',
                            cursor: 'pointer',
                        }}
                        onMouseOver={(e) => {
                            e.target.style.backgroundColor = '#9a9999';
                        }}
                        onMouseOut={(e) => {
                            e.target.style.backgroundColor = '#b0b0b0';
                        }}
                    >나중에 결제하기</button>
                    </div>
                </div>
            </div>

        </>
    );
}

export default Reservation;