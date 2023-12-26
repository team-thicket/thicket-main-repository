import React, {useEffect, useState} from "react";
import {Container, H1, Table, Td, Th} from '../../assets/css/setting/admin/StylesOfList';
import ReactDOM from "react-dom";
import Cancel from "../../payment/pages/Cancel";
import Reservation from "../../payment/pages/Reservation";

const tmp = [
    { // 사용자 예매 정보 조회 URL: /reservations/future
        "id": "e4e64cbb-cc73-40b4-805e-62be18ab47e4",
        "createdAt": "2023.09.09.", // 예매일
        "stageName": "뮤지컬 <마리퀴리>",  // 이름
        "stagetype": "Musical",
        "place": "홍익대 대학로 아트센터 대극장",    // 장소
        "date": "2023.12.31. 09:30", // 오픈시간
        "cancel": "취소",
        "status": "결제완료"
    },
    {
        "id": "string2",
        "createdAt": "string2",
        "stageName": "청소년극 <발가락 육상천재>",
        "stagetype": "Play",
        "place": "국립극단 소극장판",
        "date": "string2",
        "cancel": "2023.11.09.",
        "status": "결제대기"
    },
    {
        "id": "string3",
        "createdAt": "string3",
        "stageName": "Concert",
        "stagetype": "Concert",
        "place": "string3",
        "date": "string3",
        "cancel": "string3",
        "status": "string3"
    }
];

export const MemberTicketingList = () => {

    // useEffect(() => {
    //     fetch('/thicket-reservations/past',{
    //         method: "GET",
    //         headers: {
    //             "Authorization": localStorage.getItem('token')
    //         }
    //     })
    //         .then(res => res.json())
    //         .then(data => {
    //             setMemberName(data.name);
    //             setMemberBirth(data.birth);
    //             setMemberPhone(data.phoneNumber);
    //             setMemberEmail(data.email);
    //         })
    // }, []);

    const handleCancelClick = (item) => {
        const width = Math.floor(window.innerWidth * 0.7);
        const height = Math.floor(window.innerHeight * 0.8);
        const left = Math.floor((window.innerWidth - width) / 2);
        const top = Math.floor((window.innerHeight - height) / 2);

        const windowFeatures = `width=${width},height=${height},left=${left},top=${top}`;

        const cancelWindow = window.open('', '_blank', windowFeatures);

        ReactDOM.render(
            <Cancel/>,
            cancelWindow.document.body
        );
    };

    const isCancelable = (dateString) => {
        const currentDate = new Date();
        const reservationDate = new Date(dateString);
        const timeDifference = reservationDate - currentDate;
        const daysDifference = timeDifference / (1000 * 3600 * 24);
        return daysDifference > 3;
    };

    return (
        <Container>
            <div>
                <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
                    <div>
                        <H1>예매 내역</H1>
                    </div>
                    <div>
                        <a>취소 가능 일자 : 오픈 3일전</a>
                    </div>
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
                        <Th width="120px">예매취소</Th>
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
                            <Td>
                                {isCancelable(item.date) ? (
                                    <button onClick={() => handleCancelClick(item)}>{item.cancel}</button>
                                ) : (
                                    <span>취소불가</span>
                                )}
                            </Td>
                            <Td>{item.status}</Td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};