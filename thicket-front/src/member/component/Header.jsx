import styled from "styled-components";
import {FaSearch} from "react-icons/fa";
import HeaderMenu from "./HeaderMenu";
import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import Payment from "../../payment/pages/Payment";


const LogoHeader = styled.header`
  width: 100%;
  height: 18px;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
`

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  background-color: antiquewhite;
`

const Menubar = styled.div`
  width: 120%; // 100%로 하면 이상하게 가장자리가 조금 부족함;
  background-color: antiquewhite;
  display: flex;
  position: fixed;
  top: 18px; // 로고 위치에 따라 수동 조절
`
const SearchInput = styled.input`
  height: 30px;
  width: 185px;
  margin-right: 5px;
`;

export default function Header() {
    const [isLoggedIn, setIsLoggedIn] = useState(false)
    useEffect(() => {
        const token = localStorage.getItem('token');
        // 토큰 변경 시 로그인 상태 업데이트
        if(token === null) {
            setIsLoggedIn(false)
        } else {
            setIsLoggedIn(true);
        }
    }, []);

    const navigate = useNavigate(); // useHistory 대신 useNavigate 사용
    const [query, setQuery] = useState("");

    const handleSearch = () => {
        if (query) {
            navigate(`/search/${query}`); // useHistory 대신 useNavigate 사용
        }
    };

    return (
        <div>
            <LogoHeader>
                <div style={{ position: "fixed", width: "1280px", textAlign: "center", backgroundColor: "white", zIndex: "1000" }}>
                    <a href={"/"}>
                        <img src={"https://mybox.naver.com/#/my/viewer/3472528559389676120:5775081?resourceKey=b2pzMjU4fDExNDI0Mjc3M3xEfDA&fileResourceKey=b2pzMjU4fDM0NzI1Mjg1NTkzODk2NzYxMjB8Rnww&downloadable=true&editable=false"} alt="로고 이미지"/>
                        {/*<img src={"https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F60c14a70-6790-4917-9664-cf308b6b9f1a%2F13ce3b0a-235c-47f2-acc0-2e787e6145bb%2F%25EB%25B0%25B0%25EB%2584%2588.png?table=block&id=113a1c4f-d3e8-4d42-a1c4-c9b373dc4cbc&spaceId=60c14a70-6790-4917-9664-cf308b6b9f1a&width=2000&userId=f00abd5d-1ee9-4b23-affe-2aa6fa9a0e2d&cache=v2"} alt="로고 이미지"/>*/}
                    </a>
                </div>
            </LogoHeader>
            <Wrapper>
                <Menubar>
                    <div style={{ display: "flex", margin: "auto" }}>
                        <div style={{ display: "flex", alignItems: "center", width: "650px" }}>
                            <div style={{ width: "10px" }}></div>
                            <SearchInput
                                type="text"
                                placeholder=" 검색어를 입력해 주세요"
                                value={query}
                                onChange={(e) => setQuery(e.target.value)}
                                onKeyDown={(e) => {
                                    if (e.key === "Enter") {
                                        handleSearch();
                                    }
                                }}
                            />
                            <div
                                style={{
                                    display: "flex",
                                    alignItems: "center",
                                    justifyContent: "center",
                                    cursor: "pointer",
                                }}
                                onClick={handleSearch}
                            >
                                <FaSearch />
                            </div>
                            <div style={{ width: "20px" }}></div>
                            <HeaderMenu name={"뮤지컬"} link={"/musical"} />
                            <HeaderMenu name={"연극"} link={"/play"} />
                            <HeaderMenu name={"콘서트"} link={"/concert"} />
                            <HeaderMenu name={"티켓오픈"} link={"/soon"} />
                        </div>
                        <div style={{ display: 'flex', justifyContent: 'flex-end', width: "630px" }}>
                            {isLoggedIn ? (
                                <>
                                    <HeaderMenu name={'마이페이지'} link={'/mypage'} />
                                    <HeaderMenu name={'로그아웃'} link={'/login'} onClick={() => {
                                        localStorage.removeItem('token');
                                    }} />
                                </>
                            ) : (
                                <>
                                    <HeaderMenu name={'로그인'} link={'/login'} />
                                    <HeaderMenu name={'회원가입'} link={'/auth'} />
                                </>
                            )}
                        </div>
                    </div>
                </Menubar>
            </Wrapper>
        </div>
    )
}