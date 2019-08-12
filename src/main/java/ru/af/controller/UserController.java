package ru.af.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.af.entity.Message;
import ru.af.entity.User;
import ru.af.repo.MessageRepository;
import ru.af.repo.UserRepository;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(
            @RequestBody User newUser) {

        User user = userRepository.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> readUser(
            @PathVariable("id") int id) {

        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @RequestMapping(value = "{id}/messages", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getAllUserMessages(
            @PathVariable(value = "id") int userId) {

        List<Message> messages = messageRepository.findByUserId(userId);

        if (messages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @RequestBody() User user,
            @PathVariable("id") int id) {

        if (userRepository.findOne(id) != null) {
            user.setId(id);
            User updatedUser = userRepository.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable("id") int id) {

        userRepository.delete(id);
    }

}
