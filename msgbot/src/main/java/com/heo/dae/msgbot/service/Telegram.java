package com.heo.dae.msgbot.service;

import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.beans.factory.annotation.Autowired;

public class Telegram implements Messenger {

    @Autowired
    RestUtil restClientUtil;

    @Autowired 
    Values values;

    @Override
    public void afterPropertiesSet() throws Exception {
        

    }

    @Override
    public boolean send(String msg) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Map<String, Object> setRequestBody() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> setMessage(String msg, Map<String, Object> requestBody) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
