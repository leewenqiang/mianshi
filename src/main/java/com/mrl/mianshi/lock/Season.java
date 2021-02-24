package com.mrl.mianshi.lock;

public enum Season {

    /**
     * 春天
     */
    SPRING(1,"春天"),
    /**
     * 夏天
     */
    SUMMBER(2,"夏天"),
    /**
     * 秋天
     */
    ATOM(3,"秋天"),
    /**
     * 冬天
     */
    WQINTER(4,"冬天");

    private Integer code;
    private String msg;

    Season(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
