package com.heo.dae.msgbot.vo.line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heo.dae.msgbot.vo.Values;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Message {

    @Autowired
    Values values;
    
    Map<String, Object> req;

    public Map<String,Object> getMessage(){
        return req;
    }

    public Map<String,Object> getRequest(String msg){
        req = new HashMap<String, Object>();

        req.put("to", values.LINE_USER_ID);

        List<Map<String,String>> messages = new ArrayList<Map<String,String>>();
        
        Map<String,String> message = new HashMap<String,String>();
        message.put("type", "text");
        message.put("text", msg);

        messages.add(message);

        req.put("messages", messages);

        return req;
    }
}
