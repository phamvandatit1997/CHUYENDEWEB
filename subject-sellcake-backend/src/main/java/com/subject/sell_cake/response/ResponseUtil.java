package com.subject.sell_cake.response;

import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseUtil {
    public RestAPIResponse _createResponse(APIStatus apiStatus,Object data)
    {
        return new RestAPIResponse(apiStatus,data);
    }

    public ResponseEntity<RestAPIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus)
    {
        return new ResponseEntity(_createResponse(apiStatus,data),httpStatus);
    }

    public ResponseEntity<RestAPIResponse> successResponse(Object content)
    {
        return buildResponse(APIStatus.OK,content,HttpStatus.OK);
    }
    public ResponseEntity<RestAPIResponse> badRequestResponse(List<Constant.ParamError> errors) {

        Map<String, String> errMap = null;

        if (errors != null) {

            errMap = new HashMap<>();
            for (Constant.ParamError error : errors) {
                errMap.put(error.getCode(), error.getDesc());
            }
        }

        return buildResponse(APIStatus.ERR_BAD_REQUEST, errMap, HttpStatus.BAD_REQUEST);
    }
}
