package com.lt.web.controller;

import com.lt.web.domain.User;
import com.lt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //hello
    @GetMapping("hello")
    public String hello(){
        return "hello spring boot";
    }

    //简单 内置
    @GetMapping("user/{id}")
    public User userId(@PathVariable("id") Integer id){
        return userService.selectByPrimaryKey(id);
    }

    //中等 非内置
    @GetMapping("user/list/desc")
    public List<User> userListDesc(){
        return userService.findAllOrderByIdDesc();
    }

}


