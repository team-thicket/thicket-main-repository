import React, {useEffect, useState} from 'react';

function Cancel3({ ticketId, ticket }) {
    const [deleteTicket, setDeleteTicket] = useState([]);

    useEffect(() => {
        // Assuming you have the correct API endpoint to delete the ticket
        fetch(`/thicket-ticket/reservations/${ticketId}`, {
            method: "DELETE",
            headers: {
                "Authorization": localStorage.getItem('token')
            }
        })
            .then(res => res.json())
            .then(data => {
                setDeleteTicket(data);
            })
    }, [ticketId]);


    return (
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
                    <h1 style={{ color: 'white'}}>취소 신청</h1>
                </div>
                <div>
                    <h1 style={{ color: 'white' }}>확인 절차</h1>
                </div>
                <div>
                    <h1>취소 완료</h1>
                </div>
            </div>
            <div style={{
                width: '80%',
                backgroundColor: 'lightgray',
                marginTop: '10px',
                padding: '5px',
                borderRadius: '12px',
                display: 'flex',
                justifyContent: 'space-around',
                alignItems: 'center', // 가로 중앙 정렬
                textAlign: 'center'   // 세로 중앙 정렬
            }}>
                <div>
                    <div>예매취소가 정상적으로 완료되었습니다.</div>
                    <div>예매내역에서 확인 바랍니다.</div>
                </div>
                {/*<div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>*/}
                {/*    <div>*/}
                {/*        <button>확인하기</button>*/}
                {/*    </div>*/}
                {/*</div>*/}
            </div>
            {/*<div style={{ display: 'flex', justifyContent: 'center' }}>*/}
            {/*    <div style={{ padding: '5px 50px', width: '80%', textAlign: 'left'}}>*/}
            {/*        <div><h1 style={{fontSize: '20px'}}>예약번호 : {`${ticket?.id?.slice(0, 4)}**${ticket?.id?.slice(-2)}`}</h1></div>*/}
            {/*        <hr />*/}
            {/*        <div>*/}
            {/*            <table>*/}
            {/*                <tbody>*/}
            {/*                <tr>*/}
            {/*                    <td style={{ width: '100px'}}>고객명</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{ticket.memberName}</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>공연제목</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{ticket.stageName}</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>장소</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{ticket.place}</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>일시</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{ticket.date}</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>좌석</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{ticket.chairType}석 {ticket.count}개</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>티켓금액</td>*/}
            {/*                    <th style={{ width: '250px', textAlign: 'left' }}>{parseInt(ticket.price).toLocaleString()}원</th>*/}
            {/*                </tr>*/}
            {/*                <tr>*/}
            {/*                    <td>취소날짜</td>*/}
                                {/*<th style={{ width: '250px', textAlign: 'left' }}>{currentDateTimeString}</th>*/}
                {/*            </tr>*/}
                {/*            </tbody>*/}
                {/*        </table>*/}
                {/*    </div>*/}
                {/*</div>*/}
                {/*<div style={{ padding: '5px 50px', width: '80%', textAlign: 'left'}}>*/}
                {/*    <div><h1 style={{fontSize: '20px'}}>결제정보</h1></div>*/}
                {/*    <hr />*/}
                {/*    <div>*/}
                {/*        <table>*/}
                {/*            <tbody>*/}
                {/*            <tr>*/}
                {/*                <td style={{ width: '100px'}}>환불방법</td>*/}
                {/*                <th style={{ width: '250px', textAlign: 'left' }}>계좌입금 or 카드취소</th>*/}
                {/*            </tr>*/}
                {/*            <tr>*/}
                {/*                <td>환불금액</td>*/}
                {/*                <th style={{ width: '250px', textAlign: 'left' }}>{parseInt(ticket.price).toLocaleString()}원</th>*/}
                {/*            </tr>*/}
                {/*            <tr>*/}
                {/*                <td>환불계좌</td>*/}
                {/*                <th style={{ width: '250px', textAlign: 'left' }}>내계좌로 줘</th>*/}
                {/*            </tr>*/}
                {/*            <tr>*/}
                {/*                <td>예금주명</td>*/}
                {/*                <th style={{ width: '250px', textAlign: 'left' }}>몰?루</th>*/}
                {/*            </tr>*/}
                {/*            </tbody>*/}
                {/*        </table>*/}
                {/*    </div>*/}
                {/*</div>*/}
            {/*</div>*/}
        </div>
    );
}

export default Cancel3;