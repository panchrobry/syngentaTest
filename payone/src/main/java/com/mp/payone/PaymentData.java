package com.mp.payone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentData {
    private Long aid = 47578L;
    private Long amount = 10000L;
    private String api_version = "3.11";
    private String city;
    private String clearingtype;
    private String country;
    private String currency = "EUR";
    private String email;
    private String encoding = "UTF-8";
    private String firstname;
    private String key = "cd39712dcc4f556bed63a0bd70b44060";
    private String language;
    private String lastname;
    private Long mid = 47552L;
    private String mode = "test";
    private Long portalid = 2034634L;
    private String reference;
    private String request;
    private String street;
    private String zip;
    private String pseudocardpan;
    private String truncatedcardpan;
    private String cardtype;
    private String cardexpiredate;
    private Long sequence;
    private Long txid;
    private String successurl;
    private String errorurl;
    private String backurl;



/*    private String request;
    private String pseudocardpan;
    private String truncatedcardpan;
    private String cardtype;
    private String cardexpiredate;
    private String clearingtype;
    private String reference;
    private String currency;
    private String customerid;
    private Long userId;
    private String firstname;
    private String lastname;
    private String country;
    private Long cardpan;
    private Long cardcvc2;
    //responsetype: 'JSON',                        // fixed value
    private String mode = "test";                                // desired mode
    private Long mid = 47552L;                                // your MID
    private Long portalid = 2034634L;                         // your PortalId*/
}
