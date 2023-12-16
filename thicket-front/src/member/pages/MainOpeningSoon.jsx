import styled from "styled-components";

const MainContainer = styled.main`
  max-width: 1280px;
  margin: 70px auto 0; // 헤더 위치에 따라 수동 조절
`;

const Poster = styled.div`
  display: inline-block;
  width: 200px;
  height: 200px;
`
function MainOpeningSoon(){
    const OpenCard = ({date}) => {
        return (
            <Poster>
                <img/>
                <div>{date}</div>
                <div>제목</div>
                <div>살려주세요</div>
            </Poster>
        )
    }

    return(
        <MainContainer>
            <h2>티켓오픈</h2>
            <OpenCard date={"2023-11-16"}/>
            <OpenCard date={"2023-11-17"}/>
            <OpenCard date={"2023-12-16"}/>
            <OpenCard date={"2023-11-30"}/>
            <OpenCard date={"2023-12-7"}/>
        </MainContainer>
    )
}

export default MainOpeningSoon;