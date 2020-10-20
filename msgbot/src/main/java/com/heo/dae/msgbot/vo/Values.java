package com.heo.dae.msgbot.vo;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Values {
    @Value("${line.channel_access_token}")
    public String LINE_CHANNEL_ACCESS_TOKEN;

    @Value("${line.push_api_url}")
    public String PUSH_API_URL;

    @Value("${slack.webhook}")
    public String WEBHOOK;

    @Value("${slack.username}")
    public String USERNAME;
    
    @Value("${line.user_id}")
    public String LINE_USER_ID;

    @Value("${priority.list}")
    public List<Messengers> PRIORITY_LIST;

    @Value("${kakao.bearer.key")
    public String KAKAO_BEARER_KEY;
}
