import {
    Wrapper,
    InvisibleScroll,
    MainContainer,
    H1,
    DivList2, Poster2, Img2, ImgDivInfo2,
    // 왼쪽광고, 오른쪽광고
} from "../../assets/css/setting/MainStyleCSS";
import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

const ShowList = () => {
    const [shows, setShows] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/shows/before')
            .then(response => response.json())
            .then(data => {
                setShows(data);
            });
    }, []);

    const formatDateString = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString();
    };

    return (
        <DivList2>
            {Array.isArray(shows) > 0 ? (
                shows.map(show => (
                    <Poster2 key={show.id} onClick={() => navigate(`/detail/${show.id}`)}>
                        <Img2 src={show.posterImg} alt="Poster" />
                        <ImgDivInfo2>
                            <div><h2>{show.name}</h2></div>
                            <div><h3>{show.place}</h3></div>
                            <div><h4>{`${formatDateString(show.ticketOpen)}`}</h4></div>
                        </ImgDivInfo2>
                    </Poster2>
                ))
            ) : (
                <H1>없습니다.　　　　　　　　아니 그냥 없어요.</H1>
            )}
        </DivList2>
    );
};

function MainOpeningSoon() {

    return (
        <Wrapper>
            <InvisibleScroll>
                <MainContainer>
                    <H1>오픈예정 목록</H1>
                    <ShowList />
                </MainContainer>
            </InvisibleScroll>
            {/*<왼쪽광고>광고</왼쪽광고>*/}
            {/*<오른쪽광고>문의</오른쪽광고>*/}
        </Wrapper>
    );
};

export default MainOpeningSoon;