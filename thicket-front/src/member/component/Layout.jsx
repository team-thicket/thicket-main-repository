import Main from "../pages/Main";
import Header from "./Header";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Mypage from "../pages/Mypage";
import React, {createContext, useState} from "react";
import AdminPage from "../pages/AdminPage";
import ReadyKakaopay from "../pages/ReadyKakaopay";
import ApproveKakaopay from "../pages/ApproveKakaopay";
import Login from "../pages/Login";
import AuthPage from "../pages/AuthPage";
import SignUp from "../pages/SignUp";

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
                        <Route path="mypage" element={<Mypage />} />
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
