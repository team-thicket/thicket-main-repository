import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList1,
    RankCard, Poster1, Img1, ImgInfo1,
    // 왼쪽광고, 오른쪽광고
} from "../../assets/css/setting/MainStyleCSS";
import {useEffect, useState} from "react";

const ShowList = () => {
    const [shows, setShows] = useState([]);

    useEffect(() => {
        fetch('/shows/stagetype/CONCERT')
            .then(response => response.json())
            .then(data => {
                setShows(data);
            });
    }, []);

    const formatDateString = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString(); // Adjust the format as needed
    };

    return (
        <DivList1>
            {Array.isArray(shows) > 0 ? (
                shows.map(show => (
                    <Poster1 key={show.id}>
                        <Img1 src={show.posterImg} alt="Poster" />
                        <ImgInfo1>
                            <div>{show.name}</div>
                            <div>{show.place}</div>
                            <div>{`${formatDateString(show.stageOpen)} ~ ${formatDateString(show.stageClose)}`}</div>
                        </ImgInfo1>
                    </Poster1>
                ))
            ) : (
                <H1>없습니다.　　　　　　　　</H1>
            )}
        </DivList1>
    );
};

function MainConcertList() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1>진행중인 콘서트 목록</H1>
                    <ShowList />
                </MainContainer>
            </InvisibleScroll>
            {/*<왼쪽광고>광고</왼쪽광고>*/}
            {/*<오른쪽광고>문의</오른쪽광고>*/}
        </Wrapper>
    );
};

export default MainConcertList;