import {useEffect, useState} from "react";


export default function ReadyKakaopay(){
    const [tid, setTid] = useState("");
    const paymentId = "439ff30f-6ed2-48e4-9a8d-c529cb6c0597";
    useEffect(() => {
        fetch(`/ready?paymentId=${paymentId}`,{
            method: "GET",
            cache: "no-cache"
        })
        .then(res => res.json())
        .then(data => {
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
        </div>
    )
}