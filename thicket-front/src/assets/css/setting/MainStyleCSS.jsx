import styled from "styled-components";

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
  //border: black solid 1px; // 1px 두께의 검은색 실선 테두리, 사이즈 확인용
`;
export const MainContainer = styled.div`
    overflow-y: auto; // 내부 수직 스크롤바
    max-height: 90.5vh; // 계산 끝
`;

export const DivList1 = styled.div`
  margin-bottom: 40px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px 100px;
`;

export const Poster1 = styled.div`
  display: inline-block;
`;

export const Img1 = styled.img`
  width: 360px;
  height: 480px;
  border-radius: 10px 10px 0 0;
`;

export const ImgDiv1 = styled.div`
  width: 360px;
  height: 480px;
  border-radius: 10px 10px 0 0;
  background-color: grey;  // 여기부터 아래까지는
  display: flex;           // 빼도 됨
  align-items: center;     // 글자 가운데 보려고
  justify-content: center; // 넣은거임
`;

export const ImgDivInfo1 = styled.div`
  width: 318px;
  padding: 20px;
  border: 1px solid;
  border-top: none;
  border-radius: 0 0 10px 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 3px; // 균등분배
`;

export const RankCard = ({rank}) => {
    return (
        <div style={{ display: "inline-block"  }}>
            <Poster1>
                {/*<Img1 />*/}
                <ImgDiv1>이미지</ImgDiv1>
                <ImgDivInfo1>
                    <div>{rank}(랭킹) or 제목</div>
                    <div>장소</div>
                    <div>날짜</div>
                </ImgDivInfo1>
            </Poster1>
        </div>
    )
};

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
  border-radius: 10px 0 0 10px;
`;

export const ImgDiv2 = styled.div`
  max-width: 210px;
  min-width: 210px;
  height: 100%;
  border-radius: 10px 0 0 10px;
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

export const OpenCard = ({date}) => {
    return (
        <Poster2>
            <ImgDiv2>이미지</ImgDiv2>
            <ImgDivInfo2>
                <div><h1>{date}오늘 or 내일</h1></div>
                <div><h1>제목</h1></div>
                <div><h1>장소</h1></div>
            </ImgDivInfo2>
        </Poster2>
    )
};

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