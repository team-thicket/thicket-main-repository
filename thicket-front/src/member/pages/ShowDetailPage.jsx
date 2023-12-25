import {useEffect, useState} from "react";
import ReactDOM from 'react-dom';
import 'react-calendar/dist/Calendar.css';
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import {
    Wrapper, MarginTop, Container, Scroll,
    ShowMain, ShowSide, SideTop, SideMarginTop, SideMargin, SideFont, SideBottom,
    PostImg, PostDetailImg, PostInfo,
    H1, Th, Th1, Th2, Td1, Td2, LightGrayLine,
    StyledCalendar, ButtonList, ChoiceDiv,
} from "../../assets/css/setting/MainStyleCSS";
import Reservation from "../../payment/pages/Reservation";

function ShowDetailPage() {
    const [show, setShow] = useState([]);       // 공연정보
    const [times, setTimes] = useState([]);     // 공연정보-시간리스트
    const [chairs, setChairs] = useState([]);   // 단일시간-좌석리스트
    const [value, onChange] = useState(new Date());     // 달력
    const [filteredTimes, setFilteredTimes] = useState([]); // 시간필터링 (초단위 생략)
    const [selectedDate, setSelectedDate] = useState(null);     // 선택 날짜
    const [selectedTime, setSelectedTime] = useState(null);     // 선택 시간
    const [selectedChair, setSelectedChair] = useState(null);   // 선택 좌석
    const [selectedQuantity, setSelectedQuantity] = useState(1); // 갯수 기본 1개

    useEffect(() => { // 공연정보 (SELECT * FROM thicket_stage.stage; → id)
        fetch('/shows/stagedetail/a6b5c388-8692-41fa-91b6-104a608674e0')
            .then(response => response.json())
            .then(data => {
                setShow(data);
            });
    }, []);
    useEffect(() => { // 공연정보 - 시간리스트 (SELECT * FROM thicket_stage.stage; → id)
        fetch('/tickets/all/a6b5c388-8692-41fa-91b6-104a608674e0')
            .then(response => response.json())
            .then(data => {
                setTimes(data);
            });
    }, []);
    useEffect(() => { // 단일시간 - 좌석리스트 (SELECT * FROM thicket_stage.chair; → stage_start_id)
        fetch('/chairs/all/5851f931-196e-4277-aef5-5e87abddf883')
            .then(response => response.json())
            .then(data => {
                setChairs(data);
            });
    }, []);

    // 데이터 타임 형변환
    const formatDateString = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString();
    };

    // 날짜 변경시 초기화
    const handleDateChange = (date) => {
        setSelectedDate(date);

        // 시간, 좌석, 수량을 초기화
        setSelectedTime(null);
        setSelectedChair(null);
        setSelectedQuantity(1);

        // 수량 리스트 초기화
        const quantitySelect = document.getElementById('quantitySelect');
        if (quantitySelect) {
            quantitySelect.value = 1;
        }

        // 해당 날짜에 맞는 시간 데이터 필터링
        const filtered = times
            .filter((time) => moment(time.date).isSame(date, 'day'))
            .map((time) => ({
                ...time,
                time: moment(time.time, 'HH:mm:ss').format('HH:mm'),
            }));
        setFilteredTimes(filtered);
    };

    // 시간 선택
    const handleTimeSelect = (time) => {
        setSelectedTime(time);
    };

    // 좌석 선택
    const handleChairSelect = (chair) => {
        setSelectedChair(chair);
    };

    // 수량 선택
    const handleQuantityChange = (event) => {
        setSelectedQuantity(parseInt(event.target.value, 10));
    };

    // 이하 예매 로직
    const [reservationWindow, setReservationWindow] = useState(null);

    const handleReservationClick = () => {
        // 예매하기 버튼을 눌렀을 때, 새 창으로 Reservation 페이지를 엽니다.
        const width = Math.floor(window.innerWidth * 0.7);
        const height = Math.floor(window.innerHeight * 0.8);
        const left = Math.floor((window.innerWidth - width) / 2);
        const top = Math.floor((window.innerHeight - height) / 2);

        const windowFeatures = `width=${width},height=${height},left=${left},top=${top}`;

        const paymentWindow = window.open('', '_blank', windowFeatures);

        if (paymentWindow) {
            // 예약 페이지를 새 창에 렌더링
            const reservationContainer = paymentWindow.document.createElement('div');
            paymentWindow.document.body.appendChild(reservationContainer);

            setReservationWindow(reservationContainer);
        } else {
            // 팝업 창이 차단되었거나 오류가 있는 경우에는 경고를 표시합니다.
            alert('팝업 창이 차단되었거나 오류가 발생했습니다. 팝업 차단을 해제해주세요.');
        }
    };

    useEffect(() => {
        // reservationWindow이 변경되면 Reservation 컴포넌트를 렌더링
        if (reservationWindow) {
            ReactDOM.render(<Reservation />, reservationWindow);
        }
    }, [reservationWindow]);

    return (
        <Wrapper>
            <MarginTop>
                <Container>
                    <ShowMain>
                        <Scroll>
                            <Container>
                                <PostImg src={show.posterImg} alt="posterImg" />
                                <PostInfo>
                                    <H1>{show.name}← 이건 디비값　　　　두줄까지 내려가면 이렇게 보입니다.</H1>
                                    <table>
                                        <tr>
                                            <Th>장소</Th>
                                            <Td1>{show.place}</Td1>
                                        </tr>
                                        <tr>
                                            <Th>공연기간</Th>
                                            <Td1>{`${formatDateString(show.stageOpen)} ~ ${formatDateString(show.stageClose)}`}</Td1>
                                        </tr>
                                        <tr>
                                            <Th>공연시간</Th>
                                            <Td1>{show.runningTime}</Td1>
                                        </tr>
                                        <tr>
                                            <Th>관람연령</Th>
                                            <Td1>{show.ageLimit}</Td1>
                                        </tr>
                                        <tr>
                                            <Th1>가격</Th1>
                                            {Array.isArray(chairs) && chairs.length > 0 && chairs
                                                .sort((a, b) => b.price - a.price) // 가격을 내림차순으로 정렬
                                                .map(chair => (
                                                    <tr key={chair.chairUuid}>
                                                        <Th2>{chair.chairType}석</Th2>
                                                        <Td2>{Number(chair.price).toLocaleString()}원</Td2>
                                                    </tr>
                                                ))}
                                        </tr>
                                    </table>
                                </PostInfo>
                            </Container>
                            <div style={{marginTop:'50px'}} />
                            <h2>공연정보</h2>
                            <div style={{marginTop:"10px"}} />
                            <LightGrayLine />
                            <div style={{marginTop:"10px"}} />
                            <p>{show.stageInfo}</p>
                            <PostDetailImg src={show.detailPosterImg} alt="detailPosterImg" />
                        </Scroll>
                    </ShowMain>
                    <ShowSide>
                        <Scroll>
                            <SideTop>
                                <SideMarginTop>
                                    <SideFont>관람일</SideFont>
                                    <StyledCalendar
                                        onChange={(date) => {
                                            onChange(date);
                                            handleDateChange(date);
                                        }}
                                        value={value}
                                        locale="ko"
                                        formatDay={(locale, date) => moment(date).format('D')}
                                        showNeighboringMonth={false}
                                        calendarType="gregory" // 일요일부터 시작
                                        minDate={new Date(show.stageOpen)} // Set the minimum date
                                        // minDate={new Date()} // 실제 로직에서는 스테이지 오픈 대신 오늘 날짜로 사용
                                        maxDate={new Date(show.stageClose)} // Set the maximum date
                                    />
                                </SideMarginTop>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>회차</SideFont>
                                    {filteredTimes.length > 0 ? (
                                        <ButtonList>
                                            {filteredTimes.map((time, index) => (
                                                <ChoiceDiv
                                                    key={index}
                                                    selected={time === selectedTime}
                                                    selectedTime={selectedTime}
                                                    onClick={() => handleTimeSelect(time)}
                                                >
                                                    {index + 1}회 {time.time}
                                                </ChoiceDiv>
                                            ))}
                                        </ButtonList>
                                    ) : (
                                        <div style={{marginTop:'5px'}}>
                                            <ChoiceDiv>해당 날짜는 공연이 없습니다.</ChoiceDiv>
                                        </div>
                                    )}
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>좌석</SideFont>
                                    {filteredTimes.length > 0 && (
                                        <ButtonList>
                                            {Array.isArray(chairs) ? (
                                                chairs.map(chair => (
                                                    <ChoiceDiv
                                                        key={chair.chairUuid}
                                                        selected={chair === selectedChair}
                                                        selectedTime={selectedTime}
                                                        onClick={() => handleChairSelect(chair)}
                                                    >
                                                        {chair.chairType}석
                                                    </ChoiceDiv>
                                                ))
                                            ) : null}
                                        </ButtonList>
                                    )}
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>수량</SideFont>
                                    {filteredTimes.length > 0 && (
                                        <div style={{ marginTop: '5px' }}>
                                            <select id="quantitySelect" onChange={handleQuantityChange}>
                                                {[...Array(10).keys()].map((value) => (
                                                    <option key={value + 1} value={value + 1}>{value + 1}</option>
                                                ))}
                                            </select> 개
                                        </div>
                                    )}
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>선택정보</SideFont>
                                    {selectedDate && selectedTime && selectedChair && (
                                        <div style={{ width: '90%', margin: '10px 0 0 0', display: 'grid' }}>
                                            <SideFont>
                                                {moment(selectedDate).format('YYYY년 MM월 DD일')}　{selectedTime.time}　{selectedChair.chairType}석 {selectedQuantity}개
                                            </SideFont>
                                        </div>
                                    )}
                                </SideMargin>
                            </SideTop>
                            <SideBottom onClick={handleReservationClick}>
                                예매하기
                            </SideBottom>
                        </Scroll>
                    </ShowSide>
                </Container>
            </MarginTop>
        </Wrapper>
    );
}

export default ShowDetailPage;