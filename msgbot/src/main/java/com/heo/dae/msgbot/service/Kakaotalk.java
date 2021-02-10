package com.heo.dae.msgbot.service;

import java.net.URI;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.MessengerDetail;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Kakaotalk implements MessengerDetail {

    private RestTemplate restTemplate;

    @Value("${kakao.talk.key}")
    private String KAKAO_TALK_KEY;
    
    @Value("${kakao.url.memo}")
    private String url;

    public Kakaotalk(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean send(String msg) {
        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        JSONObject template_object = new JSONObject();
        
        template_object.put("object_type", "text");
        template_object.put("link", new JSONObject().put("web_url", "https://developers.kakao.com"));
        template_object.put("text", "text msg");
        template_object.put("button_title", "click");

        body.add("template_object", template_object.toString());

        RequestEntity request = RequestEntity
                                .post(URI.create(url))
                                .header("Authorization", "Bearer " + KAKAO_TALK_KEY)
                                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                                .body(body);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        
        return response.getStatusCode().equals(HttpStatus.OK);
    }

    @Override
    public void setType(Messengers type) {
        // TODO Auto-generated method stub

    }
    
}
