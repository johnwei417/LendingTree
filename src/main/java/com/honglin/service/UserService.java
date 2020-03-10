package com.honglin.service;

import com.honglin.model.entity.User;
import com.honglin.model.pojo.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    void delete(int id);

    User findOne(String username);

    User findById(int id);

    User findByUsername(String username);

    User findUserByEmail(String email);

    User findUserByResetToken(String resetToken);


    //update user information
    UserDto update(UserDto userDto);

    UserDto updatePassword(UserDto userDto);

    UserDto updateEmail(UserDto userDto);

    UserDto updatePhone(UserDto userDto);

    UserDto updateAnswerAndQuestion(UserDto userDto);

    UserDto updateFirstnameAndLastname(UserDto userDto);

    UserDto updateResetToken(UserDto userDto);

    UserDto resetPassword(UserDto userDto);

}
