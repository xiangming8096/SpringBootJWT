package com.lt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lt.web.domain.User;
import com.lt.web.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Transactional
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public List<User> findAllOrderByIdDesc() {
        return userMapper.findAllOrderByIdDesc();
    }
}

