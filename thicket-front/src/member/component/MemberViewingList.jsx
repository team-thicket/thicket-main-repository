import React from "react";
import {Container, H1, Table, Td, Th} from '../../assets/css/setting/admin/StylesOfList';

const tmp = [
    { // 사용자 관람한 정보 조회 URL: /reservations/past
        "id": "e4e64cbb-cc73-40b4-805e-62be18ab47e",
        "createdAt": "2023.09.09.", // 예매일
        "stageName": "뮤지컬 <마리퀴리>",  // 이름
        "stagetype": "Musical",
        "place": "홍익대 대학로 아트센터 대극장",    // 장소
        "date": "2023.12.31. 09:30", // 오픈시간
        "status": "상태뭔데"
    },
    {
        "id": "string2",
        "createdAt": "string2",
        "stageName": "청소년극 <발가락 육상천재>",
        "stagetype": "Play",
        "place": "국립극단 소극장판",
        "date": "string2",
        "status": "무슨상태"
    },
    {
        "id": "string3",
        "createdAt": "string3",
        "stageName": "Concert",
        "stagetype": "Concert",
        "place": "string3",
        "date": "string3",
        "status": "결제상태"
    }
];

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
                        <Th width="120px">예매일</Th>
                        <Th width="115px">티켓고유번호</Th>
                        <Th width="70px">구분</Th>
                        <Th width="220px">공연명</Th>
                        <Th width="auto">장소</Th>
                        <Th width="120px">공연일</Th>
                        <Th width="85px">상태</Th>
                    </tr>
                    {tmp.map((item) => (
                        <tr key={item.id}>
                            <Td>{item.createdAt}</Td>
                            <Td>{item.id.slice(0, 4)}**{item.id.slice(-2)}</Td>
                            <Td>{item.stagetype}</Td>
                            <Td>{item.stageName}</Td>
                            <Td>{item.place}</Td>
                            <Td>{item.date}</Td>
                            <Td>{item.status}</Td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};