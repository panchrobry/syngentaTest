package com.mp.payone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/payone/api")
public class PayoneRestAPI {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String handleJSONRequest(@RequestBody String data) {
        log.error(String.format("JSON request [%s]", data));
        return "TSOK";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String handleUrlEncodedRequest(@RequestBody String data) {
        log.error(String.format("Url encoded request [%s]", data));
        return "TSOK";
    }

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String handleOtherRequest(@RequestBody String data) {
        log.error(String.format("Other request [%s]", data));
        return "TSOK";
    }

    @PostMapping("/payment")
    public RedirectView payment(@ModelAttribute PaymentData paymentData) throws IllegalAccessException {
        String url = "https://api.pay1.de/post-gateway/";

        paymentData.setRequest("authorization");
        paymentData.setClearingtype("cc");
        paymentData.setReference(((int)(Math.random() * 1000000000)) + "");
        paymentData.setAmount(1000L);
        paymentData.setCurrency("EUR");
        paymentData.setCity("Kiel");
        paymentData.setCountry("DE");
        paymentData.setEmail("ahenry.viii@tudor.gov.uk");
        paymentData.setFirstname("Henry");
        paymentData.setLanguage("de");
        paymentData.setLastname("Tudor");
        paymentData.setStreet("Royal Street 1");
        paymentData.setZip("24118");
        paymentData.setSuccessurl("https://syngentapayone.herokuapp.com/payment/success?reference=" + paymentData.getReference());
        paymentData.setErrorurl("https://syngentapayone.herokuapp.com/payment/error?reference=" + paymentData.getReference());
        paymentData.setBackurl("https://syngentapayone.herokuapp.com/payment/back?reference=" + paymentData.getReference());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Field[] allFields = PaymentData.class.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(paymentData);
            if (value != null) {
                map.add(name, value);
            }
            field.setAccessible(false);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Response> response = restTemplate.postForEntity(url, entity, Response.class);

        Response body = response.getBody();
        if ("REDIRECT".equals(body.getStatus())) {
            return new RedirectView(body.getRedirecturl());
        }
        if (response.getStatusCode() != HttpStatus.NO_CONTENT || !"APPROVED".equals(body.getStatus())) {
            return new RedirectView("/payment/error");
        }
        log.info("body = " + body + ", REFERENCE = " + paymentData.getReference());
        return new RedirectView("/payment/success");
    }

    @PostMapping(value = "/refund")
    public void refund(@ModelAttribute RefundData refundData) throws IllegalAccessException {
        String url = "https://api.pay1.de/post-gateway/";

        refundData.setRequest("refund");
        refundData.setAmount(-1000L);
        refundData.setCurrency("EUR");
        refundData.setSequencenumber(1L);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Field[] allFields = RefundData.class.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(refundData);
            if (value != null) {
                map.add(name, value);
            }
            field.setAccessible(false);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

        //ResponseEntity<Response> response = restTemplate.postForEntity(url, entity, Response.class);

        //Response body = response.getBody();
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("");
    }

    @PostMapping(value = "/capture")
    public void capture(@RequestBody PaymentData paymentData) throws IllegalAccessException {
        String url = "https://api.pay1.de/post-gateway/";
        paymentData.setRequest("capture");
        paymentData.setAmount(1000L);
        paymentData.setSequence(1L);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Field[] allFields = PaymentData.class.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(paymentData);
            if (value != null) {
                map.add(name, value);
            }
            field.setAccessible(false);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<Response> response = restTemplate.postForEntity(url, entity, Response.class);

        Response body = response.getBody();
    }

    @GetMapping("/hash")
    public String hash(String message) throws InvalidKeyException, NoSuchAlgorithmException {
        return HashUtil.sha384(message);
    }
}
