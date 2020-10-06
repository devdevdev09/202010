package com.heo.dae.msgbot.messenger;

import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Slack implements InitializingBean {

    @Value("${slack.webhook}")
    private String webhook;
    
    public void send(){
        System.out.println("webhook : " + webhook);    
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(webhook.isEmpty()){
            throw new PropertyException(Property.SLACK_WEBHOOK);
        }
    }
}
