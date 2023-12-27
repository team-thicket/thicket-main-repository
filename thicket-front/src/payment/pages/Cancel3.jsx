import React, {useEffect} from 'react';

function Cancel3({ ticketId }) {

    useEffect(() => {

        fetch(`/thicket-ticket/reservations/${ticketId}`, {
            method: "DELETE",
            headers: {
                "Authorization": localStorage.getItem('token')
            }
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
                    <div style={{ width:'50px', backgroundColor: 'rad'}}>
                        <button>확인</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Cancel3;