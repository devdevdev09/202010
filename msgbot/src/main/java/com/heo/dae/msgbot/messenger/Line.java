package com.heo.dae.msgbot.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Line implements Messenger {

    @Autowired
    RestUtil restClientUtil;

    @Autowired
    Values values;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (values.PUSH_API_URL.isEmpty()) {
            throw new PropertyException(Property.PUSH_API_URL);
        }
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            Map<String, Object> requestBody;
            requestBody = setRequestBody();
            requestBody = setMessage(msg, requestBody);

            status = restClientUtil.post(values.PUSH_API_URL, requestBody, Messengers.LINE);    
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
        List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
        
        Map<String,String> message = new HashMap<String,String>();
        message.put("type", "text");
        message.put("text", msg);

        messages.add(message);

        requestBody.put("messages", messages);

        return requestBody;
    }
}
