import React, {useState} from 'react';
import Cancel3 from "./Cancel3";

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

function Cancel2({ tmp }) {
    const [showCancel3, setShowCancel3] = useState(false);

    const handleConfirmClick = () => {
        // showCancel3를 true로 설정하여 Cancel3 컴포넌트를 렌더링합니다.
        setShowCancel3(true);
    };
    return (
        <div style={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
            {showCancel3 ? (
                <Cancel3 tmp={tmp}/>
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
                            <h1 style={{ color: 'white' }}>취소 내역 확인</h1>
                        </div>
                        <div>
                            <h1>비밀번호 확인</h1>
                        </div>
                        <div>
                            <h1 style={{ color: 'white'}}>취소 완료</h1>
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
                        alignItems: 'center', // 상하 정렬
                        textAlign: 'center'   // 좌우 정렬
                    }}>
                        <div>
                            <div>비밀번호 확인</div>
                            <div>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인해 주세요</div>
                        </div>
                    </div>
                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                        <div>
                            비밀번호: <input type="password" />
                        </div>
                        <div>
                            <button onClick={handleConfirmClick}>확인</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Cancel2;