import styled from "styled-components";
import Menu from "../component/Menu";
import {MemberMyPage} from "../component/MemberMyPage";
import Withdraw from "../component/Withdraw";
import React, {useState} from "react";
import {MemberTicketingList} from "../component/MemberTicketingList";
import {MemberViewingList} from "../component/MemberViewingList";
import {MemberCouponList} from "../component/MemberCouponList";

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
`
const ASide = styled.div`
  align-items: center;
  padding: 10px;
  margin: 70px 1078px 20px 20px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  border: black solid 1px;
  border-radius: 5px;
  width: 160px;
  display: inline-block;
  position: fixed; // 위치 고정
`
const Main = styled.div`
  padding: 10px;
  margin: 70px 20px 20px 222px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  border: black solid 1px;
  border-radius: 5px;
  width: 1016px;
  height: auto;
  display: inline-block;
  position: fixed;
`
const Mypage = () => {
    const [content, setContent] = useState("mypage");
    const contentHandler = (selectedContent) => {
        setContent(selectedContent);
    }
    const selectComponent = {
        mypage: <MemberMyPage contentHandler={contentHandler}/>,
        withdraw: <Withdraw />,
        ticketing: <MemberTicketingList />,
        viewing: <MemberViewingList />,
        coupon: <MemberCouponList />
    };
    return(
        <Wrapper>
            <ASide>
                <Menu name={"회원 정보"} onClick={() => contentHandler("mypage")} />
                <Menu name={"예매 내역"} onClick={() => contentHandler("ticketing")} />
                <Menu name={"관람 내역"} onClick={() => contentHandler("viewing")} />
                <Menu name={"보유 쿠폰"} onClick={() => contentHandler("coupon")} />
            </ASide>
            {content && <Main>{selectComponent[content]}</Main>}
        </Wrapper>
    )
}

export default Mypage;