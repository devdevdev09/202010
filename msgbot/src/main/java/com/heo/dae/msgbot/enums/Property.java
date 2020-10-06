package com.heo.dae.msgbot.enums;

public enum Property {
    SLACK_WEBHOOK("slack.webhook");

    private String name;

    private Property(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
}
