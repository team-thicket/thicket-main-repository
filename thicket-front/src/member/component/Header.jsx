import styled from "styled-components";
import Nav from "./Nav";

const LogoHeader = styled.header`
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
`
export default function Header() {
    return (
        <LogoHeader>
            <div>
                <a href={"/"}>
                    <img src={"https://mybox.naver.com/#/my/viewer/3472528559389676120:5775081?resourceKey=b2pzMjU4fDExNDI0Mjc3M3xEfDA&fileResourceKey=b2pzMjU4fDM0NzI1Mjg1NTkzODk2NzYxMjB8Rnww&downloadable=true&editable=false"} alt="로고 이미지"/>
                    {/*<img src={"https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F60c14a70-6790-4917-9664-cf308b6b9f1a%2F13ce3b0a-235c-47f2-acc0-2e787e6145bb%2F%25EB%25B0%25B0%25EB%2584%2588.png?table=block&id=113a1c4f-d3e8-4d42-a1c4-c9b373dc4cbc&spaceId=60c14a70-6790-4917-9664-cf308b6b9f1a&width=2000&userId=f00abd5d-1ee9-4b23-affe-2aa6fa9a0e2d&cache=v2"} alt="로고 이미지"/>*/}
                </a>
            </div>
        </LogoHeader>
    )
}