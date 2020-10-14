package com.heo.dae.msgbot.enums;

public enum Property {
    SLACK_WEBHOOK("slack.webhook"),
    PUSH_API_URL("line.push_api_url"),
    LINE_CHANNEL_ACCESS_TOKEN("line.channel_access_token"),
    USERNAME("slack.username"),
    LINE_USER_ID("line.user_id"),
    ;

    private String name;

    private Property(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
}
