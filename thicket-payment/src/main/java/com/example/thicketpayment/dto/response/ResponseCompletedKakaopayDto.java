package com.example.thicketpayment.dto.response;

import lombok.Data;

@Data
public class ResponseCompletedKakaopayDto {
    private String tid;
    private String paymentId;
    private String payment_method_type;
    private Amount amount;
    private CardInfo card_info;
    private String item_name;
    private String item_code;
    private Integer quantity;
    private String create_at;
    private String approved_at;

    @Data
    public class Amount {
        private Integer total;
        private Integer tax_free;
        private Integer vat;
        private Integer point;
        private Integer discount;
    }

    @Data
    private class CardInfo {
        private String purchase_corp;
        private String issuer_corp;
        private String kakaopay_purchase_corp;
        private String kakaopay_issuer_corp;
        private String bin;
        private String install_month;
        private String approve_id;
    }

}
