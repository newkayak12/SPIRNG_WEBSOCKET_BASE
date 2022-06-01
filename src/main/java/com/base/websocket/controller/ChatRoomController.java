package com.base.websocket.controller;

import com.base.websocket.common.baseDto.PagingDto;
import com.base.websocket.common.exception.ServiceException;
import com.base.websocket.common.responseContainer.Response;
import com.base.websocket.repository.dto.ChatRoomDto;
import com.base.websocket.repository.dto.ChatRoomInsertDto;
import com.base.websocket.repository.dto.UserDto;
import com.base.websocket.service.ChatRoomService;
import com.base.websocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    /**
     * 채팅방 리스트
     * @param userNo
     * @param paging
     * @return
     */
    @GetMapping("/chatroom/{userNo}")
    public Response chatRoomList(@PathVariable Long userNo, @ModelAttribute PagingDto paging){
        return new Response(200, "", chatRoomService.getChatRoomList(userNo, paging));
    }

    /**
     * 채팅방 리스트 중 선택
     * @param userNo
     * @param uuid
     * @return
     */
    @GetMapping("/chatroom/{userNo}/{uuid}")
    public Response chatRoom(@PathVariable Long userNo, @Payload String uuid){
        return new Response(200, "", chatRoomService.getChatRoom(userNo, uuid));
    }

    /**
     * 채팅방 등록/수정
     * @param chatRoomInsertDto
     * @return
     */
    @PostMapping("/chatroom/")
    public Response chatRoom(@RequestBody ChatRoomInsertDto chatRoomInsertDto){
        return new Response(200, "", chatRoomService.saveChatRoom(chatRoomInsertDto));
    }

    /**
     * 채팅방 삭제
     * @param roomNo
     * @return
     * @throws ServiceException
     */
    @DeleteMapping("/chatroom/{roomNo}")
    public Response chatRoom(@PathVariable Long roomNo) throws ServiceException {
        chatRoomService.deleteChatRoom(roomNo);
        return new Response(200, "", null);
    }

    @GetMapping("/chatroom/{uuid}/message")
    public Response chatRoomMessages(@PathVariable String uuid,  @ModelAttribute PagingDto paging){
        messageService.chatRoomMessages(uuid, paging);
    }
}
