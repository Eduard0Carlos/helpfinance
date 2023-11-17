package com.helpfinance.core.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultObject {
    public Boolean success;
    public List<String> errors;

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    public Object data;

    public ResultObject(Object data) {
        this.success = true;
        this.data = data;
    }

    public ResultObject(List<String> errors) {
        this.success = false;
        this.errors = errors;
    }
}
