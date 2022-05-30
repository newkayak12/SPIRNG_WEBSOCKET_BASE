package com.base.websocket.common.configurations;

import com.base.websocket.common.constants.Constants;
import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.common.responseContainer.Response;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.fileUpload.FileUpload;
import com.server.base.common.responseContainer.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServiceException.class})
    public Response serviceExceptionHandler(ServiceException e){
        Boolean isDevMode = Constants.IS_DEV_MODE;
        if(!isDevMode){
            return new Response(-500, "일시적인 오류입니다.", null);
        }
        log.error("{}\n{}", e.getMessage(), e.getStackTrace());
        return new Response(e.getCode(), e.getMsg(), null);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Response exceptionHandler(Exception e){
        Boolean isDevMode = Constants.IS_DEV_MODE;
        if(!isDevMode){
            return new Response(-500, "일시적인 오류입니다.", null);
        }
        log.error("{}\n{}", e.getMessage(), e.getStackTrace());
        return new Response(-500, e.getMessage(), null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response internalServerErrorHandler(Exception e){
        Boolean isDevMode = Constants.IS_DEV_MODE;
        if(!isDevMode){
            return new Response(-500, "일시적인 오류입니다.", null);
        }
        log.error("{}\n{}", e.getMessage(), e.getStackTrace());
        return new Response(-500, e.getMessage(), null);
    }
}
