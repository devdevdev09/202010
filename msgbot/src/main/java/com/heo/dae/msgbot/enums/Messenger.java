package com.heo.dae.msgbot.enums;

public enum Messenger {
    SLACK("슬랙"),KAKAO("카카오톡"),LINE("라인"),TELE("텔레그램");

    private String name;

    private Messenger(String name){
        this.name = name;
    }
}
