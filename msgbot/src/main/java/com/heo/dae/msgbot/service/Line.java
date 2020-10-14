package com.heo.dae.msgbot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class Line implements Messenger {
    private final RestUtil restClientUtil;
    private final Values values;

    public Line(RestUtil restClientUtil, Values values) {
        this.restClientUtil = restClientUtil;
        this.values = values;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Optional.ofNullable(values.LINE_CHANNEL_ACCESS_TOKEN).orElseThrow();
        Optional.ofNullable(values.PUSH_API_URL).orElseThrow();
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            HttpHeaders headers = createHeaders();
            
            Map<String, Object> requestBody;
            requestBody = setRequestBody();
            requestBody = setMessage(msg, requestBody);

            status = restClientUtil.post(values.PUSH_API_URL, requestBody, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (status == 200) ? true : false;
    }

    @Override
    public Map<String, Object> setRequestBody() {
        Map<String, Object> requestBody = new HashMap<String, Object>();

        requestBody.put("to", values.LINE_USER_ID);

        return requestBody;
    }

    @Override
    public Map<String, Object> setMessage(String msg, Map<String, Object> requestBody) {
        // 반드시 messages로 보내야 하는지??
        List<Map<String, String>> messages = new ArrayList<Map<String, String>>();

        Map<String, String> message = new HashMap<String, String>();
        message.put("type", "text");
        message.put("text", msg);

        messages.add(message);

        requestBody.put("messages", messages);

        return requestBody;
    }

    /**
     * create header
     * @return HttpHeaders
     */
    @Override
    public HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + values.LINE_CHANNEL_ACCESS_TOKEN);

        return headers;
    }
}
