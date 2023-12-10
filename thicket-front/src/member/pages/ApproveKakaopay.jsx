import {useEffect, useState} from "react";

export default function ApproveKakaopay() {
    const [tid, setTid] = useState("");
    const [method, setMethod] = useState("");
    const [amount, setAmount] = useState({});
    const [cardInfo, setCardInfo] = useState({});
    const [itemName, setItemName] = useState("");
    const [itemCode, setItemCode] = useState("");

    useEffect(() => {
        const currentUrl = window.location.href;
        const searchParams = new URL(currentUrl).searchParams;
        const token = searchParams.get("pg_token");
        if (!token) {
            window.close();
            return;
        }
        const payinfo = window.parent.opener;
        fetch(`/approve`, {
            method: "POST",
            headers:{
                "Content-Type":"application/json;charset=utf-8"
            },
            cache: "no-cache",
            body: JSON.stringify({
                "tid": payinfo.tid,
                "paymentId": payinfo.paymentId,
                "pgToken": token
            })
        })
        .then(res => res.json())
        .then(data => {
            setTid(data.tid);
            setMethod(data.payment_method_type);
            setAmount(data.amount);
            setCardInfo(data.card_info);
            setItemName(data.item_name);
            setItemCode(data.item_code);
        })
    }, []);

    return(
        <div>
            <p><span>결제 코드 : {tid}</span></p>
            <p><span>결제 방법 : {method}</span></p>
            <p><span>금액 : {amount.total}</span></p>
            <p><span>부가세 : {amount.vat}</span></p>
            <p><span>카드사 정보 : {cardInfo.purchase_corp}</span></p>
            <p><span>상품명 : {itemName}</span></p>
            <p><span>상품 코드 : {itemCode}</span></p>
        </div>
    )
}