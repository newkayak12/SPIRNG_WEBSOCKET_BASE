package com.base.websocket.common.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
public class DefaultDateEntity {
    @Column(name = "reg_date", nullable = false, insertable = true, updatable = false)
    private LocalDateTime regDate;
    @Column(name = "modified_date", nullable = true, insertable = true, updatable = true)
    private LocalDateTime modifiedDate;

    @PostPersist
    public void write(){
        this.regDate = LocalDateTime.now();
    }
    @PostUpdate void modify(){
        this.modifiedDate = LocalDateTime.now();
    }
}
