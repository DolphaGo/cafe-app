package com.cafe.dolphago.service;

import com.cafe.dolphago.web.dto.kakaoPay.KakaoPayApprovalRequestDto;
import com.cafe.dolphago.web.dto.kakaoPay.KakaoPayReadyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Log
@RequiredArgsConstructor
@Service
public class KakaoPayService {

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalRequestDto kakaoPayApprovalRequestDto;
    private static final String HOST = "https://kapi.kakao.com";


    @Transactional
    public String kakaoPayReady(Long ooid,String orderuser,String mainMenu,int Totalcnt, int TotalPay) {
        System.out.println("카카오 페이 결제를 위한 준비 단계입니다.");
        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "1484a35f7612f9d9034e284849f3e71f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        System.out.println("요청 헤더 : "+headers);
        // 서버로 요청할 Body
        String item_name=mainMenu;
        if(Totalcnt-1>0) item_name=item_name+"외 "+(Totalcnt-1)+"건";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", ooid+"");
        params.add("partner_user_id", orderuser);
        params.add("item_name",item_name); //맨 위에거 기준으로만 결제로 넘김
        params.add("quantity",Totalcnt+"");
        params.add("total_amount", TotalPay+"");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        System.out.println("요청 Payload : "+params);
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            System.out.println("" + kakaoPayReadyVO);


            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "/pay";
    }


    public KakaoPayApprovalRequestDto kakaoPayInfo(String pg_token, Long ooid,String orderuser,int TotalPay) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "1484a35f7612f9d9034e284849f3e71f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", ooid+"");
        params.add("partner_user_id", orderuser);
        params.add("pg_token", pg_token);
        params.add("total_amount", TotalPay+"");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalRequestDto = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalRequestDto.class);
            log.info("" + kakaoPayApprovalRequestDto);

            return kakaoPayApprovalRequestDto;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


}

