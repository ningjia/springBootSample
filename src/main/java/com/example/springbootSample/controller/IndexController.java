package com.example.springbootSample.controller;

import com.example.springbootSample.model.SysConfig;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
class IndexController {

    @Value(value = "${app.name}") //读取properties配置文件中的属性值
    private String appNme;

    @Autowired
    SysConfig sysConfig;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("appNme", appNme);
        model.addAttribute("sysConfig", sysConfig);
        return "index";
    }

}
