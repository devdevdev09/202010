package com.heo.dae.msgbot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.MessengerDetail;
import com.heo.dae.msgbot.interfaces.RequestData;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestDataImpl implements RequestData {
    private final Values values;

    @Override
    public Map<String, Object> setRequestBody(Messengers type, String msg) {
        Map<String, Object> requestBody = new HashMap<String, Object>();

        if(type.equals(Messengers.SLACK)){
            requestBody.put("username", values.USERNAME);
            requestBody.put("text", msg);
        }else if(type.equals(Messengers.LINE)){
            requestBody.put("to", values.LINE_USER_ID);
            List<Map<String, String>> messages = new ArrayList<Map<String, String>>();

            Map<String, String> message = new HashMap<String, String>();
            message.put("type", "text");
            message.put("text", msg);

            messages.add(message);

            requestBody.put("messages", messages);
        }else if(type.equals(Messengers.KAKAOTALK)){
            Map<String, Object> template_object = new HashMap<String, Object>();
            template_object.put("object_type", "text");
            template_object.put("text", "spring boot test kakao rest");
            template_object.put("link", null);
            String s = "object_type=text&text=Springboot_test_kakao_rest&link=null";

            requestBody.put("template_object", s);

        }
        
        return requestBody;
    }

    @Override
    public HttpHeaders setRequestHeader(Messengers type) {
        HttpHeaders headers = new HttpHeaders();

        if(type.equals(Messengers.LINE)){
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + values.LINE_CHANNEL_ACCESS_TOKEN);
        }else if(type.equals(Messengers.KAKAOTALK)){
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            headers.add("Authorization", "Bearer " + values.KAKAO_BEARER_KEY);
        }

        return headers;
    }

    @Override
    public MessengerDetail getMessenger(Messengers type) {

        return null;
    }

    @Override
    public String getUrl(Messengers type) {
        String url = "";

        if(type.equals(Messengers.LINE)){
            url  = values.PUSH_API_URL;
        }else if(type.equals(Messengers.SLACK)){
            url = values.WEBHOOK;
        }else if(type.equals(Messengers.KAKAOTALK)){
            url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        }

        return url;
    }
    
}
