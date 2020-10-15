package com.heo.dae.msgbot.vo;

import java.util.List;

import com.heo.dae.msgbot.enums.Messengers;

import lombok.Data;

@Data
public class MsgBody {
    List<Messengers> typeList;
    String message;
}
