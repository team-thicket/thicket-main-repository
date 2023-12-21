import React from "react";
import {Container, H1, Table, Td, Th} from '../../assets/css/setting/admin/StylesOfList';

export const MemberCouponList = ({ contentHandler }) => {
    // const handleTitleClick = () => {
    //     contentHandler("클릭 이동방식. AdminBeforeList 참고");
    // };

    return (
        <Container>
            <div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <H1>보유 쿠폰</H1>
                </div>
                <Table>
                    <tbody>
                    <tr>
                        <Th width="auto">제목</Th>
                    </tr>
                    <tr>
                        <Td>추후 업데이트 예정입니다.</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};