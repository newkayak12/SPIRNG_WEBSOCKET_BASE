package com.base.websocket.common.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter
public class AuthEntity {
    @Column(name = "refresh_token", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    private String refreshToken;
}
