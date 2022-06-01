package com.base.websocket.repository.messageRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepositoryCustom {
    Page<Message> getMessages(String uuid, Pageable pageable);
}
