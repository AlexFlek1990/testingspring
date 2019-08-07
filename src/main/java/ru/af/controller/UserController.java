package ru.af.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.af.entity.Message;
import ru.af.repo.MessageRepository;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private MessageRepository messageRepository;

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllUseMessages(
            @RequestParam(value = "id") int userId) {

        List<Message> messages = messageRepository.findByUserId(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
