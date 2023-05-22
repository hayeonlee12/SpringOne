package com.codingbox.sprip.payment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VerifyPaymentRequestDTO {
    private String impUid; // 아임포트 거래 고유번호
    private String merchantUid; // 상점 거래 고유번호

    public VerifyPaymentRequestDTO() {
    }

    public VerifyPaymentRequestDTO(String impUid, String merchantUid) {
        this.impUid = impUid;
        this.merchantUid = merchantUid;
    }

    
}