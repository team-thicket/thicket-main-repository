import styled from "styled-components";
import Menu from "../component/Menu";
import React, {useState} from "react";
import {AdminAllStageList} from "../component/AdminAllStageList";
import AdminCreateStage from "../component/AdminCreateStage";
import {AdminMusicalList} from "../component/AdminMusicalList";
import {AdminPlayList} from "../component/AdminPlayList";
import {AdminConcertList} from "../component/AdminConcertList";

const Wrapper = styled.div`
  display: flex; // Flex 컨테이너로 설정하여 자식 요소들을 플렉스 방향으로 배치
  width: 100%; // 부모 요소에 대해 100%의 너비를 차지
`
const ASide = styled.div`
  align-items: center; // 자식 요소를 수직으로 중앙 정렬
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  margin: 20px; // 모든 측면에 20px의 바깥쪽 여백 적용
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  width: 15%; // 부모 요소 대비 15%의 너비 할당
  height: 100%; // 부모 요소 대비 100%의 높이 할당
  display: inline-block; // 인라인-블록 요소로 설정
`
const Main = styled.div`
  padding: 10px; // 모든 측면에 10px의 안쪽 여백 적용
  margin-bottom: 20px; // 아래에 20px의 바깥쪽 여백 적용
  margin-right: 20px; // 오른쪽에 20px의 바깥쪽 여백 적용
  margin-top: 20px; // 위에 20px의 바깥쪽 여백 적용
  border: black solid 1px; // 1px 두께의 검은색 실선 테두리
  border-radius: 5px; // 5px의 테두리를 둥글게 처리
  width: 77%; // 부모 요소 대비 77%의 너비 할당
  height: 100%; // 부모 요소 대비 100%의 높이 할당
  display: inline-block; // 인라인-블록 요소로 설정
`
const AdminPage = () => {
    const [content, setContent] = useState("all");

    const contentHandler = (selectedContent) => {
        setContent(selectedContent);
    };

    const selectComponent = {
        all: <AdminAllStageList contentHandler={contentHandler} />,
        create: <AdminCreateStage contentHandler={contentHandler} />,
        musical: <AdminMusicalList contentHandler={contentHandler} />,
        play: <AdminPlayList contentHandler={contentHandler} />,
        concert: <AdminConcertList contentHandler={contentHandler} />,
    };

    return (
        <Wrapper>
            <ASide>
                <Menu name={"공연 등록"} onClick={() => contentHandler("create")} />
                <Menu name={"전체 목록"} onClick={() => contentHandler("all")} />
                <Menu name={"뮤지컬"} onClick={() => contentHandler("musical")} />
                <Menu name={"연극"} onClick={() => contentHandler("play")} />
                <Menu name={"콘서트"} onClick={() => contentHandler("concert")} />
            </ASide>
            <Main>{selectComponent[content]}</Main>
        </Wrapper>
    );
};

export default AdminPage;