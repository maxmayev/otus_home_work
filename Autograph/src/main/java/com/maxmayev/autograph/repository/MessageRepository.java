package com.maxmayev.autograph.repository;

import com.maxmayev.autograph.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDestination(String destination);
}
