import {useEffect} from "react";

export default function ApproveKakaopay() {
    useEffect(() => {
        const currentUrl = window.location.href;
        const searchParams = new URL(currentUrl).searchParams;
        const token = searchParams.get("pg_token");
        if (!token) {
            window.close();
            return;
        }
        console.log(token);
            // fetch(`/approve`, {
            //     method: "GET",
            //     cache: "no-cache",
            //     body: JSON.stringify({
            //         "tid": kakao.kakaoTid,
            //         "paymentId": kakao.kakaoPaymentId,
            //         "pgToken": token
            //     })
            // })
            // .then(res => res.json())
            // .then(data => {
            //     console.log("호출됨")
            //     return () => {
            //         // window.removeEventListener("message", tokenListener);
            //         // readyPopup?.close();
            //         // setReadyPopup(null);
            //     }
            // })
    }, []);
}