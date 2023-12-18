import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList,
    OpenCard,
    왼쪽광고, 오른쪽광고
} from "../../assets/css/setting/MainStyleCSS";

function MainOpeningSoon() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1>티켓오픈 목록</H1>
                    <DivList>
                        <OpenCard date={"2023-11-16"}/>
                        <OpenCard date={"2023-11-17"}/>
                        <OpenCard date={"2023-12-18"}/>
                        <OpenCard date={"2023-11-19"}/>
                        <OpenCard date={"2023-11-20"}/>
                        <OpenCard date={"2023-12-21"}/>
                        <OpenCard date={"2023-11-16"}/>
                        <OpenCard date={"2023-11-17"}/>
                        <OpenCard date={"2023-12-18"}/>
                        <OpenCard date={"2023-11-19"}/>
                        <OpenCard date={"2023-11-20"}/>
                        <OpenCard date={"2023-12-21"}/>
                        <OpenCard date={"2023-11-16"}/>
                        <OpenCard date={"2023-11-17"}/>
                        <OpenCard date={"2023-12-18"}/>
                        <OpenCard date={"2023-11-19"}/>
                        <OpenCard date={"2023-11-20"}/>
                        <OpenCard date={"2023-12-21"}/>
                        <OpenCard date={"2023-11-16"}/>
                        <OpenCard date={"2023-11-17"}/>
                        <OpenCard date={"2023-12-18"}/>
                        <OpenCard date={"2023-11-19"}/>
                        <OpenCard date={"2023-11-20"}/>
                        <OpenCard date={"2023-12-21"}/>
                    </DivList>
                </MainContainer>
            </InvisibleScroll>
            <왼쪽광고>광고</왼쪽광고>
            <오른쪽광고>광고</오른쪽광고>
        </Wrapper>
    );
};

export default MainOpeningSoon;