import React from "react";

function MyReserveInfo() {

    return(

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
                        2023년 11월 25일(금)<br /> 19:30 <br /><br />
                        VIP석 2매 <br /><br />
                        198,000원
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
                            2023년 11월 23일(수) 24:00
                        </div>
                        <div>
                            티켓 금액의 0~30%
                        </div>
                    </td>
                </tr>
                <hr style={{width: '315%'}} />
                <tr style={{ height: '40px'}}>
                    <th style={{ color:'#7a7a7a', textAlign: 'left', borderRadius: '5px'}}>총 결제 금액 |</th>
                    <td style={{ textAlign: 'right', borderRadius: '5px'}}>198,000 원</td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}

export default MyReserveInfo;