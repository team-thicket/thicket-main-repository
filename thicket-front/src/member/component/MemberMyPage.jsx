import React, {useEffect, useState} from 'react';
import '../../assets/css/setting/MemberMyPage.css';

export const MemberMyPage = ({contentHandler}) => {
    // States to track passwords
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [memberName, setMemberName] = useState("");
    const [memberBirth, setMemberBirth] = useState("");
    const [memberEmail, setMemberEmail] = useState("");

    // State to track password check result
    const [passwordCheckResult, setPasswordCheckResult] = useState(true);
    useEffect(() => {
        fetch('/members',{
            method: "GET",
            headers: {
                "Email":'test123@gmail.com'
            }
        })
            .then(res => res.json())
            .then(data => {
                setMemberName(data.name);
                setMemberBirth(data.birth);
                setMemberEmail(data.email);
            })
    }, []);

    useEffect(() => {
        if (newPassword !=="" && confirmNewPassword !== "" && newPassword !== confirmNewPassword) {
            setPasswordCheckResult(false)
            return;
        }
        setPasswordCheckResult(true)
    }, [newPassword,confirmNewPassword]);

    // 비밀번호 변경 함수
    const handlePasswordChange = () => {
        if (newPassword !== confirmNewPassword) {
            alert("비밀 번호와 비밀번호 확인이 서로 다릅니다.")
            return;
        }
        if (currentPassword.trim() === "" || newPassword.trim() === "" || confirmNewPassword.trim() === "") {
            alert("비밀 번호 변경란을 모두 입력해 주십시오.")
            return;
        }
        fetch('/members',{
            method: "PATCH",
            headers: {
                'Email':'test123@gmail.com',
                'Content-Type':'application/json'
            },
            body: JSON.stringify({currentPassword,newPassword})
        })
            .then(res => res.text())
            .then(data => alert(data));

        setCurrentPassword("");
        setConfirmNewPassword("");
        setNewPassword("");
    };

    return (
        <div className="member-page-container">
            <div>
                <h1 className="custom">기본정보</h1>
                <table className="info-table">
                    <tbody>
                    <tr>
                        <th className="custom">이름</th>
                        <td className="custom">{memberName}</td>
                    </tr>
                    <tr>
                        <th className="custom" >생년월일</th>
                        <td className="custom" >{memberBirth}</td>
                    </tr>
                    <tr>
                        <th className="custom" >이메일</th>
                        <td className="custom" >{memberEmail}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br />
            <div className="custom">
                <h1 className="custom">비밀번호 변경</h1>
                <table className="password-table">
                    <tbody>
                    <tr>
                        <th className="custom">기존 비밀번호</th>
                        <td className="custom">
                            <input className="custom"
                                   type="password"
                                   id="currentPassword"
                                   placeholder="  비밀번호를 입력하세요."
                                   value={currentPassword}
                                   onChange={(e) => setCurrentPassword(e.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th className="custom">신규 비밀번호</th>
                        <td className="custom">
                            <input className="custom"
                                   type="password"
                                   id="newPassword"
                                   placeholder="  비밀번호를 입력하세요."
                                   value={newPassword}
                                   onChange={(e) => setNewPassword(e.target.value)}
                            />
                        </td>
                    </tr>
                    <tr>
                        <th className="custom">신규 비밀번호 재확인</th>
                        <td className="custom">
                            <input className="custom"
                                   type="password"
                                   id="confirmNewPassword"
                                   placeholder="  비밀번호를 입력하세요."
                                   value={confirmNewPassword}
                                   onChange={(e) => setConfirmNewPassword(e.target.value)}
                            />
                            <button className="custom" onClick={handlePasswordChange}>변경</button>
                            {passwordCheckResult === false && (
                                <p style={{ color: 'red', fontSize:'15px'}}>비밀번호가 일치하지 않습니다.</p>
                            )}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div className="custom-right">
                <button className="withdrawal-btn" onClick={contentHandler} name={"withdraw"}>회원탈퇴</button>
            </div>
        </div>
    );
};