import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import ReadyKakaopay from "../../member/pages/ReadyKakaopay";
import Bank from "../component/Bank";


function Payment({ selectedChair, selectedTime, selectedDate, selectedQuantity }) {
    const [paymentMethod, setPaymentMethod] = useState('');
    const [show, setShow] = useState([]);       // 공연정보
    // const [times, setTimes] = useState([]);     // 공연정보-시간리스트
    // const [chairs, setChairs] = useState([]);   // 단일시간-좌석리스트

    useEffect(() => {
        // 페이지가 처음 렌더링될 때 초기 상태
        setPaymentMethod('bank');
    }, []);

    useEffect(() => { // 공연정보 (stage.stage uuid)
        fetch('/shows/stagedetail/51f864ca-1352-4b9b-80fe-359ad340c136')
            .then(response => response.json())
            .then(data => {
                setShow(data);
            });
    }, []);
    // useEffect(() => { // 공연정보 - 시간리스트 (stage.stage uuid)
    //     fetch('/tickets/all/51f864ca-1352-4b9b-80fe-359ad340c136')
    //         .then(response => response.json())
    //         .then(data => {
    //             setTimes(data);
    //         });
    // }, []);
    // useEffect(() => { // 단일시간 - 좌석리스트 (stage.stage_start uuid)
    //     fetch('/chairs/all/892c4364-a8f0-4e58-85ef-41d2f6434331')
    //         .then(response => response.json())
    //         .then(data => {
    //             setChairs(data);
    //         });
    // }, []);

    return (
        <>
            {/*Header*/}
            <div
                style={{
                    backgroundColor: 'lightgray',
                    color: 'black',
                    padding: '5px',
                    textAlign: 'center',
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    position: 'relative',
                    borderRadius: '12px',
                }}
            >
                <div style={{ width: '19%' }}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black',
                            height: '80%', top: '10%',
                            position: 'absolute', left: '20%' }}></div>
                        <h1>결제하기</h1>
                    </div>
                </div>

                <div style={{ width: '44%', textAlign: 'left' }}>
                    <p>{show.name}</p>
                </div>

                <div style={{ width: '32%', textAlign: 'left'}}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black', height: '80%',top: '10%',
                                        position: 'absolute', left: '66.7%'}}></div>
                        <p>{show.place}</p>
                    </div>
                </div>
            </div>

            {/*Content*/}
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
                    <h1 style={{borderBottom: '1.5px solid black', fontSize: '15px'}}>티켓 수령방법</h1>
                    <div className="form-check" style={{ backgroundColor: 'lightgray', borderRadius: '5px', height: '55px', padding: '5px'}}>
                        <div style={{ display: 'flex', flexDirection: 'row', marginBottom: '10px'}}>
                        <input className="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault2" checked />
                        <label className="form-check-label" htmlFor="flexRadioDefault2">
                            현장수령
                        </label>
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'row'}}>
                        <input className="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1" disabled />
                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                            모바일 티켓
                        </label>
                        </div>
                    </div>
                    <br />
                    <br />
                    <h1 style={{borderBottom: '1.5px solid black', fontSize: '15px'}}>결제 방식 선택</h1>
                    <div className="form-check" style={{ backgroundColor: 'lightgray', borderRadius: '5px', height: '105px', padding: '5px' }} >

                        <div style={{ display: 'flex', flexDirection: 'row', marginBottom: '5px' }}>
                        <input className="form-check-input"
                               type="radio"
                               name="flexRadioDefault2"
                               id="flexRadioDefault2"
                               checked={paymentMethod === 'kakao'}
                               onChange={() => setPaymentMethod('kakao')}
                        />
                        <label className="form-check-label" htmlFor="flexRadioDefault2">
                            카카오페이
                        </label>
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'row', marginBottom: '5px' }}>
                        <input className="form-check-input"
                               type="radio"
                               name="flexRadioDefault2"
                               id="flexRadioDefault2"
                               checked={paymentMethod === 'bank'}
                               onChange={() => setPaymentMethod('bank')}
                        />
                        <label className="form-check-label" htmlFor="flexRadioDefault2">
                            무통장 입금
                        </label>
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'row', marginBottom: '5px' }}>
                        <input className="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault1" disabled />
                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                            신용카드
                        </label>
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'row' }}>
                        <input className="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault1" disabled />
                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                            휴대폰 결제
                        </label>
                        </div>
                    </div>
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
                    {/*<h1 style={{fontSize: '20px'}}>예매가 정상적으로 완료되었습니다.</h1>*/}
                    {/*<p> 다음날 자정까지 결제하지 않으면 어쩌구 저쩌구 됩니다!!!</p>*/}
                    <h1 style={{ fontSize: '15px'}}>
                        {paymentMethod === 'kakao'
                        ? <ReadyKakaopay />

                            :
                                <Bank />
                            }
                    </h1>
                </div>
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '1%',
                    }}
                ></div>
                <div style={{ padding: '10px', maxHeight: '80vh', width: '30%', textAlign: 'left'}}>
                    <p style={{padding: '6px', backgroundColor: 'lightgray', borderBottom: '2px solid darkgray', borderRadius: '10px', marginBottom: '1px'}}> 예약 번호 | T123456789 (총 2매)</p>
                    <table>
                        <tbody>
                        <tr style={{height: '150px'}}>
                            <th style={{ textAlign: 'center', backgroundColor: 'lightgray', width: '25%', borderRadius: '5px'}}>
                                공연 <br /><br /><br />
                                장소 <br /><br />
                                좌석
                            </th>
                            <td style={{ textAlign: 'center', width: '80%'}}>
                                {show.name}<br /><br />
                                {show.place} <br /><br />
                                {selectedChair && selectedChair.chairType}석 {selectedQuantity}매
                            </td>
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
                        >결제하기</button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Payment;