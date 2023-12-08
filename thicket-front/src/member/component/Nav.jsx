import styled from "styled-components";
import HeaderMenu from "./HeaderMenu";
import { FaSearch } from "react-icons/fa"; // Import search icon from react-icons library

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  background-color: antiquewhite;
`

const Menubar = styled.div`
  max-width: 1280px;
  display: flex;
`

function Nav() {
    return(
        <Wrapper>
            <Menubar>
                <div style={{ width: "20px" }}></div>
                <div style={{ display: "flex", alignItems: "center", width: "850px" }}>
                    <input style={{ height: "30px", width: "185px", marginRight: "5px" }} />
                    <FaSearch />
                    <div style={{ width: "20px" }}></div>
                    <HeaderMenu name={"뮤지컬"} />
                    <HeaderMenu name={"연극"} />
                    <HeaderMenu name={"콘서트"} />
                    <HeaderMenu name={"티켓오픈"} />
                </div>
                <div style={{ display: 'flex', justifyContent: 'flex-end', width: '400px' }}>
                    <HeaderMenu name={"마이페이지"} link={"/mypage"} />
                    <HeaderMenu name={"임시어드민"} link={"/admin"} />
                    <HeaderMenu name={"로그아웃"} />
                </div>
                <div style={{ width: "10px" }}></div>
            </Menubar>
        </Wrapper>

    )
}

export default Nav;