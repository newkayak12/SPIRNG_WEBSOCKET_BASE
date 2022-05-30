package com.base.websocket.repository.chatUserListRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.base.websocket.repository.chatUserListRepository.QChatUserList.chatUserList;

public class ChatUserListRepositoryImpl extends QuerydslRepositorySupport implements ChatUserListRepositoryCustom {
    public ChatUserListRepositoryImpl() {
        super(ChatUserList.class);
    }

    @Override
    public Page<ChatUserList> getChatRoomListByUserNo(Long userNo, Pageable pageable) {
        QueryResults<ChatUserList> results  = from(chatUserList)
                .where(chatUserList.user.userNo.eq(userNo))
                .fetchResults();
        return new PageImpl<ChatUserList>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ChatUserList getChatRoomByUserNoAndUuid(Long userNo, String uuid) {
        return from(chatUserList)
                .where(chatUserList.chatRoom.uuid.eq(uuid)
                        .and(chatUserList.user.userNo.eq(userNo)))
                .fetchOne();

    }
}
