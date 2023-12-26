import {useEffect, useState} from "react";

export default function ReadyKakaopay({ amount }){
    const [tid, setTid] = useState("");
    const paymentId = "d74240ab-c25c-4051-b11e-a7c74100209f";
    useEffect(() => {
        fetch(`/thicket-ticket/ready?paymentId=${paymentId}`,{
            method: "GET",
            cache: "no-cache"
        })
        .then(res => res.json())
        .then(data => {
            window.tid = data.tid;
            window.paymentId = paymentId;
            const width = 430;
            const height = 500;
            const left = window.screenX + (window.outerWidth - width) / 2;
            const top = window.screenY + (window.outerHeight - height) / 2;
            const paymentPopup = window.open(null,
                '결제중...',
                `width=${width}, height=${height}, left=${left}, top=${top}`);
            paymentPopup.document.write(`
                <iframe width="100%" height="100%" src=${data.next_redirect_pc_url}></iframe>`)
        })
    }, []);
    return(
        <div>
            <h2>결제 승인 대기 중입니다.</h2>
            <p>결제 금액: {amount.toLocaleString()}원</p>
        </div>
    )
}