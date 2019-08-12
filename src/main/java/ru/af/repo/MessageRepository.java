package ru.af.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.af.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findByUserId(int userId);

}
