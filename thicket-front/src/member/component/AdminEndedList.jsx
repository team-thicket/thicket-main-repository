import {Container, H1, Table, TableHeaderRow, Td, TdNotCenter} from '../../assets/css/setting/admin/StylesOfList';

export const AdminEndedList = () => {
    return (
        <Container>
            <div>
                <H1>공연 종료</H1>
                <Table>
                    <tbody>
                    <TableHeaderRow />
                    <tr>
                        <Td>1</Td>
                        <Td>콘서트</Td>
                        <TdNotCenter>
                            임영웅 콘서트 IM HERO TOUR 2023 - 부산
                        </TdNotCenter>
                        <Td>공연종료</Td>
                        <Td>2023.11.08.</Td>
                        <Td>2023.11.10.</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};