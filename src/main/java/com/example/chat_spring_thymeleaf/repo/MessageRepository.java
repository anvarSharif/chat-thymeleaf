package com.example.chat_spring_thymeleaf.repo;

import com.example.chat_spring_thymeleaf.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = """
            select * from messages where from_user_id=:from
            and to_user_id=:to or from_user_id=:to and to_user_id=:from
            """, nativeQuery = true)
    List<Message> getChatHistory(Integer from, Integer to);
    @Modifying
    @Transactional
    @Query(value = """
            UPDATE messages
                SET has_read = true
                WHERE id IN :chatHistory
                  AND has_read = false
                        """, nativeQuery = true)
    void readedMessage(List<Integer> chatHistory);
}