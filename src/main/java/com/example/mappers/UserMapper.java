package com.example.mappers;

import com.example.model.auth.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String GET_ALL__ACTIVE_USERS = "SELECT * FROM user";
    String GET_BY_ID = "SELECT * FROM user where user_id = #{id}";
    String INSERT_USER = "INSERT INTO user (first_name, last_name, username, photo_url) " +
            "VALUES (#{first_name}, #{last_name}, #{username}, #{photo_url})";
    String UPDATE_USER = "UPDATE user SET first_name = #{first_name}, " +
            "last_name = #{last_name}, email = #{email}, photo_url = #{photo_url} WHERE user_id = #{id}";
    String DELETE_USER = "delete from user WHERE user_id = #{id}";
    String GET_BY_NAME = "SELECT * FROM user where first_name = #{first_name}";
    String GET_PHOTO_BY_USER_ID = "Select photo_url from user where user_id = #{id}";

    @Select(GET_BY_NAME)
    public User getByName(String name);

    @Select(GET_ALL__ACTIVE_USERS)
    public ArrayList<User> getAllUsers();

    @Select(GET_BY_ID)
    public User getByID(int id);

    @Insert(INSERT_USER)
    public int insertUser(User user);

    @Update(UPDATE_USER)
    public int updateUser(User user);

    @Delete(DELETE_USER)
    public int deleteUser(int id);

    @Select(GET_PHOTO_BY_USER_ID)
    String getPhotoByUserId(int id);
}