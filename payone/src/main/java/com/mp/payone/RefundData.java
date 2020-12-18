package com.mp.payone;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefundData {
    private String request;
    private Long txid;
    private Long sequencenumber;
    private Long amount;
    private String currency = "EUR";
    private Long mid = 47552L;
    private Long portalid = 2034634L;
    private String key = "cd39712dcc4f556bed63a0bd70b44060";
    private String mode = "test";
    private String status = "APPROVED";
    private String transaction_param = "Test";
    //private String narrative_text;
}
