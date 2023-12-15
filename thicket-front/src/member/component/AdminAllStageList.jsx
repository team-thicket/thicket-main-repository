import {Container, H1, Table, TableHeaderRow, Td, TdNotCenter} from '../../assets/css/setting/admin/StylesOfList';

export const AdminAllStageList = () => {
    return (
        <Container>
            <div>
                <H1>전체 목록</H1>
                <Table>
                    <tbody>
                    <TableHeaderRow />
                    <tr>
                        <Td>3</Td>
                        <Td>뮤지컬</Td>
                        <TdNotCenter>
                            2023 푸에르자부르타 웨이라 인 서울
                        </TdNotCenter>
                        <Td>공연예정</Td>
                        <Td>2024.01.14.</Td>
                        <Td>2024.02.15.</Td>
                    </tr>
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