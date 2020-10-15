package com.heo.dae.msgbot.service;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.Messenger;
import com.heo.dae.msgbot.interfaces.MessengerDetail;

import org.springframework.stereotype.Service;

@Service
public class MessengerImpl implements Messenger {
    private final MessengerDetail slack, line;

    public MessengerImpl(MessengerDetail slack, MessengerDetail line) {
        this.slack = slack;
        this.line = line;
    }

    @Override
    public void send(String msg, List<Messengers> list) {
        for (Messengers type : list) {
            try {
                getMessenger(type).send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public MessengerDetail getMessenger(Messengers type) throws Exception {
        switch (type) {
            case SLACK:
                return slack;
            case LINE:
                return line;
            default:
                throw new Exception("설정된 타입이 아닙니다.");
        }
    }
}
