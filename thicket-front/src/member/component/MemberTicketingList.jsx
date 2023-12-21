import React from "react";
import {Container, H1, Table, Td, Th} from '../../assets/css/setting/admin/StylesOfList';

export const MemberTicketingList = ({ contentHandler }) => {
    // const handleTitleClick = () => {
    //     contentHandler("클릭 이동방식. AdminBeforeList 참고");
    // };

    return (
        <Container>
            <div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <H1>예매 내역</H1>
                </div>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="auto">예매일</Th>
                        <Th width="auto">티켓고유번호</Th>
                        <Th width="auto">구분</Th>
                        <Th width="auto">공연명</Th>
                        <Th width="auto">장소</Th>
                        <Th width="auto">공연일</Th>
                        <Th width="auto">예매취소</Th>
                        <Th width="auto">상태</Th>
                    </tr>
                    <tr>
                        <Td>2023.11.09</Td>
                        <Td>T12345</Td>
                        <Td>MUSICAL</Td>
                        <Td>뮤지컬 &lt;마리퀴리&gt;</Td>
                        <Td>홍익대 대학로 아트센터 대극장</Td>
                        <Td>2023.11.25 19:30</Td>
                        <Td>예매 취소</Td>
                        <Td>결제 대기</Td>
                    </tr>
                    <tr>
                        <Td>2022.11.03</Td>
                        <Td>T23456</Td>
                        <Td>PLAY</Td>
                        <Td>청소년극 &lt;발가락 육상천재&gt;</Td>
                        <Td>국립극단 소극장판</Td>
                        <Td>2022.11.18 19:30</Td>
                        <Td>2023.11.16 23:59까지</Td>
                        <Td>예매 완료</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};