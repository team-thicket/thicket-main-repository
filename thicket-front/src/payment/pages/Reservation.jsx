import React, {useState} from 'react';
import Payment from "./Payment";
import MyReserveInfo from "../component/MyReserveInfo";



function Reservation() {

    const [isPaymentVisible, setPaymentVisible] = useState(false);

    const handlePaymentClick = () => {
        // "지금 결제하기" 버튼이 클릭되면 Payment 컴포넌트를 보이도록 설정
        setPaymentVisible(true);
    };

    if (isPaymentVisible) {
        return <Payment />;
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
                        <h1>예매 완료</h1>
                    </div>
                </div>

                <div style={{ width: '44%', textAlign: 'left' }}>
                    <p>[청소년극] 발가락 육상천재</p>
                </div>

                <div style={{ width: '32%', textAlign: 'left'}}>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
                        <div style={{ borderRight: '1px solid black', height: '80%',
                                    top: '10%', position: 'absolute', left: '66.7%'}}></div>
                        <p>국립극단 소극장 판</p>
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
                <MyReserveInfo />
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
                    <p style={{padding: '6px', backgroundColor: 'lightgray', borderBottom: '1px solid darkgray', borderRadius: '10px', marginBottom: '1px'}}> 예약 번호 | T123456789 (총 2매)</p>
                    <table>
                        <tbody>
                        <tr style={{height: '150px'}}>
                            <th style={{ textAlign: 'center', backgroundColor: 'lightgray', width: '23%', borderRadius: '5px'}}>
                                공연 <br /><br /><br />
                                장소 <br /><br />
                                좌석
                            </th>
                            <td style={{ textAlign: 'center', width: '80%'}}>
                                [청소년극] 발가락 육상천재보다 제목이 긴 공연<br /><br />
                                국립극단 소극장 판 <br /><br />
                                VIP석 2매
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