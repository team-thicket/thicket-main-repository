import styled from "styled-components";
import Menu from "../component/Menu";
import {MemberMyPage} from "../component/MemberMyPage";
import Withdraw from "../component/Withdraw";
import React, {useState} from "react";

const Wrapper = styled.div`
  display: flex;
  width: 100%;
`
const ASide = styled.div`
  align-items: center;
  padding: 10px;
  margin: 20px;
  border: black solid 1px;
  border-radius: 5px;
  width: 15%;
  height: 100%;
  display: inline-block;
`
const Main = styled.div`
  padding: 10px;
  margin-bottom: 20px;
  margin-right: 20px;
  margin-top: 20px;
  border: black solid 1px;
  border-radius: 5px;
  width: 77%;
  height: 100%;
  display: inline-block;
`
const Mypage = () => {
    const [content, setContent] = useState("mypage");
    const contentHandler = (e) => {
        setContent(e.target.name);
    }
    const selectComponent = {
        mypage: <MemberMyPage contentHandler={contentHandler}/>,
        withdraw: <Withdraw />
    };
    return(
        <Wrapper>
            <ASide>
                <Menu name={"회원 정보"} />
                <Menu name={"예매 내역"} />
                <Menu name={"관람 내역"} />
                <Menu name={"보유 쿠폰"} />
            </ASide>
            {content && <Main>{selectComponent[content]}</Main>}
        </Wrapper>
    )
}

export default Mypage;