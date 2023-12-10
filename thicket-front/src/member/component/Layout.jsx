import Main from "../pages/Main";
import Header from "./Header";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Mypage from "../pages/Mypage";
import React from "react";
import ReadyKakaopay from "../pages/ReadyKakaopay";
import ApproveKakaopay from "../pages/ApproveKakaopay";
// import AdminPage from "../pages/AdminPage";

function Layout() {
    return (
        <>
            <Header />
            <Router>
                <Routes>
                    <Route index element={<Main />} />
                    <Route path="mypage" element={<Mypage />} />
                    <Route path="admin" element={<AdminPage />} />
                    <Route path="payment" element={<ReadyKakaopay />} />
                    <Route path="paymentCallback" element={<ApproveKakaopay />}/>
                </Routes>
            </Router>
        </>
    );
}
export default Layout;
