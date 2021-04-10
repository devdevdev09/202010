package com.heo.dae.msgbot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.Messenger;
import com.heo.dae.msgbot.vo.MsgBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final Messenger messengerImpl;

    public MainController(Messenger messengerImpl) {
        this.messengerImpl = messengerImpl;
    }

    @PostMapping("/send")
    public void send(@RequestBody MsgBody body) {
        List<Messengers> list = body.getTypeList().stream().distinct().collect(Collectors.toList());
        String msg = body.getMessage();

        messengerImpl.send(msg, list);
    }

    @GetMapping("/sendtest")
    public void send(String msg) {
        List<Messengers> list = new ArrayList<>();
        list.add(Messengers.SLACK);

        String msgs = "test message";

        messengerImpl.send(msgs, list);
    }

    @GetMapping("/send")
    public void send() {
        List<Messengers> list = new ArrayList<>();
        list.add(Messengers.KAKAOTALK);

        String msg = "test message";

        messengerImpl.send(msg, list);
    }
}
