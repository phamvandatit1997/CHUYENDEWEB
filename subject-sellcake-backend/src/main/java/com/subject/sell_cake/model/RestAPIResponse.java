package com.subject.sell_cake.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.subject.sell_cake.response.APIStatus;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestAPIResponse<T extends Object> implements Serializable {
    private int status;
    private String message;
    private T content;

    public RestAPIResponse(APIStatus apiStatus,T content)
    {
        if (apiStatus == null)
        {
            throw new IllegalArgumentException("APIStatus must not be null");
        }
        this.status = apiStatus.getStatus();
        this.message = apiStatus.getDescription();
        this.content = content;
    }
    public RestAPIResponse(APIStatus apiStatus)
    {
        this.status = apiStatus.getStatus();
        this.message = apiStatus.getDescription();
    }
    public int getStatus() {
        return status;
    }

    public T getData() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public void setData(T content) {
        this.content = content;
    }
}
