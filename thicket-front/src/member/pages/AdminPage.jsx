import Menu from "../component/Menu";
import React, {useState} from "react";
import {AdminAllStageList} from "../component/AdminAllStageList";
import AdminCreateShow from "../component/AdminCreateShow";
import { AdminBeforeList } from "../component/AdminBeforeList";
import {AdminOngoingList} from "../component/AdminOngoingList";
import {AdminEndedList} from "../component/AdminEndedList";
import AdminEditShow from "../component/AdminEditShow";
import {ASide, Main, Wrapper} from "../../assets/css/setting/MainStyleCSS";

const AdminPage = () => {
    const [content, setContent] = useState("all");
    const contentHandler = (selectedContent) => {
        setContent(selectedContent);
    };

    const selectComponent = {
        all: <AdminAllStageList />,
        create: <AdminCreateShow />,
        before: <AdminBeforeList contentHandler={contentHandler} />,
        ongoing: <AdminOngoingList />,
        ended: <AdminEndedList />,
        edit: <AdminEditShow />,
    };

    return (
        <Wrapper>
            <ASide>
                <Menu name={"공연 등록"} onClick={() => contentHandler("create")} />
                <Menu name={"전체 목록"} onClick={() => contentHandler("all")} />
                <Menu name={"공연 예정"} onClick={() => contentHandler("before")} />
                <Menu name={"공 연 중"} onClick={() => contentHandler("ongoing")} />
                <Menu name={"공연 종료"} onClick={() => contentHandler("ended")} />
            </ASide>
            <Main>{selectComponent[content]}</Main>
        </Wrapper>
    );
};

export default AdminPage;