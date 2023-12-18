import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList,
    RankCard,
    왼쪽광고, 오른쪽광고
} from "../../assets/css/setting/MainStyleCSS";

function MainConcertList() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1> 콘서트 목록</H1>
                    <DivList>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                        <RankCard rank={1}/>
                        <RankCard rank={2}/>
                        <RankCard rank={3}/>
                        <RankCard rank={4}/>
                        <RankCard rank={5}/>
                    </DivList>
                </MainContainer>
            </InvisibleScroll>
            <왼쪽광고>광고</왼쪽광고>
            <오른쪽광고>광고</오른쪽광고>
        </Wrapper>
    );
};

export default MainConcertList;