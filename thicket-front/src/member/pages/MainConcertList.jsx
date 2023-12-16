import { Wrapper, InvisibleScroll, MainContainer, RankCard } from "../../assets/css/setting/MainStyleCSS";
import { H1 } from "../../assets/css/setting/admin/StylesOfList";

function MainConcertList() {
    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                    <H1> 콘서트 Top5.</H1>
                    <RankCard rank={1}/>
                    <RankCard rank={2}/>
                    <RankCard rank={3}/>
                    <RankCard rank={4}/>
                    <RankCard rank={5}/>
                </MainContainer>
            </InvisibleScroll>
        </Wrapper>
    );
};

export default MainConcertList;