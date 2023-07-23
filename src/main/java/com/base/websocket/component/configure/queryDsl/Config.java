package com.base.websocket.component.configure.queryDsl;

import com.base.websocket.component.ConfigMsg;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created on 2023-05-18
 * Project user-service
 */
@Configuration(value = "querydslConfig")
//@RequiredArgsConstructor
public class Config {


    @PersistenceContext
    private EntityManager entityManager;

    public Config() {
        ConfigMsg.msg("QueryDsl");
    }

    @Bean
    public JPQLQueryFactory jPQLQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
