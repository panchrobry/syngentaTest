package com.mp.payone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = "/creditcard/check")
    public String payoneIframe() {
        return "check_credit_card_iframe";
    }

    @RequestMapping(value = "/creditcard/auto")
    public String payoneIframeWithAutodetection(Model model) {
        model.addAttribute("paymentData", new PaymentData());
        return "check_credit_card_automatic_detection";
    }

    @RequestMapping(value = "/frontend")
    public String frontend() {
        return "frontend";
    }

    @RequestMapping(value = "/payment/success")
    public String successPayment() {
        return "success";
    }

    @RequestMapping(value = "/payment/error")
    public String errorPayment() {
        return "failure";
    }

    @RequestMapping(value = "/payment/back")
    public String backUrlPayment() {
        return "back";
    }
}
