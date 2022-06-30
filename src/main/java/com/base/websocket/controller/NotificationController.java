package com.base.websocket.controller;

import com.base.websocket.common.responseContainer.Response;
import com.base.websocket.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sse")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;


    @GetMapping(value = "/notification/{userNo}", produces = "text/event-stream")
    public Response subscribeEvent( @RequestHeader(value = "Last-Event-Id", required = false) String lastEventId ,
                                    @PathVariable Long userNo){
        return new Response(1, "", notificationService.subscribeEvent(userNo, lastEventId));
    }
}
