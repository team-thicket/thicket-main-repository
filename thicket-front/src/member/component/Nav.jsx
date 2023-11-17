import styled from "styled-components";
import Menu from "./Menu";
const Menubar = styled.div`
  border-bottom: black solid 1px;
  padding: 1rem;
  display: grid;
  grid-column-gap: 10px; 
  grid-template-columns: repeat(8,1fr);
  background-color: antiquewhite;
`

function Nav() {
    return(
        <Menubar>
            <input/>
            <Menu name={"검색"} />
            <Menu name={"뮤지컬"} />
            <Menu name={"연극"} />
            <Menu name={"콘서트"} />
            <Menu name={"티켓오픈"} />
            <Menu name={"로그인"} />
            <Menu name={"로그아웃"} />
        </Menubar>
    )
}

export default Nav;