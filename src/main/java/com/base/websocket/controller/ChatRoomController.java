package com.base.websocket.controller;

import com.base.websocket.common.baseDto.PagingDto;
import com.base.websocket.common.responseContainer.Response;
import com.base.websocket.repository.dto.ChatRoomDto;
import com.base.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/{userNo}")
    public Response chatRoomList(@PathVariable Long userNo, @ModelAttribute PagingDto paging){
        return new Response(200, "", chatRoomService.getChatRoomList(userNo, paging));
    }
}
