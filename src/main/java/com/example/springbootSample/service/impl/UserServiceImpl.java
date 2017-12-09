package com.example.springbootSample.service.impl;

import com.example.springbootSample.mapper.UserMapper;
import com.example.springbootSample.model.User;
import com.example.springbootSample.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by jn on 2017/7/27.
 */
@Service
@Log
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> list = userMapper.selectAll();
        return list;
    }

    @Override
    public int updateUser(User user) {
        int count = userMapper.updateByPrimaryKey(user);
        return count;
    }

    @Override
    public void testTransaction() {
        try {
            //插入数据
            User user = new User();
            user.setId(99);
            user.setUsername("Test");
            user.setSex("男");
            user.setBirthday(new Date());
            user.setAddress("中文地址");
            userMapper.insert(user);
            //为测试事务的正确性，此处代码特意产生错误，以便检查数据是否回滚
            int n = 100/0;
        }catch (Exception e){
            e.printStackTrace();
            throw e;//为确保事务能够正确捕获异常，使用了catch捕获异常后，必须再将异常抛出
        }
    }

    @Override
    public List<User> getUserListBySex() {
        PageHelper.startPage(2,3); // 设置分页，参数1=页数，参数2=每页显示条数
        List<User> list = userMapper.selectBySex("1");
        return list;
    }


}
