package com.base.websocket.common.responseContainer;

import com.server.base.common.authorizations.TokenManager;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EncryptResponse extends Response{
    public EncryptResponse(HttpServletResponse response,Object tokenDto) {
        super(200, "", dataSet(tokenDto, null));
        response.addHeader(HttpHeaders.AUTHORIZATION, TokenManager.encrypt(tokenDto));
    }
    public EncryptResponse( HttpServletResponse response, Object tokenDto,  Object otherData){
        super(200, "", dataSet(tokenDto,otherData));
        response.addHeader(HttpHeaders.AUTHORIZATION, TokenManager.encrypt(tokenDto));
    }

    public static Map<String,Object> dataSet(Object tokenDto, Object other){
        Map<String,Object> map = new HashMap<>();
        if(!Objects.isNull(tokenDto)){
            map.put("userData", tokenDto);
        }
        if(!Objects.isNull(other)){
            map.put("data", other);
        }
        return map;
    }
}
