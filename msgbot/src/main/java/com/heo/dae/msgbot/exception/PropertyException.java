package com.heo.dae.msgbot.exception;

import com.heo.dae.msgbot.enums.Property;

public class PropertyException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4093029246396808897L;

    public PropertyException(Property property){
        super("application.yml > " + property.getName() + "의 값이 없습니다. 해당값을 입력하세요.");
    }
}
