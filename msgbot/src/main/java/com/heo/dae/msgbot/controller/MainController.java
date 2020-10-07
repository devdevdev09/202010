package com.heo.dae.msgbot.controller;

import java.util.List;
import java.util.Map;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.messenger.Kakaotalk;
import com.heo.dae.msgbot.messenger.Line;
import com.heo.dae.msgbot.messenger.Slack;
import com.heo.dae.msgbot.messenger.Telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    // @Autowired
    // Kakaotalk kakaotalk;

    @Autowired
    Slack slack;

    // @Autowired
    // Line line;

    // @Autowired
    // Telegram Telegram;

    @Value("${priority.list}")
    List<Messengers> list;

    // @PostMapping("/send")
    @GetMapping("/send")
    // public void test(@RequestBody Map<String, Object> requestBody){
    public void test(){
        // String msg = (String)requestBody.get("msg");
        // 설정한 우선순위로 메시지 발송

        // 순회 방법 고민....
        for(Messengers messenger : list){
            if(messenger.equals(Messengers.KAKAOTALK)){
                System.out.println("카카오톡 발송");
            }else{
                if(messenger.equals(Messengers.SLACK)){
                    System.out.println("슬랙 발송");
                }else{
                    if(messenger.equals(Messengers.LINE)){
                        System.out.println("라인 발송");
                    }else{
                        if(messenger.equals(Messengers.TELEGRAM)){
                            System.out.println("텔레그램 발송");
                        }else{
                            System.out.println("발송 X");
                        }
                    }
                }
            }
        }


    }
}
