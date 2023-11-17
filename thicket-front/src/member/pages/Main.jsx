import styled from "styled-components";

const Poster = styled.div`
  display: inline-block;
  width: 200px;
  height: 200px;
`
function Main(){
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
        <main>
            <h2> 뮤지컬 Top5.</h2>
            <RankCard rank={1}/>
            <RankCard rank={2}/>
            <RankCard rank={3}/>
            <RankCard rank={4}/>
            <RankCard rank={5}/>
            <h2> 연극 Top5.</h2>
            <RankCard rank={1}/>
            <RankCard rank={2}/>
            <RankCard rank={3}/>
            <RankCard rank={4}/>
            <RankCard rank={5}/>
            <h2> 콘서트 Top5.</h2>
            <RankCard rank={1}/>
            <RankCard rank={2}/>
            <RankCard rank={3}/>
            <RankCard rank={4}/>
            <RankCard rank={5}/>
            <h2>티켓오픈</h2>
            <OpenCard date={"2023-11-16"}/>
            <OpenCard date={"2023-11-17"}/>
            <OpenCard date={"2023-12-16"}/>
            <OpenCard date={"2023-11-30"}/>
            <OpenCard date={"2023-12-7"}/>
        </main>
    )
}

export default Main;