package com.heo.dae.msgbot.enums;

public enum Messengers {
    SLACK("슬랙"),KAKAOTALK("카카오톡"),LINE("라인"),TELEGRAM("텔레그램");

    private String name;

    private Messengers(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
