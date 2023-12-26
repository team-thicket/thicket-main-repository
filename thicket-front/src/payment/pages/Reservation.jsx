import React, {useEffect, useState} from 'react';
import Payment from "./Payment";
import moment from "moment";
import MyPage from "../../member/pages/MyPage";

function Reservation({ selectedChair, selectedTime, selectedDate, selectedQuantity }) {

    const [isPaymentVisible, setPaymentVisible] = useState(false);
    const [isGoToMypage, setGoToMypage] = useState(false);
    const [show, setShow] = useState([]);       // 공연정보
    const [times, setTimes] = useState([]);     // 공연정보-시간리스트
    const [chairs, setChairs] = useState([]);   // 단일시간-좌석리스트
    const [totalAmount, setTotalAmount] = useState(0);  // 선택된 좌석의 총 금액을 저장할 새로운 상태

    useEffect(() => { // 공연정보 (stage.stage uuid)
        fetch('/shows/stagedetail/e691b03d-236f-45a1-8dcf-bd311d1563cc')
            .then(response => response.json())
            .then(data => {
                setShow(data);
            });
    }, []);
    useEffect(() => { // 공연정보 - 시간리스트 (stage.stage uuid)
        fetch('/tickets/all/e691b03d-236f-45a1-8dcf-bd311d1563cc')
            .then(response => response.json())
            .then(data => {
                setTimes(data);
            });
    }, []);
    useEffect(() => { // 단일시간 - 좌석리스트 (stage.stage_start uuid)
        fetch('/chairs/all/69e7017c-baa2-410d-97db-465f2072729f')
            .then(response => response.json())
            .then(data => {
                setChairs(data);
            });
    }, []);

    const handleGoToMypageClick = () => {
        setGoToMypage(true);
    };
    if(isGoToMypage){
        return <MyPage />;
    }

    const handlePaymentClick = () => {
        // selectedDate, selectedTime, selectedChair가 null인 경우에만 alert를 표시합니다.
        if (!selectedDate || !selectedTime || !selectedChair) {
            alert('결제를 진행하기 전에 날짜, 시간 및 좌석을 선택하세요.');
            return; // 이후 로직을 진행하지 않도록 함수를 종료합니다.
        }
        // "지금 결제하기" 버튼이 클릭되면 Payment 컴포넌트를 보이도록 설정
        setPaymentVisible(true);
    };

    if (isPaymentVisible) {
        return <Payment
            selectedChair={selectedChair}
            selectedTime={selectedTime}
            selectedDate={selectedDate}
            selectedQuantity={selectedQuantity}
            totalAmount={totalAmount}
        />;
    }
    if (!selectedChair) {
        return <div>좌석을 선택하세요.</div>;
    }
    if (!selectedTime) {
        return <div>회차를 선택하세요.</div>;
    }
    if (!selectedDate) {
        return <div>날짜를 선택하세요.</div>;
    }

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
                        <h1>예매 대기</h1>
                    </div>
                </div>

                <div style={{ width: '44%', textAlign: 'left' }}>
                    <p>{show.name}</p>
                </div>

                <div style={{ width: '32%', textAlign: 'left'}}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black', height: '80%',
                                    top: '10%', position: 'absolute', left: '66.7%'}}></div>
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
                    <h1 style={{fontSize: '20px'}}>My 예매 정보</h1>
                    <hr />
                    <table>
                        <tbody>
                        <tr style={{height: '150px'}}>
                            <th style={{ textAlign: 'center', backgroundColor: 'lightgray', borderRadius: '5px'}}>
                                예매 일시<br /><br /><br />
                                선택좌석 <br /><br />
                                티켓 금액</th>
                            <td style={{ textAlign: 'right', width: '66%'}}>
                                {moment(selectedDate).format('YYYY년 MM월 DD일')}<br /> {selectedTime.time} <br /><br />
                                {selectedChair.chairType}석 {selectedQuantity}매 <br /><br />
                                {Number(selectedChair.price * selectedQuantity).toLocaleString()}원
                            </td>
                        </tr>
                        <hr style={{width: '315%'}} />
                        <tr style={{height: '66px'}}>
                            <th style={{ textAlign: 'left', backgroundColor: 'lightgray', borderRadius: '5px', padding: '5px' }}>
                                <div style={{ marginBottom: '5px' }}>
                                    취소 기한
                                </div>
                                <div>
                                    취소 수수료
                                </div>
                            </th>
                            <td style={{ textAlign: 'right', color: 'red', fontSize: '14px'}}>
                                <div style={{ marginBottom: '5px' }}>
                                    {moment(selectedDate-3).format('YYYY년 MM월 DD일')} 24:00 까지
                                </div>
                                <div>
                                    티켓 금액의 0~30%
                                </div>
                            </td>
                        </tr>
                        <hr style={{width: '315%'}} />
                        <tr style={{ height: '40px'}}>
                            <th style={{ color:'#7a7a7a', textAlign: 'left', borderRadius: '5px'}}>총 결제 금액 |</th>
                            <td style={{ textAlign: 'right', borderRadius: '5px'}}>
                                {Number(selectedChair.price * selectedQuantity).toLocaleString()}원</td>
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
                    <h1 style={{fontSize: '20px'}}>예매 줄서기를 완료했습니다!! </h1>
                    <p> THICKETER의 요청을 모두 받고있어요. <br /><br /> 예매 완료 여부는 <br />마이페이지에서 알려드리겠습니다!
                    <br /><br /> 마이페이지에서 기다려주세요! </p>
                </div>
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '1%',
                    }}
                ></div>
                <div style={{ padding: '10px', maxHeight: '80vh', width: '30%', textAlign: 'left'}}>
                    <p style={{padding: '6px', backgroundColor: 'lightgray', borderBottom: '1px solid darkgray', borderRadius: '10px', marginBottom: '1px'}}> 예약 번호 | T123456789 (총 2매)</p>
                    <table>
                        <tbody>
                        <tr style={{height: '150px'}}>
                            <th style={{ textAlign: 'center', backgroundColor: 'lightgray', width: '25%', borderRadius: '5px'}}>
                                공연 <br /><br /><br />
                                장소 <br /><br />
                                좌석
                            </th>
                            <td style={{ textAlign: 'center', width: '80%', padding: '15px'}}>
                                {show.name}<br /><br />
                                {show.place} <br /><br />
                                {selectedChair.chairType}석 {selectedQuantity}매
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <br />
                    <br />
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                        <button
                            onClick={handlePaymentClick}
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
                            onClick={handleGoToMypageClick}
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
                        >마이페이지로 이동하기</button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Reservation;