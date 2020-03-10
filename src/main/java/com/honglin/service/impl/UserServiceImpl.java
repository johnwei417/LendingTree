package com.honglin.service.impl;

import com.honglin.dao.UserDao;
import com.honglin.model.entity.User;
import com.honglin.model.pojo.UserDto;
import com.honglin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @Override
    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userDao.findByEmail(email);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public User findUserByResetToken(String resetToken) {
        Optional<User> optionalUser = userDao.findByResetToken(resetToken);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }


    @Override
    public UserDto update(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "createTime");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public UserDto updatePassword(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            if (bcryptEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
                user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
                BeanUtils.copyProperties(user, userDto, "answer", "createTime", "email", "firstname",
                        "lastname", "phone", "question", "role", "username", "resetToken");
                userDao.save(user);
            }
        }
        return userDto;
    }


    @Override
    public UserDto updateEmail(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "answer", "password", "createTime", "firstname",
                    "lastname", "phone", "question", "role", "username", "resetToken");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public UserDto updatePhone(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "answer", "password", "createTime", "firstname",
                    "lastname", "email", "question", "role", "username", "resetToken");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public UserDto updateAnswerAndQuestion(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "password", "createTime", "firstname",
                    "lastname", "phone", "email", "role", "username", "resetToken");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public UserDto updateResetToken(UserDto userDto) {
        User user = findUserByEmail(userDto.getEmail());
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        System.out.printf(user.getResetToken());
        if (user != null) {
            BeanUtils.copyProperties(user, userDto, "answer", "password", "createTime", "firstname",
                    "lastname", "email", "question", "role", "username", "phone");
            userDao.save(user);

        }
        return userDto;
    }


    @Override
    public UserDto resetPassword(UserDto userDto) {
        User user = findUserByResetToken(userDto.getResetToken());
        if (user != null) {
            user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
            BeanUtils.copyProperties(user, userDto, "answer", "createTime", "firstname",
                    "lastname", "email", "question", "role", "username", "phone");
            user.setResetToken(null);
            userDao.save(user);

        }
        return userDto;
    }

    @Override
    public UserDto updateFirstnameAndLastname(UserDto userDto) {
        User user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "answer", "password", "createTime", "answer",
                    "question", "phone", "email", "role", "username", "resetToken");
            userDao.save(user);
        }
        return userDto;
    }


    @Override
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setQuestion(user.getQuestion());
        newUser.setAnswer(user.getAnswer());
        newUser.setAuthorities(user.getAuthorities());
        newUser.setEmployees(user.getEmployees());
        newUser.setCustomer(user.getCustomer());
        newUser.setResetToken(user.getResetToken());

        return userDao.save(newUser);
    }
}
