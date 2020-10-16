package com.heo.dae.msgbot.service;

import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.enums.Property;
import com.heo.dae.msgbot.exception.PropertyException;
import com.heo.dae.msgbot.interfaces.MessengerDetail;
import com.heo.dae.msgbot.vo.Values;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class Line implements MessengerDetail {
    private final RestUtil restClientUtil;
    private final Values values;
    private final RequestDataImpl requestDataImpl;

    public Line(RestUtil restClientUtil, Values values, RequestDataImpl requestDataImpl) {
        this.restClientUtil = restClientUtil;
        this.values = values;
        this.requestDataImpl = requestDataImpl;
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            HttpHeaders headers = requestDataImpl.setRequestHeader(Messengers.LINE);
            Map<String, Object> requestBody = requestDataImpl.setRequestBody(Messengers.LINE, msg);

            status = restClientUtil.post(values.PUSH_API_URL, requestBody, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (status == 200) ? true : false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (values.LINE_CHANNEL_ACCESS_TOKEN.isEmpty()) {
            throw new PropertyException(Property.LINE_CHANNEL_ACCESS_TOKEN);
        }
        if (values.LINE_USER_ID.isEmpty()) {
            throw new PropertyException(Property.LINE_USER_ID);
        }
    }
}
