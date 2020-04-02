package com.example.mappers;

import com.example.model.internal.Chat;
import com.example.model.internal.ChatPost;
import com.example.model.internal.Message;
import com.example.model.responses.ChatDBResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ChatMapper {

    String GET_MESSAGES_BY_CHAT_ID = "select m.message, m.date_sent, m.chat_id, m.user_id as sender_id, c.chat_title " +
            "from messages m " +
            "join chats c " +
            "on m.chat_id = c.id " +
            "where m.chat_id = #{chat_id} " +
            "order by m.date_sent asc;";

    String GET_CHAT_LIST = "select distinct(c.chat_title) as chat_name, c.id as chat_id, max(m.message) as last_message, " +
            "m.user_id as sender_id, m.date_sent " +
            "from chats c " +
            "join messages m " +
            "on m.chat_id = c.id " +
            "join users_chats uc " +
            "on uc.chat_id = c.id " +
            "where uc.user_id = #{user_id} " +
            "and m.user_id != #{user_id} " +
            "group by chat_id, sender_id " +
            "order by m.date_sent asc";

    String GET_CHAT_ID_FOR_USERS = "select uc.chat_id " +
            "from users_chats uc " +
            "where uc.user_id = #{param1} " +
            "or user_id = #{param2} " +
            "group by uc.chat_id " +
            "order by count(uc.chat_id) desc " +
            "limit 1";

    String INSERT_MESSAGE = "INSERT INTO messages " +
            "(message, " +
            "chat_id, " +
            "user_id) " +
            "VALUES " +
            "(#{message}, #{chat_id}, #{sender_id})";

    String GET_MESSAGE_BY_ID = "select * from messages where id = #{id}";

    String CREATE_NEW_CHAT = "insert into chats (chat_title) values (#{chat_title})";

    String ASSOCIATE_NEW_CHAT = "insert into users_chats (user_id, chat_id) values " +
            "(#{param1}, #{param2})";

    @Select(GET_MESSAGES_BY_CHAT_ID)
    public ArrayList<Message> getMessagesByChatId(int chat_id);

    @Select(GET_CHAT_LIST)
    public ArrayList<ChatDBResponse> getListByUserId(int user_id);

    @Select(GET_CHAT_ID_FOR_USERS)
    int getChatIdForUserIds(int id, int other_id);

    @Insert(INSERT_MESSAGE)
    @Options(useGeneratedKeys = true)
    int insertMessage(ChatPost msg);

    @Select(GET_MESSAGE_BY_ID)
    Message getMessageById(int i);

    @Insert(CREATE_NEW_CHAT)
    @Options(useGeneratedKeys = true)
    int createNewChat(Chat chat);

    @Insert(ASSOCIATE_NEW_CHAT)
    int associateChat(int user_id, int chat_id);
}
