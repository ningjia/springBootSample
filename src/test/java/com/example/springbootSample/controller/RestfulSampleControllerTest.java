package com.example.springbootSample.controller;

import com.example.springbootSample.SpringbootSampleApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringbootSampleApplication.class) //引入springboot环境上下文
@WebAppConfiguration
public class RestfulSampleControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * RestfulSampleController的单元测试方法
     * @throws Exception
     */
    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;

        // 1、del删除id为999的user
        request = delete("/users/999");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 2、post提交一个user
        request = post("/users/")
                .param("id", "999")
                .param("username", "测试的UserName")
                .param("sex", "1")
                .param("address", "测试的Address");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3、get获取刚刚插入的user数据
        request = get("/users/999");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":999,\"username\":\"测试的UserName\",\"birthday\":null,\"sex\":\"1\",\"address\":\"测试的Address\"}")));

        // 4、put修改id为999的user
        request = put("/users/999")
                .param("username", "修改后的UserName")
                .param("address", "修改后的Address");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get获取刚刚修改的user数据
        request = get("/users/999");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":999,\"username\":\"修改后的UserName\",\"birthday\":null,\"sex\":\"1\",\"address\":\"修改后的Address\"}")));

        // 6、del删除id为999的user
        request = delete("/users/999");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/users/999");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

}
