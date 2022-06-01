package com.base.websocket.repository.messageRepository;

import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.base.websocket.repository.messageRepository.QMessage.message1;

public class MessageRepositoryImpl extends QuerydslRepositorySupport implements MessageRepositoryCustom{
    public MessageRepositoryImpl() {
        super(Message.class);
    }

    @Override
    public Page<Message> getMessages(String uuid, Pageable pageable) {
        QueryResults<Message> select =  from(message1)
                .where(message1.chatRoom.uuid.eq(uuid))
                .orderBy(message1.messageNo.desc())
                .fetchResults();

        return new PageImpl<>(select.getResults(), pageable, select.getTotal());
    }
}
