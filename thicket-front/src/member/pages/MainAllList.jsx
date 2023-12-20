import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList1,
    DivList2,
    RankCard,
    OpenCard,
} from "../../assets/css/setting/MainStyleCSS";

function MainAllList() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1> 뮤지컬 Top5.</H1>
                    <DivList1>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                        <RankCard rank={6}/>
                    </DivList1>
                    <H1> 연극 Top5.</H1>
                    <DivList1>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                    </DivList1>
                    <H1> 콘서트 Top5.</H1>
                    <DivList1>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                    </DivList1>
                    <H1> 티켓오픈 </H1>
                    <DivList2>
                        <OpenCard date={"2023-11-16"}/>
                        <OpenCard date={"2023-11-17"}/>
                        <OpenCard date={"2023-12-18"}/>
                        <OpenCard date={"2023-11-19"}/>
                        <OpenCard date={"2023-11-20"}/>
                        <OpenCard date={"2023-12-21"}/>
                    </DivList2>
                </MainContainer>
            </InvisibleScroll>
        </Wrapper>
    )
}

export default MainAllList;