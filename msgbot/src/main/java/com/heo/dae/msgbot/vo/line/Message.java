package com.heo.dae.msgbot.vo.line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Message {

    @Value("${line.user_id}")
    String LINE_USER_ID;
    
    Map<String, Object> req;

    public Map<String,Object> getMessage(){
        return req;
    }

    public Map<String,Object> getRequest(String msg){
        req = new HashMap<String, Object>();

        req.put("to", LINE_USER_ID);

        List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
        
        Map<String,String> message = new HashMap<String,String>();
        message.put("type", "text");
        message.put("text", msg);

        messages.add(message);

        req.put("messages", messages);

        return req;
    }
}
