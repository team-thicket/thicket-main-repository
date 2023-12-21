import styled, {css} from "styled-components";
import Calendar from "react-calendar";

export const Wrapper = styled.div`
  display: flex; // Flex 컨테이너로 설정하여 자식 요소들을 플렉스 방향으로 배치
  width: 100%; // 부모 요소에 대해 100%의 너비를 차지
  justify-content: center;
`;

export const InvisibleScroll = styled.main`
  margin-top: 51px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  width: 1280px;
  height: auto;
  position: fixed; // 위치 고정
  display: flex;
  justify-content:center;
  align-items : center;
  ::-webkit-scrollbar {
    display: none;
  }
  //border: black solid 1px; // 테두리, 사이즈 확인용
`;
export const MainContainer = styled.div`
    overflow-y: auto; // 내부 수직 스크롤바
    max-height: 90.5vh; // 계산 끝
`;

export const DivList1 = styled.div`
  margin-bottom: 40px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px 97px;
`;

export const Poster1 = styled.div`
  display: inline-block;
  border: 1px solid lightgray;
  border-radius: 10px;
`;

export const Img1 = styled.img`
  width: 360px;
  height: 480px;
  border-radius: 10px 10px 0 0;
`;

export const ImgInfo1 = styled.div`
  width: 318px;
  height: 99px;
  padding: 0 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 10px; // 균등분배
`;

export const DivList2 = styled.div`
  margin-bottom: 40px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 40px 40px;
`;

export const Poster2 = styled.div`
  display: flex;
  align-items: center;
  //margin-bottom: 20px;
  width: 618px;
  height: 260px;
  border: 1px solid lightgray;
  border-radius: 20px;
`;

export const Img2 = styled.img`
  max-width: 210px;
  min-width: 210px;
  height: 100%;
  border-radius: 20px 0 0 20px;
`;

export const ImgDiv2 = styled.div`
  max-width: 210px;
  min-width: 210px;
  height: 100%;
  border-radius: 20px 0 0 20px;
  background-color: grey;  // 여기부터 아래까지는
  display: flex;           // 빼도 됨
  align-items: center;     // 글자 가운데 보려고
  justify-content: center; // 넣은거임
`;

export const ImgDivInfo2 = styled.div`
  width: auto;
  height: 100%;
  padding: 0 20px;
  border-left: none;
  border-radius: 0 10px 10px 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 20px; // 사이 간격
`;

export const H1 = styled.h1`
    margin: 20px 0 20px 0;
`;

export const MarginTop = styled.main`
  margin-top: 51.5px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절 // 바꿀꺼면 51.5로
  width: 1280px;
  position: fixed; // 위치 고정
`;

export const Container = styled.div`
  display: flex;
  align-items: flex-start;
`;

export const ASide = styled.div`
  align-items: center; // 자식 요소를 수직으로 중앙 정렬
  width: 160px;
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  margin-top:20px;
`;

export const Main = styled.div`
  width: 1056px;
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  margin-left: 20px; 
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  margin-top:20px;
`;

export const 왼쪽광고 = styled.div`
  width: 100px;
  height: 513px;
  margin: 126px 655px 0 0;
  border: 1px solid #000;
  border-radius: 10px;
  background-color: #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
`;
export const 오른쪽광고 = styled.div`
  width: 100px;
  height: 513px;
  margin: 126px 0 0 655px;
  border: 1px solid #000;
  border-radius: 10px;
  background-color: #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
`;

// ────────────────────── 아래 부분은 쇼 디테일 페이지  ──────────────────────

export const ShowMain = styled.div`
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const ShowSide = styled.div`
  margin-left: 20px;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const Scroll = styled.div`
    overflow-y: auto; // 내부 수직 스크롤바
    max-height: 90.5vh;  // 계산 끝
`;

