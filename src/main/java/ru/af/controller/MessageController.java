package ru.af.controller;

import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
@RequestMapping("/message")
public class MessageController {

    @Value("${value:none}")
    public String value;

    @Autowired
    private MessageRepository messageRepository;


//    @RequestMapping(value = "post", method = RequestMethod.POST)
//    public ResponseEntity<Message> createMessage(
//            @RequestParam(value = "id", required = true) int id) {
//
//        Message message = messageRepository.updateById(id);
//        if (message == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<Message> readMessage(
            @RequestParam(value = "id", required = true) int id) {

        Message message = messageRepository.findOne(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
//    @RequestMapping(value = "put", method = RequestMethod.PUT)
//    public ResponseEntity<Message> updateMessage(
//            @RequestBody Message message) {
//
//        Message message = messageRepository.sa;
//        if (message == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(message, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public void deleteMessage(
            @RequestParam(value = "id", required = true) int id) {

        messageRepository.delete(id);
    }
}
