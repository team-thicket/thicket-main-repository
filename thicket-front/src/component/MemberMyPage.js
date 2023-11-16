import React, { useState } from 'react';

export const MemberMyPage = () => {
    // States to track passwords
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');

    // State to track password check result
    const [passwordCheckResult, setPasswordCheckResult] = useState(null);

    // Function to handle password check
    const handlePasswordCheck = () => {
        // Implement the logic to check the current password
        // For example, you can compare it with the actual current password
        const isPasswordCorrect = true; // Replace with your password check logic
        setPasswordCheckResult(isPasswordCorrect);
    };

    // Function to handle password change
    const handlePasswordChange = () => {
        // Implement the logic to update the password here
        if (newPassword === confirmNewPassword) {
            console.log('Current Password:', currentPassword);
            console.log('New Password:', newPassword);
            // You may want to send a request to the server to update the password
            // Reset password check result
            setPasswordCheckResult(null);
        } else {
            // Handle password mismatch
            console.error('New passwords do not match');
            // You may want to display an error message to the user
        }
    };

    const handleWithdrawal = () => {
        // Implement the logic to handle withdrawal
        // For example, you can send a request to the server to process the withdrawal
        console.log('Withdrawal logic here');
    };

    return (
        <div style={{ border: '2px solid #000', borderRadius: '10px', padding: '20px', width: '950px', margin: '10px' }}>
            <div>
                <h1 style={{ margin: '10px 0 0 0' }}>기본정보</h1>
                <table style={{ width: '100%', border: '2px solid #000' }}>
                    <tbody>
                    <tr style={{ borderBottom: '2px solid #000', background: '#f1f1f1' }}>
                        <th style={{ textAlign: 'left', padding: '10px', width: '100px' }}>이름</th>
                        <td style={{ textAlign: 'left', padding: '10px' }}>값을 받아온 이름</td>
                    </tr>
                    <tr style={{ borderBottom: '2px solid #000', background: '#f1f1f1' }}>
                        <th style={{ textAlign: 'left', padding: '10px', width: '100px' }}>생년월일</th>
                        <td style={{ textAlign: 'left', padding: '10px' }}>yyyy년 MM월 dd일</td>
                    </tr>
                    <tr style={{ borderBottom: '2px solid #000', background: '#f1f1f1' }}>
                        <th style={{ textAlign: 'left', padding: '10px', width: '100px' }}>이메일</th>
                        <td style={{ textAlign: 'left', padding: '10px' }}>값을받아온@이메일.주소</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <hr />
            <p>비밀번호 변경</p>
            <div>
                <input
                    type="password"
                    id="currentPassword"
                    placeholder="기존 비밀번호"
                    value={currentPassword}
                    onChange={(e) => setCurrentPassword(e.target.value)}
                />
                <button onClick={handlePasswordCheck} style={{ margin: '0 0 0 10px' }}>확인</button>
                {passwordCheckResult === false && (
                    <p style={{ color: 'red' }}>비밀번호가 일치하지 않습니다.</p>
                )}
            </div>
            <div style={{ margin: '20px 0 10px 0' }}>
                <input
                    type="password"
                    id="newPassword"
                    placeholder="신규 비밀번호"
                    value={newPassword}
                    onChange={(e) => setNewPassword(e.target.value)}
                />
            </div>
            <div style={{ margin: '10px 0' }}>
                <input
                    type="password"
                    id="confirmNewPassword"
                    placeholder="신규 비밀번호 재확인"
                    value={confirmNewPassword}
                    onChange={(e) => setConfirmNewPassword(e.target.value)}
                />
                <button onClick={handlePasswordChange} style={{ margin: '0 0 0 10px' }}>변경</button>
            </div>
            <button
                onClick={handleWithdrawal}
                style={{
                    margin: '10px 0',
                    padding: '10px',
                    background: 'red',
                    color: 'white',
                    cursor: 'pointer',
                    borderRadius: '5px', // Adjust the value for the desired roundness
                }}
            >
                회원탈퇴
            </button>
        </div>
    );
};