export const SideTop = styled.div`
  align-items: center; // 자식 요소를 수직으로 중앙 정렬
  width: 330px;
  border: lightgray solid 1px; // 실선 테두리
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  margin:20px 0;
`;

export const SideBottom = styled.div`
  display: flex;
  width: 332px;
  height: 50px;
  margin-bottom: 20px;
  border-radius: 15px;
  align-items: center;
  justify-content: center;
  background-color: #8e43e7; 
  cursor: pointer;
  color: white;
  font-size: 20px;
  font-weight: bold;
  letter-spacing: 0.1em;
`;

export const StyledCalendar = styled(Calendar)`
  border: none;

  .react-calendar__navigation { // 년, 월
    margin-bottom: 0px
  }

  .react-calendar__navigation__label { // 년, 월
    font-size: 17px;
  }

  .react-calendar__navigation__arrow { // 년, 월 옆 네비게이션 버튼
    font-size: 22px;
  }

  .react-calendar__month-view__weekdays { // 요일 전체 div
    background-color: #f0f0f0; /* 밝은 회색 배경 설정 */
  }

  .react-calendar__month-view__weekdays__weekday {
    font-size: 16px;
  }

  .react-calendar__month-view__days__day--weekend { // 주말 숫자
    color: #000000; /* 주말의 기본 색상 (토요일, 일요일) */
  }

  .react-calendar__month-view__days__day { // 일일(숫자)
    font-size: 16px;
  }
  
  .react-calendar__navigation__prev2-button,
  .react-calendar__navigation__next2-button { // 년 이동 버튼
    display: none;
  }

  .react-calendar__navigation__prev-button { // 이전월 이동 버튼
    color: #CCC;
    margin-left: 40px;
  }
  .react-calendar__navigation__next-button { // 다음월 이동 버튼
    color: #CCC;
    margin-right: 40px;
  }
`;

export const PostImg = styled.img`
  width: 300px;
  height: 400px;
  margin-top: 20px;
`;

export const PostDetailImg = styled.img`
  width: auto; /* 이미지의 원본 크기로 표시하도록 설정 */
  max-width: 926px; /* 최대 너비를 926px로 제한 (원본보다 큰 경우에만 적용) */
  margin: 10px 0 20px 0;
`;

export const PostInfo = styled.div`
  width: 584px;
  height: 400px;
  margin-left: 40px;
  margin-top: 20px;
`;

export const LightGrayLine = styled.div`
  width: 100%;
  border-top: lightgray solid 1px;
`;

export const SideFont = styled.div`
  text-align: left;
  font-size: 14px;
  font-weight: bold;
`;
export const SideMarginTop = styled.div`
  width: 90%;
  margin-top: 10px;
`;
export const SideMargin = styled.div`
  width: 90%;
  margin: 10px 0;
`;

export const ButtonList = styled.div`
  margin-top: 5px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
`;

export const ChoiceDiv = styled.div`
  text-align: center;
  font-size: 14px;
  border: 1px solid ${props => (props.selected ? '#8e43e7' : 'gray')};
  border-radius: 5px;
  padding: 10px;
  cursor: ${props => (props.onClick ? 'pointer' : 'default')};
  background-color: ${props => (props.selected ? '#8e43e7' : 'white')};
  font-weight: ${props => (props.selected ? 'bold' : 'normal')}; // Added font-weight property

  &:hover {
    background-color: ${props => (props.selected ? '#8e43e7' : props.onClick ? '#f0f0f0' : 'white')};
  }

  ${props => props.selected && props.selectedTime && css`
    background-color: #8e43e7;
    font-weight: bold;
  `}
`;

export const Th  = styled.th`
  width: 80px;
  text-align: left;
  padding: 8px 0px;
`;
export const Th1  = styled.th`
  width: 80px;
  text-align: left;
  padding: 8px 0px;
  vertical-align: top;
`;

export const Th2  = styled.th`
  width: 60px;
  text-align: left;
  padding: 8px 0px;
`;