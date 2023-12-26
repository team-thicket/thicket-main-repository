import React, {useState} from 'react';
import Cancel2 from "./Cancel2";

function Cancel() {

    const tmp = { // 예매(결제)취소 - 예매만 된 티켓 ,결제까지 완료된 티켓 나눠서 로직 처리.
        "id": "e4e64cbb-cc73-40b4-805e-62be18ab47e4",
        "created": "2023.09.09.",
        "memberName": "홍길동",
        "stageName": "뮤지컬 <마리퀴리>",
        "date": "2023.12.31. 09:30",
        "stagetype": "Musical",
        "place": "홍익대 대학로 아트센터 대극장",
        "showStatus": "수령방법",
        "cancelDate": "취소기한(오픈3일전)",
        "howreceive": "수령방법",// 여기서는 안쓸듯
        "method": "이건 뭔데",
        "status": "상태 뭔데",
        "chairType": "VIP", // API 명세서에 없음
        "count": "1",       // API 명세서에 없음
        "price": "99500",   // API 명세서에 없음
    };

    const [showCancel2, setShowCancel2] = useState(false);

    const handleCancel2Click = () => {
        // showCancel2를 true로 설정하여 Cancel2 컴포넌트를 렌더링합니다.
        setShowCancel2(true);
    };

    return (
        <div style={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
            {showCancel2 ? (
                <Cancel2 tmp={tmp} />
            ) : (
                <div style={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <div style={{
                        backgroundColor: 'lightgray',
                        color: 'black',
                        padding: '5px',
                        textAlign: 'center',
                        display: 'grid',
                        gridTemplateColumns: 'repeat(3, 1fr)',  // 추가된 부분
                        justifyContent: 'space-between',
                        alignItems: 'center',
                        position: 'relative',
                        borderRadius: '12px',
                        width:'100%',
                    }}
                    >
                        <div >
                            <h1>취소 내역 확인</h1>
                        </div>
                        <div>
                            <h1 style={{ color: 'white' }}>비밀번호 확인</h1>
                        </div>
                        <div>
                            <h1 style={{ color: 'white'}}>취소 완료</h1>
                        </div>
                    </div>
                    <div style={{ width: '80%', backgroundColor: 'lightgray', marginTop: '10px', padding: '5px', borderRadius: '12px', display: 'flex', justifyContent: 'space-around' }}>
                        <div>
                            <div>본 예매 내역을 취소하시겠습니까?</div>
                            <div>취소/환불 내역을 확인한 후 [예매취소하기] 버튼을 클릭해 주시기 바랍니다.</div>
                        </div>
                        <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                            <div>
                                <button onClick={handleCancel2Click}>예매취소하기</button>
                            </div>
                        </div>
                    </div>
                    <div style={{ display: 'flex', justifyContent: 'center' }}>
                        <div style={{ padding: '5px 50px', width: '80%', textAlign: 'left'}}>
                            <div><h1 style={{fontSize: '20px'}}>예약번호 : {tmp.id.slice(0, 4)}**{tmp.id.slice(-2)}</h1></div>
                            <hr />
                            <div>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td style={{ width: '100px'}}>고객명</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.memberName}</th>
                                    </tr>
                                    <tr>
                                        <td>공연제목</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.stageName}</th>
                                    </tr>
                                    <tr>
                                        <td>장소</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.place}</th>
                                    </tr>
                                    <tr>
                                        <td>일시</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.date}</th>
                                    </tr>
                                    <tr>
                                        <td>좌석</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.chairType}석 {tmp.count}개</th>
                                    </tr>
                                    <tr>
                                        <td>티켓금액</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{parseInt(tmp.price).toLocaleString()}원</th>
                                    </tr>
                                    <tr>
                                        <td>취소기한</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{tmp.cancelDate}</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div style={{ padding: '5px 50px', width: '80%', textAlign: 'left'}}>
                            <div><h1 style={{fontSize: '20px'}}>결제정보</h1></div>
                            <hr />
                            <div>
                                <table>
                                    <tbody>
                                    <tr>
                                        <td style={{ width: '100px'}}>환불방법</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>계좌입금 or 카드취소</th>
                                    </tr>
                                    <tr>
                                        <td>환불금액</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>{parseInt(tmp.price).toLocaleString()}원</th>
                                    </tr>
                                    <tr>
                                        <td>환불계좌</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>내계좌로 줘</th>
                                    </tr>
                                    <tr>
                                        <td>예금주명</td>
                                        <th style={{ width: '250px', textAlign: 'left' }}>몰?루</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Cancel;