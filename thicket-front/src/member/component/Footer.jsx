import {useState} from "react";
import {redirect} from "react-router-dom";
import styled from "styled-components";
const modalWrapper = styled.div`
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.1);
`;
const modalContent = styled.div``;
const modalBody = styled.div`
  width: 500px;
  height: 300px;
  padding: 30px 30px;
  margin: 0 auto;
  border: 1px solid #777;
  background-color: #fff;
`;


function Footer() {
    const [paymentId, setPaymentId] = useState("")
    const onClick = (e) => {
        setPaymentId("3a7ff069-23b5-4072-aea8-c8e6161531f2")
        fetch(`/kakao?paymentId=${paymentId}`,{
            method: "GET"
        })
            .then(res => res.json())
            .then(data => {
                window.open(data.next_redirect_pc_url, 'kakaopay', 'width=430, height=500, location=no, status=no, scrollbars=yes');
            })
    }
    return(
        <footer style={{borderTop:"solid 1px"}}>
            <div>푸터 메세지</div>
            <button onClick={onClick}> 결제 하기 </button>
        </footer>
    )
}

export default Footer;