const adminContainerStyle = {
    padding: '10px',
};

const infoTableStyle = {
    width: '100%',
    borderCollapse: 'collapse',
};

const customH1Style = {
    margin: '0 0 20px 0',
    paddingLeft: '5px',
};

const customThStyle = {
    textAlign: 'center',
    height: '29px',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: '#f7f7f7',
    width: '180px',
    borderTop: '1px solid #000',
};

const customTdStyle = {
    textAlign: 'center',
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: 'white',
    borderTop: '1px solid #000',
};

const customTdNotCenterStyle = {
    padding: '10px',
    borderBottom: '1px solid #ccc',
    background: 'white',
    borderTop: '1px solid #000',
};

export const AdminConcertList = () => {
    return (
        <div style={adminContainerStyle} >
            <div>
                <h1 style={customH1Style} >
                    콘서트 목록
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '6%' }}>번호</th>
                        <th style={{ ...customThStyle, width: '8%' }}>구분</th>
                        <th style={{ ...customThStyle, width: 'auto' }}>제목</th>
                        <th style={{ ...customThStyle, width: '8%' }}>상태</th>
                        <th style={{ ...customThStyle, width: '10%' }}>오픈일</th>
                        <th style={{ ...customThStyle, width: '10%' }}>마감일</th>
                    </tr>
                    <tr>
                        <td style={customTdStyle}>3</td>
                        <td style={customTdStyle}>콘서트</td>
                        <td style={customTdNotCenterStyle}>
                            임영웅 콘서트 IM HERO TOUR 2023 - 부산
                        </td>
                        <td style={customTdStyle}>오픈전</td>
                        <td style={customTdStyle}>2023.12.08.</td>
                        <td style={customTdStyle}>2023.12.10.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};
