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

@Service
public class RequestDataImpl implements RequestData {
    private final Values values;

    public RequestDataImpl(Values values){
        this.values = values;
    }

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
        }
        
        return requestBody;
    }

    @Override
    public HttpHeaders setRequestHeader(Messengers type) {
        HttpHeaders headers = new HttpHeaders();

        if(type.equals(Messengers.LINE)){
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + values.LINE_CHANNEL_ACCESS_TOKEN);
        }

        return headers;
    }

    @Override
    public MessengerDetail getMessenger(Messengers type) {

        return null;
    }
    
}
