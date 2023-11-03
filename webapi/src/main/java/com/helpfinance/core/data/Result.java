package com.helpfinance.core.data;

public class Result {
    public Boolean success;
    public Object data;

    public Result(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
