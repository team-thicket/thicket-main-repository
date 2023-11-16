import React from "react";
import "./withdraw.css";
import "../../assets/css/setting/fonts.css";
import "../../assets/css/setting/mixin.css";

const Withdraw = () => {
    const handleWithdrawal = () => {
        // 탈퇴 로직을 이곳에 추가
        // 예를 들어, API 호출 등을 사용하여 서버에 탈퇴 요청을 보낼 수 있습니다.
        console.log("사용자가 탈퇴하기 버튼을 클릭했습니다.");
      };
    return (
        <section id="withdraw" className="withdraw">
        <div>
        <h1 className="withdraw_title"><b> 탈퇴하기 </b></h1>
        <hr></hr>
        <br></br>
            
            <p>탈퇴 시 해당 계정 내 모든 예매 정보는 삭제되며,</p>
            <p>계정 복구는 불가능합니다.</p>
            <p>탈퇴한 이메일로 재가입은 불가능합니다.</p>
            <br></br>
            <h3 className="red-text">정말 탈퇴하시겠습니까?</h3>
            
        <br></br>
       
        <button className="withdrawal-button" onClick={handleWithdrawal}>
        탈퇴하기
      </button>
      </div>
        </section>
    );
};

export default Withdraw;