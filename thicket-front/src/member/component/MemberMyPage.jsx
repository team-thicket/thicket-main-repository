import React, {useEffect, useState} from 'react';
import '../../assets/css/setting/MemberMyPage.css';

export const MemberMyPage = () => {
    // States to track passwords
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [memberName, setMemberName] = useState("");
    const [memberBirth, setMemberBirth] = useState("");
    const [memberEmail, setMemberEmail] = useState("");

    // State to track password check result
    const [passwordCheckResult, setPasswordCheckResult] = useState(null);
    useEffect(() => {
        fetch('/members',{
            method: "GET",
            headers: {
                "Email":'test123@gmail.com',
            },
            // cache: "no-cache"
        })
            .then(res => res.json())
            .then(data => {
                setMemberName(data.name);
                setMemberBirth(data.birth);
                setMemberEmail(data.email);
            })
    }, []);

    const changePassword = () => {

    }


    // 비밀번호 일치 확인 함수
    const handlePasswordCheck = () => {
        // Implement the logic to check the current password
        // For example, you can compare it with the actual current password
        const isPasswordCorrect = true; // Replace with your password check logic
        setPasswordCheckResult(isPasswordCorrect);
        // 현재 코드에서는 isPasswordCorrect를 항상 true로 설정하고 있어서 실제 비밀번호 확인 로직이 구현되어 있지 않습니다.
        // 이 부분을 실제로 사용자가 입력한 비밀번호와 실제 비밀번호를 비교하는 로직으로 수정해야 합니다.
    };

    // 비밀번호 변경 함수
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

    const checkPassword = () => {

    }

    return (
        <div className="member-page-container">
            <div>
                <h2 className="custom">기본정보</h2>
                <table className="info-table">
                    <tbody>
                    <tr>
                        <th>이름</th>
                        <td>{memberName}</td>
                    </tr>
                    <tr>
                        <th>생년월일</th>
                        <td>{memberBirth}</td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>{memberEmail}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br /><br />
            <div>
                <h2 className="custom">비밀번호 변경</h2>
                <table className="password-table">
                    <tbody>
                    <tr>
                        <th>기존 비밀번호</th>
                        <td>
                            <input
                                type="password"
                                id="currentPassword"
                                placeholder="비밀번호를 입력하세요."
                                value={currentPassword}
                                onChange={(e) => setCurrentPassword(e.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>신규 비밀번호</th>
                        <td>
                            <input
                                type="password"
                                id="newPassword"
                                placeholder="비밀번호를 입력하세요."
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th>신규 비밀번호 재확인</th>
                        <td>
                            <input
                                type="password"
                                id="confirmNewPassword"
                                placeholder="비밀번호를 입력하세요."
                                value={confirmNewPassword}
                                onChange={(e) => setConfirmNewPassword(e.target.value)}
                            />
                            <button className="custom" onClick={handlePasswordChange}>변경</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br /><br />
            <div>
                <button className="withdrawal-btn" onClick={handleWithdrawal}>회원탈퇴</button>
            </div>
        </div>
    );
};