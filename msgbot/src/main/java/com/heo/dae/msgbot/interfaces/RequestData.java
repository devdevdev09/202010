package com.heo.dae.msgbot.interfaces;

import java.util.Map;

import org.springframework.http.HttpHeaders;

public interface RequestData {
    public Map<String, Object> setRequestBody(MessengerDetail type, String msg);
    public HttpHeaders setRequestHeader(MessengerDetail type);
    public MessengerDetail getMessenger(MessengerDetail type);
}
