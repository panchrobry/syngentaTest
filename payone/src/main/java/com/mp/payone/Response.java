package com.mp.payone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("TxId")
    private Long txid;
    @JsonProperty("UserId")
    private Long userid;
    @JsonProperty("RedirectUrl")
    private String redirecturl;
    private String errorcode;
    private String errormessage;
    private String customermessage;
}
