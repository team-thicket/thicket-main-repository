import {Container, H1, Table, TableHeaderRow, Td, TdNotCenter} from '../../assets/css/setting/admin/StylesOfList';

export const AdminOngoingList = () => {
    return (
        <Container>
            <div>
                <H1>공 연 중</H1>
                <Table>
                    <tbody>
                    <TableHeaderRow />
                    <tr>
                        <Td>2</Td>
                        <Td>연극</Td>
                        <TdNotCenter>
                            4D공포연극
                        </TdNotCenter>
                        <Td>진행중</Td>
                        <Td>2022.12.01.</Td>
                        <Td>2023.12.31.</Td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};