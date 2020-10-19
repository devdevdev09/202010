package com.heo.dae.msgbot.service;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.Messenger;
import com.heo.dae.msgbot.interfaces.MessengerDetail;

import org.springframework.stereotype.Service;

@Service
public class MessengerImpl implements Messenger {
    private final MessengerDetail messengerDetailImpl;

    public MessengerImpl(MessengerDetail messengerDetailImpl) {
        this.messengerDetailImpl = messengerDetailImpl;
    }

    @Override
    public void send(String msg, List<Messengers> list) {
        for (Messengers type : list) {
            try {
                messengerDetailImpl.setType(type);
                messengerDetailImpl.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
