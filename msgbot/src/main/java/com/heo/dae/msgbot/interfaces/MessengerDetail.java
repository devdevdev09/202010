package com.heo.dae.msgbot.interfaces;

import org.springframework.beans.factory.InitializingBean;

public interface MessengerDetail extends InitializingBean {
    public boolean send(String msg);
}
