package com.example.springbootSample.controller;

import com.example.springbootSample.model.SysConfig;
import com.example.springbootSample.model.User;
import com.example.springbootSample.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@Log
public class TestController {

    @Value(value = "${app.name}") //读取properties配置文件中的属性值
    private String appNme;

    @Autowired
    SysConfig sysConfig;

    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    @ResponseBody
    String testMethod() {
        log.info("---testMethod");
        String str = "\nHello World!";
        //输出properties属性值
        str += "\n环境:"+appNme;
        //输出properties属性值（类型安全）
        String str_sysConfig = sysConfig.toString();
        str += "\n"+str_sysConfig;
        //测试mapper中的select操作
        User user = userService.getUser(1);
        str += "\n"+user.toString();
        //测试mapper中的update操作
        user.setBirthday(new Date());
        int count_update = userService.updateUser(user);
        str += "\n更新记录数:"+count_update;
        //测试事务，产生错误并回滚数据
        try{
            userService.testTransaction();
        }catch(Exception e){
            str += "\n出现错误（"+e.getMessage()+"），已回滚";
        }
        //测试mapper中的select操作
        List<User> userList = userService.getUserList();
        String str_users = userList.toString();
        //输出信息
        log.info(str);
        return str;
    }


}
