package com.base.websocket.repository.userRepository;

import com.base.websocket.common.baseEntity.AuthEntity;
import com.base.websocket.common.baseEntity.UserDateEntity;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;


    @Entity
    @Table(name = "users")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @EqualsAndHashCode
    @ToString
    public class User extends UserDateEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(updatable = false, name = "user_no")
        private Long userNo;
        @Column(name = "user_id", length = 50)
        private String userId;
        @Column(name = "password", length = 500)
        private String password;

        @Embedded
        private AuthEntity authEntity;

        public void setRefreshToken(String value){
            if(ObjectUtils.isEmpty(this.authEntity.getRefreshToken())){
                authEntity.setRefreshToken(value);
            }

        }
    }
