package com.heo.dae.msgbot.interfaces;

import com.heo.dae.msgbot.enums.Messengers;

public interface MessengerDetail {
    public boolean send(String msg);
    public void setType(Messengers type);
}
