import React from "react";

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

export const AdminBeforeList = ({ contentHandler }) => {
    const handleTitleClick = () => {
        // contentHandler에 선택한 내용을 전달합니다.
        contentHandler("editShow");
    };
    return (
        <div style={adminContainerStyle} >
            <div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <h1 style={customH1Style} >
                        공연 예정
                    </h1>
                    <p style={{ marginLeft: '10px', fontSize: '15px', color: '#555', lineHeight: '1.7' }}>
                        제목을 클릭하면 일부 내용을 수정할 수 있습니다.
                    </p>
                </div>
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
                        <td style={customTdStyle}>3</td>
                        <td style={customTdStyle}>뮤지컬</td>
                        <td style={{ ...customTdNotCenterStyle, cursor: 'pointer' }} onClick={handleTitleClick}>
                            2023 푸에르자부르타 웨이라 인 서울
                        </td>
                        <td style={customTdStyle}>공연예정</td>
                        <td style={customTdStyle}>2024.01.14.</td>
                        <td style={customTdStyle}>2024.02.15.</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};
