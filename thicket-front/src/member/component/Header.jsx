import styled from "styled-components";
import Nav from "./Nav";

const LogoHeader = styled.header`
  top: 0;
  left: 0;
  width: 100%;
  display: flex;
  justify-content: center;
`
export default function Header() {
    return (
            <LogoHeader>
                <div>
                    <a href={"/"}>
                        <img src={"https://mybox.naver.com/#/my/viewer/3472528559389676120:5775081?resourceKey=b2pzMjU4fDExNDI0Mjc3M3xEfDA&fileResourceKey=b2pzMjU4fDM0NzI1Mjg1NTkzODk2NzYxMjB8Rnww&downloadable=true&editable=false"} alt="로고 이미지"/>
                    </a>
                </div>
            </LogoHeader>
    )
}