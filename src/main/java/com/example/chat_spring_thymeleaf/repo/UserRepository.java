package com.example.chat_spring_thymeleaf.repo;

import com.example.chat_spring_thymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneAndPassword(String phone,String password);
@Query(value = """
select count(*)
from messages
where to_user_id=:currentUserId
and from_user_id=:fromUserId
and has_read=false
""",nativeQuery = true)
    int getNotificationCount(Integer currentUserId,Integer fromUserId);
}