import React from "react";
import {
    Route,
    Routes,
    BrowserRouter as Router
} from "react-router-dom";
import Mypage from "./member/pages/Mypage";

import Main from "./member/pages/Main";
import Layout from "./member/component/Layout";


function App() {
  return (
      <Layout />
  );
}
export default App;
