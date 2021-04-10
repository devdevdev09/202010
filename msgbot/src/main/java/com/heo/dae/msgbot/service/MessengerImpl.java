package com.heo.dae.msgbot.service;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;
import com.heo.dae.msgbot.interfaces.Messenger;
import com.heo.dae.msgbot.interfaces.MessengerDetail;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessengerImpl implements Messenger {
    private final MessengerDetail messengerDetailImpl;

    @Override
    public boolean send(String msg, List<Messengers> list) {
        boolean result = false;
        for (Messengers type : list) {
            try {
                messengerDetailImpl.setType(type);
                result = messengerDetailImpl.send(msg);

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
