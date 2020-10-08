package com.heo.dae.msgbot.messenger;

import java.util.HashMap;
import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Slack implements InitializingBean, Messenger {

    @Value("${slack.webhook}")
    private String webhook;

    @Value("${slack.username}")
    private String username;

    @Autowired
    RestUtil restClientUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (webhook.isEmpty()) {
            throw new PropertyException(Property.SLACK_WEBHOOK);
        }
    }

    @Override
    public boolean send(String msg) {
        boolean result = false;
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("username", username);
        req.put("text", msg);

        try {
            restClientUtil.post(webhook, req, Messengers.SLACK);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        
        return result;
    }

}
