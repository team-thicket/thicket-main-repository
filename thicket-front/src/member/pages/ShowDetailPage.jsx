import {useEffect, useState} from "react";
import ReactDOM from 'react-dom';
import 'react-calendar/dist/Calendar.css';
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import {
    ButtonList,
    ChoiceDiv,
    Container,
    H1,
    LightGrayLine,
    MarginTop,
    PostImage,
    PostInfo,
    Scroll,
    ShowMain,
    ShowSide,
    SideBottom,
    SideFont,
    SideMargin,
    SideMarginTop,
    SideTop,
    StyledCalendar,
    Th,
    Th1,
    Th2,
    Wrapper,
} from "../../assets/css/setting/MainStyleCSS";
import Reservation from "../../payment/pages/Reservation";

function ShowDetailPage() {
    const [value, onChange] = useState(new Date());
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
                                <PostImage>
                                    포스터 이미지
                                    <br/>
                                    300x400 사이즈
                                </PostImage>
                                <PostInfo>
                                    <H1>2024 뮤지컬 노트르담 드 파리 - 한국어버 옆에 폭이 좁아서 제목을 두줄까지 어때?</H1>
                                    <table>
                                        <tr>
                                            <Th>장소</Th>
                                            <td>2세종문화회관 대극장</td>
                                        </tr>
                                        <tr>
                                            <Th>공연기간</Th>
                                            <td>2024.01.24 ~2024.03.24</td>
                                        </tr>
                                        <tr>
                                            <Th>공연시간</Th>
                                            <td>150분(인터미션 20분 포함)</td>
                                        </tr>
                                        <tr>
                                            <Th1>가격</Th1>
                                            <tr>
                                                <Th2>VIP석</Th2>
                                                <td>젤비쌈</td>
                                            </tr>
                                            <tr>
                                                <Th2>R석</Th2>
                                                <td>좀비쌈</td>
                                            </tr>
                                            <tr>
                                                <Th2>S석</Th2>
                                                <td>비쌈</td>
                                            </tr>
                                            <tr>
                                                <Th2>A석</Th2>
                                                <td>낫베드</td>
                                            </tr>
                                            <tr>
                                                <Th2>B석</Th2>
                                                <td>보기불편</td>
                                            </tr>
                                        </tr>
                                    </table>
                                </PostInfo>
                            </Container>
                            <div style={{marginTop:'50px'}} />
                            <h2>공연정보 or 공지사항</h2>
                            <div style={{marginTop:"10px"}} />
                            <p>내용 (디테일이미지 전 공지사항, 매진 등 입력 가능)</p>
                            <div style={{width: '926px', height: '2000px', marginBottom:'20px', backgroundColor: '#CCC'}}>
                                <H1>상세페이지 이미지</H1>
                                <H1>첨부한 파일_(아래스크롤)</H1>
                            </div>
                        </Scroll>
                    </ShowMain>
                    <ShowSide>
                        <Scroll>
                            <SideTop>
                                <SideMarginTop>
                                    <SideFont>관람일</SideFont>
                                    <StyledCalendar
                                        onChange={onChange}
                                        value={value}
                                        locale="ko"
                                        formatDay={(locale, date) => moment(date).format('D')}
                                        showNeighboringMonth={false}
                                        calendarType="US" // 일요일부터 시작
                                    />
                                </SideMarginTop>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>회차</SideFont>
                                    <ButtonList>
                                        <ChoiceDiv>1회 09:00</ChoiceDiv>
                                        <ChoiceDiv>2회 13:00</ChoiceDiv>
                                        <ChoiceDiv>3회 19:00</ChoiceDiv>
                                    </ButtonList>
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>좌석</SideFont>
                                    <ButtonList>
                                        <ChoiceDiv>VIP석</ChoiceDiv>
                                        <ChoiceDiv>R석</ChoiceDiv>
                                        <ChoiceDiv>S석</ChoiceDiv>
                                        <ChoiceDiv>A석</ChoiceDiv>
                                        <ChoiceDiv>B석</ChoiceDiv>
                                    </ButtonList>
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>수량</SideFont>
                                    <div style={{ marginTop: '5px' }}>
                                        <select>
                                            {[...Array(10).keys()].map((value) => (
                                                <option key={value + 1} value={value + 1}>{value + 1}</option>
                                            ))}
                                        </select> 개
                                    </div>
                                </SideMargin>
                                <LightGrayLine />
                                <SideMargin>
                                    <SideFont>선택정보</SideFont>
                                    <div style={{ width: '90%', margin: '10px 0 0 0', display: 'grid' }}>
                                        <SideFont>
                                            yyyy년 MM월 dd일 hh:mm
                                            <br/>
                                            VIP석 1개
                                        </SideFont>
                                    </div>
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
};

export default ShowDetailPage;