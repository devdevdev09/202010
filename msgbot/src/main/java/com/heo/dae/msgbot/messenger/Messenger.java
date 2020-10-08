package com.heo.dae.msgbot.messenger;

import org.springframework.beans.factory.InitializingBean;

public interface Messenger extends InitializingBean {

    /**
     * 전송할 텍스트를 받아서 해당 메신저로 전송하고,
     * 메시지 전송 결과를 리턴한다.
     * 
     * @param  msg 전송할 텍스트
     * 
     * @return 메시지 전송 성공/실패를 리턴한다. 
     * 
     */
    public boolean send(String msg);
}
