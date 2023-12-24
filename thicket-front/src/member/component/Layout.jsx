import MainAllList from "../pages/MainAllList";
import Header from "./Header";
import {BrowserRouter as Router, redirect, Route, Routes, useNavigate} from "react-router-dom";
import MyPage from "../pages/MyPage";
import React, {createContext, useEffect, useState} from "react";
import AdminPage from "../pages/AdminPage";
import ReadyKakaopay from "../pages/ReadyKakaopay";
import ApproveKakaopay from "../pages/ApproveKakaopay";
import Login from "../pages/Login";
import AuthPage from "../pages/AuthPage";
import SignUp from "../pages/SignUp";
import MainMusicalList from "../pages/MainMusicalList";
import MainPlayList from "../pages/MainPlayList";
import MainConcertList from "../pages/MainConcertList";
import MainOpeningSoon from "../pages/MainOpeningSoon";
import ShowDetailPage from "../pages/ShowDetailPage";

export const EmailContext = createContext();

function Layout() {
    return (
        <>
            <Header/>
            <Router>
                <Routes>
                    <Route index element={<MainAllList />} />
                    <Route path="musical" element={<MainMusicalList />} />
                    <Route path="play" element={<MainPlayList />} />
                    <Route path="concert" element={<MainConcertList />} />
                    <Route path="soon" element={<MainOpeningSoon />} />
                    <Route path="detail/:id" element={<ShowDetailPage />} />
                    <Route path="mypage" element={<MyPage />} />
                    <Route path="admin" element={<AdminPage />} />
                    <Route path="payment" element={<ReadyKakaopay />} />
                    <Route path="paymentCallback" element={<ApproveKakaopay />} />
                    <Route path="login" element={<Login />} />
                    <Route path="auth" element={<AuthPage />} />
                    <Route path="signup" element={<SignUp />} />
                </Routes>
            </Router>
        </>
    );
}
export default Layout;
