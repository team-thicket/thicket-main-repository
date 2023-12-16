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
function MainMusicalList(){
    const RankCard = ({rank}) => {
        return (
            <Poster>
                <img/>
                <div>{rank}</div>
                <div>제목</div>
                <div>살려주세요</div>
            </Poster>
        )
    }

    return(
        <MainContainer>
            <h2> 뮤지컬 Top5.</h2>
            <RankCard rank={1}/>
            <RankCard rank={2}/>
            <RankCard rank={3}/>
            <RankCard rank={4}/>
            <RankCard rank={5}/>
        </MainContainer>
    )
}

export default MainMusicalList;