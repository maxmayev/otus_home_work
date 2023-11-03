package com.maxmayev.autograph.repository;

import com.maxmayev.autograph.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

