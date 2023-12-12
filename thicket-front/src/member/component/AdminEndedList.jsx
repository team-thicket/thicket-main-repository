const adminContainerStyle = {
    padding: '10px',
    overflowY: 'auto', // Add overflow property for vertical scrollbar
    maxHeight: '80vh', // Set a max height to trigger scrollbar
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

export const AdminEndedList = () => {
    return (
        <div style={adminContainerStyle} >
            <div>
                <h1 style={customH1Style} >
                    공연 종료
                </h1>
                <table style={infoTableStyle} >
                    <tbody>
                    <tr>
                        <th style={{ ...customThStyle, width: '60px' }}>번호</th>
                        <th style={{ ...customThStyle, width: '60px' }}>구분</th>
                        <th style={{ ...customThStyle, width: 'auto' }}>제목</th>
                        <th style={{ ...customThStyle, width: '70px' }}>상태</th>
                        <th style={{ ...customThStyle, width: '102px' }}>오픈일</th>
                        <th style={{ ...customThStyle, width: '102px' }}>마감일</th>
                    </tr>
                    <tr>
                        <td style={customTdStyle}>1</td>
                        <td style={customTdStyle}>콘서트</td>
                        <td style={customTdNotCenterStyle}>
                            임영웅 콘서트 IM HERO TOUR 2023 - 부산
                        </td>
                        <td style={customTdStyle}>공연종료</td>
                        <td style={customTdStyle}>2023.11.08.</td>
                        <td style={customTdStyle}>2023.11.10.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};
