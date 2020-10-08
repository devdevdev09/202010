package com.heo.dae.msgbot.messenger;

import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.vo.line.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Line implements Messenger {

    @Autowired
    RestUtil restClientUtil;

    @Autowired
    Message message;

    @Value("${line.push_api_url}")
    String PUSH_API_URL;

    @Override
    public boolean send(String msg) {
        try {
            Map<String, Object> req = message.getRequest(msg);

            restClientUtil.post(PUSH_API_URL, req, Messengers.LINE);    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
