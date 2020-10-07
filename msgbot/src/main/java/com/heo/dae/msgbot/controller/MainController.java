package com.heo.dae.msgbot.controller;

import com.heo.dae.msgbot.messenger.Slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    Slack slack;

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        slack.send("test");
    }
}
