package com.base.websocket.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String PROJECT_NAME;
    public static String SUBSCRIBE;
    public static String PUBLISH;
    public static String ENDPOINT;
    public static String[] ALLOW_ORIGIN;
    public static Boolean IS_DEV_MODE;


    @Value("${CONSTANTS.PROJECTNAME}")
    public void setProjectName(String _project_name) {
        PROJECT_NAME = _project_name;
    }

    @Value("${CONSTANTS.SUBSCRIBE}")
    public void setSubscribe(String _SUBSCRIBE) {
        Constants.SUBSCRIBE = _SUBSCRIBE;
    }

    @Value("${CONSTANTS.PUBLISH}")
    public void setPublishString(String _PUBLISH) {
        Constants.PUBLISH = _PUBLISH;
    }

    @Value("${CONSTANTS.ENDPOINT}")
    public void setEndPoint(String _END_POINT) {
        Constants.ENDPOINT = _END_POINT;
    }

    @Value("${CONSTANTS.ALLOWORIGIN}")
    public void setAllowOrigin(String _ALLOW_ORIGIN) {
        Constants.ALLOW_ORIGIN = _ALLOW_ORIGIN.split(",");
    }

    @Value("${CONSTANTS.ISDEVMODE}")
    public void setIsDevMode(Boolean _is_dev_mode){ IS_DEV_MODE = _is_dev_mode; }
}
