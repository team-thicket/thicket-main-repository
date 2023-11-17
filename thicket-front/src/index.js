import React from 'react';
import ReactDOM from 'react-dom/client';
import "./assets/css/style.css";
import Withdraw from './member/component/withdraw';
import {MemberMyPage} from './component/MemberMyPage';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <>
        <MemberMyPage />
        <Withdraw />
    </>
)
