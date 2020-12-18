package com.mp.payone;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import java.util.Date;

/**
 * https://docs.payone.com/display/public/PLATFORM/Parameter+for+the+TransactionStatus+query
 */
@Data
public class TransactionStatus {
    @ToString.Exclude
    @FormParam("key")
    private String key;
    @FormParam("txaction")
    private String txAction;
    @FormParam("transaction_status")
    private String transactionStatus;
    @FormParam("notify_version")
    private String notifyVersion;
    @FormParam("mode")
    private String mode;
    @FormParam("portalid")
    private Long portalId;
    @FormParam("aid")
    private Long accountId;
    @FormParam("clearingtype")
    private String clearingType;
    @FormParam("txtime")
    private Date txTime;
    @FormParam("currency")
    private String currency;
    @FormParam("userid")
    private Long userId;
    @FormParam("customerid")
    private Long customerId;
    @ToString.Exclude
    @FormParam("param")
    private String param;
    private String token;

    private String transaction_param;


}
