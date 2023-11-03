package com.helpfinance.core.factories;

import com.helpfinance.core.data.Result;

public class ResultFactory {
    public static <T> Result getSuccessResult(T data) {
        return new Result(true, data);
    }

    public static <T> Result getFailureResult(T data) {
        return new Result(false, data);
    }
}
