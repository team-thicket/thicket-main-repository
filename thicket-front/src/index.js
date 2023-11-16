import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import "./assets/css/style.css";
import Withdraw from './member/component/withdraw';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <div>
        <App />
        <Withdraw />
    </div>
);
