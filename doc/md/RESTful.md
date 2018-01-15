## RESTful的API服务
### RESTful API具体设计如下
- 查询用户列表
    - 请求类型：GET
    - URL：/users
- 创建一个用户
    - 请求类型：POST
    - URL：/users
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
RestfulSampleController.test()
### Refer
[Spring Boot构建RESTful API与单元测试](http://blog.didispace.com/springbootrestfulapi/)
