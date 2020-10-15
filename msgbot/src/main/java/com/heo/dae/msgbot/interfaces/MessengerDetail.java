package com.heo.dae.msgbot.interfaces;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;

public interface MessengerDetail extends InitializingBean {
    public boolean send(String msg);

    public Map<String, Object> setRequestBody();

    public Map<String, Object> setMessage(String msg, Map<String, Object> requestBody);

    public HttpHeaders createHeaders();

}
