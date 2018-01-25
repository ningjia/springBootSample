## RESTful的API服务
### RESTful API具体设计如下
- 查询用户列表
    - 请求类型：GET
    - URL：/users/
    - 需要分页处理时的URL：/users/?pageNo=2&pageSize=3
- 创建一个用户
    - 请求类型：POST
    - URL：/users/
- 根据id查询一个用户
    - 请求类型：GET
    - URL：/users/id
- 根据id更新一个用户
    - 请求类型：PUT
    - URL：/users/id
- 查询用户列表
    - 请求类型：DELETE
    - URL：/users/id
### 单元测试
- 单元测试方法：RestfulSampleControllerTest.testUserController()
- 通过验证断言(mockMvc.andExpect)来判断执行请求后的结果是否满足预期；
- 通过引入Spring上下文，来避免所测试的对象没有正确完成依赖注入；

### Refer
- [Spring Boot构建RESTful API与单元测试](http://blog.didispace.com/springbootrestfulapi/)
- [Spring Boot使用单元测试](http://tengj.top/2017/12/28/springboot12/) (MockMvc详细说明、断言assertThat语法说明、单元测试回滚)