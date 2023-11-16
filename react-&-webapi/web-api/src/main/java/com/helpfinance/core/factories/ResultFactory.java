package com.helpfinance.core.factories;

import java.util.List;

import com.helpfinance.core.data.IHttpResult;
import com.helpfinance.core.data.HttpResult;

public class ResultFactory {
    public static <T> IHttpResult getSuccessResult(T data) {
        return new HttpResult(data);
    }

    public static <T> IHttpResult getFailureResult(List<String> errors) {
        return new HttpResult(errors);
    }
}
