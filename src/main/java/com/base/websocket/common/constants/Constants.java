package com.base.websocket.common.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    public static String PROJECT_NAME;

    @Value("${CONSTANTS.PROJECTNAME}")
    public static void setProjectName(String _project_name) {
        PROJECT_NAME = _project_name;
    }
}
