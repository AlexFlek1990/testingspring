package ru.af.repo;

import org.springframework.data.repository.CrudRepository;
import ru.af.entity.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Integer> {

    List<Message> findByUserId(int userId);

}
