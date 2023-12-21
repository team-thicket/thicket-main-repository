import styled, {css} from 'styled-components';
import React from "react";

export const Container = styled.div`
    padding: 10px;
    overflow-y: auto; // 내부 수직 스크롤바
    max-height: 80vh;
`;

export const H1 = styled.h1`
    margin: 0 0 20px 0;
    padding-left: 5px;
`;

export const P = styled.p`
    margin: 0 0 10px 10px;
    font-size: 15px;
    color: #555;
    line-height: 1.7;
`;

export const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`;

export const Th = styled.th`
    text-align: center;
    height: 29px;
    padding: 10px;
    border-bottom: 1px solid #ccc;
    background: #f7f7f7;
    width: ${(props) => props.width || 'auto'};
    border-top: 1px solid #000;
`;

export const Td = styled.td`
    text-align: center;
    padding: 10px;
    border-bottom: 1px solid #ccc;
    background: white;
    border-top: 1px solid #000;
`;

const commonStyles = css`
    padding: 10px;
    border-bottom: 1px solid #ccc;
    background: white;
    border-top: 1px solid #000;
`;

export const TdNotCenter = styled.td`
    ${commonStyles};
`;

export const TdNotCenterAnd = styled.td`
    ${commonStyles};
    cursor: pointer;
    &:hover {
      background: #f0f0f0;
    }
`;

export const TableHeaderRow = () => {
    return (
        <tr>
            <Th width="60px">번호</Th>
            <Th width="60px">구분</Th>
            <Th width="auto">제목</Th>
            <Th width="70px">상태</Th>
            <Th width="102px">오픈일</Th>
            <Th width="102px">마감일</Th>
        </tr>
    );
};