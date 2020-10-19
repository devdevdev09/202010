package com.heo.dae.msgbot.interfaces;

import java.util.Map;

import com.heo.dae.msgbot.enums.Messengers;

import org.springframework.http.HttpHeaders;

public interface RequestData {
    public Map<String, Object> setRequestBody(Messengers type, String msg);
    public HttpHeaders setRequestHeader(Messengers type);
    public MessengerDetail getMessenger(Messengers type);
    public String getUrl(Messengers type);
}
