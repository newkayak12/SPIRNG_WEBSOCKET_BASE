package com.base.websocket.repository.UserRepository;

import com.base.websocket.repository.UserRepository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}