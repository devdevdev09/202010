package com.heo.dae.msgbot.service;

import java.util.HashMap;
import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class Slack implements Messenger {

    private final RestUtil restClientUtil;
    private final Values values;

    public Slack(RestUtil restClRestUtil, Values values) {
        this.restClientUtil = restClRestUtil;
        this.values = values;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (values.WEBHOOK.isEmpty()) {
            throw new PropertyException(Property.SLACK_WEBHOOK);
        }
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            Map<String, Object> requestBody;

            requestBody = setRequestBody();
            requestBody = setMessage(msg, requestBody);

            status = restClientUtil.post(values.WEBHOOK, requestBody, Messengers.SLACK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (status == 200) ? true : false;
    }

    @Override
    public Map<String, Object> setRequestBody() {
        Map<String, Object> requestBody = new HashMap<String, Object>();

        requestBody.put("username", values.USERNAME);

        return requestBody;
    }

    @Override
    public Map<String, Object> setMessage(String msg, Map<String, Object> requestBody) {
        requestBody.put("text", msg);

        return requestBody;
    }

    @Override
    public HttpHeaders createHeaders() {
        return null;
    }

}
