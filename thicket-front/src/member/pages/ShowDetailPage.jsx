import { useEffect, useState } from "react";
import ReactDOM from "react-dom";
import "react-calendar/dist/Calendar.css";
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import {
  Wrapper,
  MarginTop,
  Container,
  Scroll,
  ShowMain,
  ShowSide,
  SideTop,
  SideMarginTop,
  SideMargin,
  SideFont,
  SideBottom,
  PostImg,
  PostDetailImg,
  PostInfo,
  H1,
  Th,
  Th1,
  Th2,
  Td1,
  Td2,
  LightGrayLine,
  StyledCalendar,
  ButtonList,
  ChoiceDiv,
  DisabledSideBottom,
} from "../../assets/css/setting/MainStyleCSS";
import Reservation from "../../payment/pages/Reservation";

function ShowDetailPage() {
  const [show, setShow] = useState([]); // 공연정보
  const [times, setTimes] = useState([]); // 공연정보-시간리스트
  const [chairs, setChairs] = useState([]); // 단일시간-좌석리스트
  const [value, onChange] = useState(new Date()); // 달력
  const [filteredTimes, setFilteredTimes] = useState([]); // 시간필터링 (초단위 생략)
  const [selectedDate, setSelectedDate] = useState(null); // 선택 날짜
  const [selectedTime, setSelectedTime] = useState(null); // 선택 시간
  const [selectedChair, setSelectedChair] = useState(null); // 선택 좌석
  const [selectedQuantity, setSelectedQuantity] = useState(1); // 갯수 기본 1개
  const [totalAmount, setTotalAmount] = useState(0); // 선택된 좌석의 총 금액을 저장할 새로운 상태
  const [serverTime, setServerTime] = useState(Date);

  // 공연 상세 가져오기
  useEffect(() => {
    // 공연정보 (SELECT * FROM thicket_stage.stage; → id)
    // 현재 페이지의 url에서 공연 UUID 가져오기
    const showId = window.location.href.split("/")[4];
    // 공연 상세 API 호출
    fetch(`/thicket-show/shows/stagedetail/${showId}`)
      .then((response) => response.json())
      .then((data) => {
        console.log("Show Detail Data:", data);
        setShow(data);
      })
      .then(
        fetch(`/thicket-show/tickets/all/${showId}`)
          .then((response) => response.json())
          .then((data) => {
            setTimes(data);
          })
      );
    setInterval(dirtyCheck, 500);
  }, []);
  function calculateThreeDaysAgo(selectedDate, selectedTime) {
    const selectedDateTime = new Date(`${selectedDate} ${selectedTime}`);
    selectedDateTime.setDate(selectedDateTime.getDate() - 3);
    return selectedDateTime;
  }

  // 좌석정보 가져오기 회차정보 클릭시
  const getChairInfo = (stageId) => {
    fetch(`/thicket-show/chairs/all/${stageId}`)
      .then((response) => response.json())
      .then((data) => {
        setChairs(data);
      });
  };

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
    const quantitySelect = document.getElementById("quantitySelect");
    if (quantitySelect) {
      quantitySelect.value = 1;
    }

    // 해당 날짜에 맞는 시간 데이터 필터링
    const filtered = times
      .filter((time) => moment(time.date).isSame(date, "day"))
      .map((time) => ({
        ...time,
        time: moment(time.time, "HH:mm:ss").format("HH:mm"),
      }));
    console.log(filtered);
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

  const dirtyCheck = async () => {
    await fetch("/thicket-show/shows/serverTime")
      .then((response) => response.text())
      .then((data) => {
        setServerTime(new Date(data));
      });
  };

  // 이하 예매 로직
  const [reservationWindow, setReservationWindow] = useState(null);

  // 3일 전의 날짜를 계산하는 함수
  function calculateThreeDaysAgo(dateTimeString) {
    const currentDate = new Date(dateTimeString);
    currentDate.setDate(currentDate.getDate() - 3);
    return currentDate;
  }

  const handleReservationClick = () => {
    if (selectedDate && selectedTime && selectedChair) {
      const reservationData = {
        stageName: show.name,
        date: new Date(),
        place: show.place,
        chairType: chairs[0].chairType,
        count: selectedQuantity,
        price: chairs[0].price,
        cancelDate: calculateThreeDaysAgo(
          moment(selectedDate).format("YYYY-MM-DD"),
          selectedTime.time
        ),
        stageId: show.stageId,
        chairId: chairs[0].chairId,
        stageType: show.stageType,
        latency: new Date() - serverTime,
      };
      console.log(reservationData);
      fetch("/thicket-ticket/reservations/ticket", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: localStorage.getItem("token"),
        },
        body: JSON.stringify(reservationData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("네트워크 응답이 올바르지 않습니다");
          }
          return response.text(); // 텍스트로 응답 받기
        })
        .then((result) => {
          // 여기서 result는 성공 또는 실패 문자열입니다.
          console.log("Reservation result:", result);
          // 성공 또는 실패에 따른 처리를 추가하세요.
          if (result === "성공") {
            // 성공한 경우의 처리
            alert("예매 대기중 입니다.");
          } else {
            // 실패한 경우의 처리
          }
        })
        .catch((error) => {
          console.error("예약 중 오류 발생:", error);
          alert("예약 중 오류가 발생했습니다. 다시 시도해주세요.");
        });
    } else {
      alert("날짜, 시간 및 좌석을 선택하세요.");
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
                  <H1>{show.name}</H1>
                  <table>
                    <tr>
                      <Th>장소</Th>
                      <Td1>{show.place}</Td1>
                    </tr>
                    <tr>
                      <Th>공연기간</Th>
                      <Td1>{`${formatDateString(
                        show.stageOpen
                      )} ~ ${formatDateString(show.stageClose)}`}</Td1>
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
                      {Array.isArray(chairs) &&
                        chairs.length > 0 &&
                        chairs
                          .sort((a, b) => b.price - a.price) // 가격을 내림차순으로 정렬
                          .map((chair) => (
                            <tr key={chair.chairUuid}>
                              <Th2>{chair.chairType}석</Th2>
                              <Td2>
                                {Number(chair.price).toLocaleString()}원
                              </Td2>
                            </tr>
                          ))}
                    </tr>
                  </table>
                </PostInfo>
              </Container>
              <div style={{ marginTop: "50px" }} />
              <h2>공연정보</h2>
              <div style={{ marginTop: "10px" }} />
              <LightGrayLine />
              <div style={{ marginTop: "10px" }} />
              <p>{show.stageInfo}</p>
              {Array.isArray(show.detailPosterImg) ? (
                show.detailPosterImg.map((url) => <PostDetailImg src={url} />)
              ) : (
                <p>상세 이미지가 없습니다.</p>
              )}
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
                    formatDay={(locale, date) => moment(date).format("D")}
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
                          onClick={() => {
                            handleTimeSelect(time);
                            getChairInfo(time.stageId);
                          }}
                        >
                          {index + 1}회 {time.time}
                        </ChoiceDiv>
                      ))}
                    </ButtonList>
                  ) : (
                    <div style={{ marginTop: "5px" }}>
                      <ChoiceDiv>해당 날짜는 공연이 없습니다.</ChoiceDiv>
                    </div>
                  )}
                </SideMargin>
                <LightGrayLine />
                <SideMargin>
                  <SideFont>좌석</SideFont>
                  {filteredTimes.length > 0 && (
                    <ButtonList>
                      {Array.isArray(chairs)
                        ? chairs.map((chair) => (
                            <ChoiceDiv
                              key={chair.chairUuid}
                              selected={chair === selectedChair}
                              selectedTime={selectedTime}
                              onClick={() => handleChairSelect(chair)}
                            >
                              {chair.chairType}석
                            </ChoiceDiv>
                          ))
                        : null}
                    </ButtonList>
                  )}
                </SideMargin>
                <LightGrayLine />
                <SideMargin>
                  <SideFont>수량</SideFont>
                  {filteredTimes.length > 0 && (
                    <div style={{ marginTop: "5px" }}>
                      <select
                        id="quantitySelect"
                        onChange={handleQuantityChange}
                      >
                        {[...Array(10).keys()].map((value) => (
                          <option key={value + 1} value={value + 1}>
                            {value + 1}
                          </option>
                        ))}
                      </select>{" "}
                      개
                    </div>
                  )}
                </SideMargin>
                <LightGrayLine />
                <SideMargin>
                  <SideFont>선택정보</SideFont>
                  {selectedDate && selectedTime && selectedChair && (
                    <div
                      style={{
                        width: "90%",
                        margin: "10px 0 0 0",
                        display: "grid",
                      }}
                    >
                      <SideFont>
                        {moment(selectedDate).format("YYYY년 MM월 DD일")}　
                        {selectedTime.time}　{selectedChair.chairType}석{" "}
                        {selectedQuantity}개
                      </SideFont>
                    </div>
                  )}
                </SideMargin>
              </SideTop>
              {new Date(show.ticketOpen) >= serverTime ? (
                <DisabledSideBottom>예매하기</DisabledSideBottom>
              ) : (
                <SideBottom onClick={handleReservationClick}>
                  예매하기
                </SideBottom>
              )}
            </Scroll>
          </ShowSide>
        </Container>
      </MarginTop>
    </Wrapper>
  );
}

export default ShowDetailPage;
