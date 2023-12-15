import React from "react";
import {Container, H1, Table, Td, TdNotCenterAnd, P, TableHeaderRow} from '../../assets/css/setting/admin/StylesOfList';

export const AdminBeforeList = ({ contentHandler }) => {
    const handleTitleClick = () => {
        contentHandler("edit");
    };

    return (
        <Container>
            <div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <H1>공연 예정</H1>
                    <P>
                        제목을 클릭하면 일부 내용을 수정할 수 있습니다.
                    </P>
                </div>
                <Table>
                    <tbody>
                    <TableHeaderRow />
                    <tr>
                        <Td>3</Td>
                        <Td>뮤지컬</Td>
                        <TdNotCenterAnd onClick={handleTitleClick}>
                            2023 푸에르자부르타 웨이라 인 서울
                        </TdNotCenterAnd>
                        <Td>공연예정</Td>
                        <Td>2024.01.14.</Td>
                        <Td>2024.02.15.</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};