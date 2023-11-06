package com.helpfinance.core.data;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResult extends ResponseEntity<ResultObject> implements IHttpResult {
    public HttpResult(Object data) {
        super(new ResultObject(data), null, HttpStatus.OK);
    }

    public HttpResult(List<String> errors) {
        super(new ResultObject(errors), null, HttpStatus.BAD_REQUEST);
    }
}
