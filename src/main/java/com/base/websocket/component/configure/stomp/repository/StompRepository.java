package com.base.websocket.component.configure.stomp.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class StompRepository {
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, String> userSessionHash;
    private HashOperations<String, String, List<String>> userRoomHash;

    @Autowired
    public StompRepository( RedisTemplate<String, Object> redisTemplate ){
        this.redisTemplate = redisTemplate;
        userSessionHash = this.redisTemplate.opsForHash();
        userRoomHash = this.redisTemplate.opsForHash();
    }

    public void attachUser( String sessionId, String userNo){
        userSessionHash.putIfAbsent("user", sessionId, userNo);
        this.userLogger(sessionId);
    }
    public void detachUser(String sessionId) {
        userSessionHash.delete("user", sessionId);
        this.userLogger(sessionId);
    }

    public void enterRoom( String roomNo, String userNo ){
        if( !userRoomHash.putIfAbsent("room", roomNo, Arrays.asList(userNo)) ) {
            List<String> room = userRoomHash.get("room", roomNo);
            room.add(userNo);
            userRoomHash.put("room", roomNo, room);
        }
        this.roomLogger(roomNo);
    }
    public void leaveRoom( String roomNo, String userNo ) {
        if( userRoomHash.hasKey("room", roomNo) ){
            List<String> users = userRoomHash.get("room", roomNo);

            if( users.size() == 1)  userRoomHash.delete("room", roomNo);
            else {
                users.remove(userNo);
                userRoomHash.put("room", roomNo, users);
            }
        }

        this.roomLogger(roomNo);
    }

    private void userLogger(String sessionId) {
        log.warn("USER :: {}", userSessionHash.get("user", sessionId));
    }
    private void roomLogger(String roomNo) {
        log.warn("USER :: {}", userSessionHash.get("room", roomNo));
    }
}
