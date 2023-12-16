import Main from "../pages/Main";
import Header from "./Header";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import MyPage from "../pages/MyPage";
import React, {createContext, useState} from "react";
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

export const EmailContext = createContext();

function Layout() {
    const [email, setEmail] = useState("");

    return (
        <EmailContext.Provider value={{ email, setEmail }}>
            <>
                <Header />
                <Router>
                    <Routes>
                        <Route index element={<Main />} />
                        <Route path="musical" element={<MainMusicalList />} />
                        <Route path="play" element={<MainPlayList />} />
                        <Route path="concert" element={<MainConcertList />} />
                        <Route path="soon" element={<MainOpeningSoon />} />
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
        </EmailContext.Provider>
    );
}
export default Layout;
