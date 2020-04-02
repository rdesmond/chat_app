package com.example.controller;

import com.example.model.auth.User;
import com.example.model.internal.Chat;
import com.example.model.internal.ChatPost;
import com.example.model.internal.Message;
import com.example.model.responses.ChatDBResponse;
import com.example.model.responses.ResponseObject;
import com.example.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserMessageService userService;


    //RequestMapping maps URLs to methods

    //Get
    @RequestMapping("/")
    public ResponseObject<ArrayList<User>> getUsers() {
        ResponseObject<ArrayList<User>> obj = new ResponseObject();
        obj.setData(userService.getAllUsers());
        return obj;
    }

    @RequestMapping("/{id}")
    public ResponseObject<User> getById(@PathVariable(value="id")int id) {
        ResponseObject<User> obj = new ResponseObject();
        obj.setData(userService.getById(id));
        return obj;
    }

    //Create
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseObject<User> addNew(@RequestBody User user) {
        ResponseObject<User> obj = new ResponseObject();
        obj.setData(userService.addNew(user));
        return obj;
    }

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public  ResponseObject<User> updateById(@RequestBody User user) {
        ResponseObject<User> obj = new ResponseObject();
        obj.setData(userService.updateById(user));
        return obj;
    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public ResponseObject<User> deleteById(@RequestParam(value="id")int id){
        ResponseObject<User> obj = new ResponseObject();
        obj.setData(userService.deleteById(id));
        return obj;
    }

    @RequestMapping("/{id}/chats")
    public ResponseObject<ArrayList<ChatDBResponse>>  getChatListByUserId(@PathVariable(value="id")int id){
        ResponseObject<ArrayList<ChatDBResponse>> obj = new ResponseObject();
        obj.setData(userService.getChatListByUserId(id));
        return obj;
    }

    @PostMapping("/{id}/chats/{other_id}")
    public ResponseObject<Chat>  createNewChat(
            @PathVariable(value="id")int id,
            @PathVariable(value="other_id")int other_id,
            @RequestBody Chat chat){
        ResponseObject<Chat> obj = new ResponseObject();
        obj.setData(userService.createChat(chat, id, other_id));
        return obj;
    }

    @RequestMapping("/{id}/chats/{other_id}")
    public ResponseObject<ArrayList<Message>> getChatListByUserId(
            @PathVariable(value="id")int id,
            @PathVariable(value="other_id")int other_id){
        ResponseObject<ArrayList<Message>> obj = new ResponseObject();
        obj.setData(userService.getChatDetail(id, other_id));
        return obj;
    }

    /*
    POST:
    /user/9/chat
    {
    recipientId: 14,
    message: "this is the message",
    timestamp: 02/03/2020 4:45:34
}
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/chat")
    public ResponseObject<Message> addNewMessage(
            @PathVariable("id") int userId,
            @RequestBody ChatPost chat) {
        ResponseObject<Message> obj = new ResponseObject();
        obj.setData(userService.addNewMessage(chat));
        return obj;
    }
}
