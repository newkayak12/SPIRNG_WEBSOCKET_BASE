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
    private HashOperations<String, String, String> userSessionHash;
    private HashOperations<String, String, List<String>> userRoomHash;
    private String USER_HASH_KEY = "user";
    private String ROOM_HASH_KEY = "room";

    @Autowired
    public StompRepository( RedisTemplate<String, Object> redisTemplate ){
        userSessionHash = redisTemplate.opsForHash();
        userRoomHash = redisTemplate.opsForHash();
    }

    public void attachUser( String sessionId, String userNo){
        userSessionHash.putIfAbsent(USER_HASH_KEY, userNo, sessionId);
        this.userLogger(userNo);
    }
    public void detachUser(String userNo) {
        userSessionHash.delete(USER_HASH_KEY, userNo);
        this.userLogger(userNo);
    }


    public void enterRoom( String roomNo, String userNo ){
        if( !userRoomHash.putIfAbsent(ROOM_HASH_KEY, roomNo, Arrays.asList(userNo)) ) {
            List<String> room = userRoomHash.get(ROOM_HASH_KEY, roomNo);
            room.add(userNo);
            userRoomHash.put(ROOM_HASH_KEY, roomNo, room);
        }
        this.roomLogger(roomNo);
    }
    public void leaveRoom( String roomNo, String userNo ) {
        if( userRoomHash.hasKey(ROOM_HASH_KEY, roomNo) ){
            List<String> users = userRoomHash.get(ROOM_HASH_KEY, roomNo);

            if( users.size() == 1)  userRoomHash.delete(ROOM_HASH_KEY, roomNo);
            else {
                users.remove(userNo);
                userRoomHash.put(ROOM_HASH_KEY, roomNo, users);
            }
        }

        this.roomLogger(roomNo);
    }
    public List<String> getRoom(String roomNo){return userRoomHash.get(ROOM_HASH_KEY, roomNo);}


    private void userLogger(String sessionId) {
        log.warn("USER :: {}", userSessionHash.get(USER_HASH_KEY, sessionId));
    }
    private void roomLogger(String roomNo) {
        log.warn("USER :: {}", userSessionHash.get(ROOM_HASH_KEY, roomNo));
    }
}
