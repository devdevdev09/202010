package com.heo.dae.msgbot.service;

import java.util.Map;

import com.heo.dae.msgbot.common.RestUtil;
import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.MessengerDetail;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class MessengerDetailImpl implements MessengerDetail {
    private final RestUtil restClientUtil;
    private final RequestDataImpl requestDataImpl;
    
    private Messengers type;

    public MessengerDetailImpl(RestUtil restClientUtil, RequestDataImpl requestDataImpl) {
        this.restClientUtil = restClientUtil;
        this.requestDataImpl = requestDataImpl;
    }

    @Override
    public boolean send(String msg) {
        int status = 0;

        try {
            HttpHeaders headers = requestDataImpl.setRequestHeader(type);
            Map<String, Object> requestBody = requestDataImpl.setRequestBody(type, msg);
            String url = requestDataImpl.getUrl(type);

            status = restClientUtil.post(url, requestBody, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (status == 200) ? true : false;
    }

    @Override
    public void setType(Messengers type) {
        this.type = type;
    }
    
}
