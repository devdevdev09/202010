package com.heo.dae.msgbot.interfaces;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;

public interface Messenger{

    /**
     * 전송할 텍스트를 받아서 해당 메신저로 전송하고,
     * 메시지 전송 결과를 리턴한다.
     * 
     * @param  msg 전송할 텍스트
     * 
     * @return 메시지 전송 성공/실패를 리턴한다. 
     * 
     */
	public boolean send(String msg, List<Messengers> list);
}
