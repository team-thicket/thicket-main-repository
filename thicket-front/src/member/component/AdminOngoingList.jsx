import {Container, H1, Table, TableHeaderRow, Td, TdNotCenterAnd} from '../../assets/css/setting/admin/StylesOfList';

export const AdminOngoingList = ({contentHandler, showId}) => {
    const handleTitleClick = () => {
        contentHandler("entrance");
    };
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
                        <TdNotCenterAnd onClick={handleTitleClick}>
                            4D공포연극
                        </TdNotCenterAnd>
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