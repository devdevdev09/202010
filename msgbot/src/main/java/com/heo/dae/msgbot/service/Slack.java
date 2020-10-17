package com.heo.dae.msgbot.service;

import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.MessengerDetail;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class Slack implements MessengerDetail {
    private final RestUtil restClientUtil;
    private final Values values;
    private final RequestDataImpl requestDataImpl;

    public Slack(RestUtil restClRestUtil, Values values, RequestDataImpl requestDataImpl) {
        this.restClientUtil = restClRestUtil;
        this.values = values;
        this.requestDataImpl = requestDataImpl;
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            HttpHeaders headers = requestDataImpl.setRequestHeader(Messengers.SLACK);
            Map<String, Object> requestBody = requestDataImpl.setRequestBody(Messengers.SLACK, msg);

            status = restClientUtil.post(values.WEBHOOK, requestBody, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (status == 200) ? true : false;
    }

}
