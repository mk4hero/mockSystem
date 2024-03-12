package com.ruoyi.common.enums;

public enum MsgType {

    JSON("json"), XML("xml"), FLSTR("flStr"), OTHER(""), UNKNOWN("");

    private String value;

    MsgType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static MsgType getKey(String type){
        for (MsgType constants : values()) {
            if (constants.getValue() == type) {
                return constants;
            }
        }
        return OTHER;
    }
}
