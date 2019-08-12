package ru.af.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.af.entity.Message;
import ru.af.repo.MessageRepository;

import java.util.List;


@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> createMessage(
            @RequestBody Message newMessage) {

        Message message = messageRepository.save(newMessage);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Message>> readAllMessage() {

        List<Message> messages = messageRepository.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> readMessage(
            @PathVariable("id") int id) {

        Message message = messageRepository.findOne(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> updateMessage(
            @RequestBody Message message,
            @PathVariable("id") int id) {

        if (messageRepository.findOne(id) != null) {
            message.setId(id);
            Message updatedMessage = messageRepository.save(message);
            return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMessage(
            @PathVariable("id") int id) {

        messageRepository.delete(id);
    }

}