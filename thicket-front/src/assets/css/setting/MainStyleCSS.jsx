import styled from "styled-components";

export const Wrapper = styled.div`
  display: flex; // Flex 컨테이너로 설정하여 자식 요소들을 플렉스 방향으로 배치
  width: 100%; // 부모 요소에 대해 100%의 너비를 차지
  justify-content: center;
`
export const ASide = styled.div`
  align-items: center; // 자식 요소를 수직으로 중앙 정렬
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  margin: 70px 1098px 20px 0; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  width: 160px;
  display: inline-block; // 인라인-블록 요소로 설정
  position: fixed; // 위치 고정
`
export const Main = styled.div`
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  margin: 70px 0 20px 202px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  width: 1056px;
  height: auto; // 부모 요소 대비 100%의 높이 할당
  display: inline-block; // 인라인-블록 요소로 설정
  position: fixed; // 위치 고정
`

export const InvisibleScroll = styled.main`
  margin-top: 70px; // 로거해더 이미지 크기에 따라 마진탑값 수동조절
  width: 1280px;
  height: auto;
  position: fixed; // 위치 고정
  display: flex;
  justify-content:center;
  align-items : center;
  ::-webkit-scrollbar {
    display: none;
  }
`;

export const MainContainer = styled.div`
    overflow-y: auto; // 내부 수직 스크롤바
    max-height: 85vh; // 
`;

export const Poster = styled.div`
  display: inline-block;
  width: 252px;
  height: 200px;
  margin: 1px 1px 40px 1px;
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리, 사이즈 확인
`;

export const RankCard = ({rank}) => {
    return (
        <Poster>
            <img/>
            <div>{rank}</div>
            <div>제목</div>
            <div>살려주세요</div>
        </Poster>
    )
};

export const OpenCard = ({date}) => {
    return (
        <Poster>
            <img/>
            <div>{date}</div>
            <div>제목</div>
            <div>살려주세요</div>
        </Poster>
    )
};