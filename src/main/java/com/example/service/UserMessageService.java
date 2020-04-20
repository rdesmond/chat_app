package com.example.service;

import com.example.mappers.ChatMapper;
import com.example.mappers.UserMapper;
import com.example.model.auth.User;
import com.example.model.internal.Chat;
import com.example.model.internal.ChatPost;
import com.example.model.internal.Message;
import com.example.model.responses.ChatDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserMessageService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ChatMapper chatMapper;

    //get all users using mybatis
    public ArrayList<User> getAllUsers (){
        return userMapper.getAllUsers();
    }

    //get user by id
    public User getById(int id){
        return userMapper.getByID(id);
    }

    //add new user
    public User addNew(User user) {
        userMapper.insertUser(user);
        return userMapper.getByName(user.getFirst_name());
    }

    //update user by its id
    public User updateById(User user) {
        userMapper.updateUser(user);
        return userMapper.getByName(user.getFirst_name());
    }

    //delete
    public User deleteById(int id) {
        userMapper.deleteUser(id);
        return userMapper.getByID(id);
    }

    public ArrayList<ChatDBResponse> getChatListByUserId(int id) {

        ArrayList<ChatDBResponse> chats = chatMapper.getListByUserId(id);

        for(ChatDBResponse c : chats){
            try {
                c.setPhoto_url(userMapper.getPhotoByUserId(c.getSender_id()));
            } catch(Exception e){
                System.out.println("photo not found");
            }
            c.setLast_message(chatMapper.getLastMessage(c.getChat_id()));
        }

        return  chats;
    }


    public ArrayList<Message> getChatDetail(int id, int other_id) {
        int chat_id = chatMapper.getChatIdForUserIds(id, other_id);
        return chatMapper.getMessagesByChatId(chat_id);
    }

    public Message addNewMessage(ChatPost msg) {
        int i = chatMapper.insertMessage(msg);
        return chatMapper.getMessageById(msg.getId());
    }

    public Chat createChat(Chat chat, int id, int other_id) {
        int i = chatMapper.createNewChat(chat);
        int x = chatMapper.associateChat(id, chat.getId());
        int y = chatMapper.associateChat(other_id, chat.getId());
        return chat;
    }
}
