import React from "react";
import {Container, H1, Table, Td, Th} from '../../assets/css/setting/admin/StylesOfList';

export const MemberViewingList = ({ contentHandler }) => {
    // const handleTitleClick = () => {
    //     contentHandler("클릭 이동방식. AdminBeforeList 참고");
    // };

    return (
        <Container>
            <div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <H1>관람 내역</H1>
                </div>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="auto">공연일</Th>
                        <Th width="auto">티켓고유번호</Th>
                        <Th width="auto">구분</Th>
                        <Th width="auto">공연명</Th>
                        <Th width="auto">장소</Th>
                    </tr>
                    <tr>
                        <Td>2022.11.18 19:30</Td>
                        <Td>T23456</Td>
                        <Td>PLAY</Td>
                        <Td>청소년극 &lt;발가락 육상천재&gt;</Td>
                        <Td>국립극단 소극장판</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};