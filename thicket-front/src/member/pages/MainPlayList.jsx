import {Wrapper, InvisibleScroll, MainContainer, RankCard, H1, DivList} from "../../assets/css/setting/MainStyleCSS";

function MainPlayList() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1> 연극 목록</H1>
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
        </Wrapper>
    );
};

export default MainPlayList;