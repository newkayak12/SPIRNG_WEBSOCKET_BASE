package com.base.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created on 2023-05-04
 * Project user-service
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WakeUp {

    @EventListener(value = {ApplicationReadyEvent.class})
    public void message(){
        log.warn(" {} is ready", ("\n" +
                "  ____                 __        __   _    ____                 _    _   \n" +
                " | __ )  __ _ ___  ___ \\ \\      / /__| |__/ ___|  ___   ___ ___| | _| |_ \n" +
                " |  _ \\ / _` / __|/ _ \\ \\ \\ /\\ / / _ \\ '_ \\___ \\ / _ \\ / __/ _ \\ |/ / __|\n" +
                " | |_) | (_| \\__ \\  __/  \\ V  V /  __/ |_) |__) | (_) | (_|  __/   <| |_ \n" +
                " |____/ \\__,_|___/\\___|___\\_/\\_/ \\___|_.__/____/ \\___/ \\___\\___|_|\\_\\\\__|\n" +
                "                     |_____|                                             "));
    }
}
