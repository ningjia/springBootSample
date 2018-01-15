package com.example.springbootSample.controller;

import com.example.springbootSample.model.User;
import com.example.springbootSample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class RestfulSampleController {

    @Autowired
    private UserService userService;

    /**
     * 处理"/users/"的GET请求，用来获取用户列表
     *
     * 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递;
     *
     * @return
     */
    @GetMapping(value="/") //等同于RequestMapping(method=RequestMethod.GET)
    public List<User> getUserList() {
        List<User> users = userService.getUserList();
        return users;
    }

    /**
     * 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
     *
     * url中的id可通过@PathVariable绑定到函数的参数中;
     *
     * @param id User记录的主键id
     * @return
     */
    @GetMapping(value="/{id}") //等同于RequestMapping(method=RequestMethod.GET)
    public User getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return user;
    }

    /**
     * 处理"/users/"的POST请求，用来创建User
     *
     * 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数;
     *
     * @param user 需要创建的User记录
     * @return
     */
    @PostMapping(value="/") //等同于RequestMapping(method=RequestMethod.POST);
    public String postUser(@ModelAttribute User user) {
        userService.insertUser(user);
        return "success";
    }

    /**
     * 处理"/users/{id}"的PUT请求，用来更新User信息
     *
     * @param id User记录的主键id
     * @param user 需要更新的User记录
     * @return
     */
    @PutMapping(value="/{id}") //等同于RequestMapping(method=RequestMethod.PUT)
    public String putUser(@PathVariable Integer id, @ModelAttribute User user) {
        User oldUser = userService.getUser(id);
        if(user.getUsername()!=null)
            oldUser.setUsername(user.getUsername());
        if(user.getSex()!=null)
            oldUser.setSex(user.getSex());
        if(user.getAddress()!=null)
            oldUser.setAddress(user.getAddress());
        if(user.getBirthday()!=null)
            oldUser.setBirthday(user.getBirthday());
        userService.updateUser(oldUser);
        return "success";
    }

    /**
     * 处理"/users/{id}"的DELETE请求，用来删除User
     *
     * @param id User记录的主键id
     * @return
     */
    @DeleteMapping(value="/{id}") //等同于RequestMapping(method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "success";
    }

}
