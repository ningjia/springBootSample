package com.example.springbootSample.service;

import com.example.springbootSample.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by jn on 2017/7/27.
 */
public interface UserService {

    public User getUser(Integer id);

    public List<User> getUserList(int pageNo, int pageSize);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Integer id);

    @Transactional
    public void testTransaction();

    public List<User> getUserListByPage(int pageNo, int pageSize);

}
