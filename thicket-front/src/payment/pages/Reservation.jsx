import React from 'react';

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
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '33.33%',
                    }}
                ></div>
                <div
                    style={{
                        borderRight: '1px solid gray',
                        height: '90%',
                        width: '33.33%',
                    }}
                ></div>
                <div style={{ height: '100%', width: '33.33%' }}></div>
            </div>
        </>
    );
}

export default Reservation;