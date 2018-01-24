## Swagger2
- 与SpringBoot整合后，可以生成RESTful API文档。
- API文档的内容，整合入实现代码中，让维护文档和修改代码整合为一体。可以在修改代码逻辑的同时，方便的修改文档说明。
- 提供了[页面测试功能](http://localhost:8080/springBootSample/swagger-ui.html)来调试每个RESTful API。
### 依赖
```groovy
compile('io.springfox:springfox-swagger2:2.2.2')
compile('io.springfox:springfox-swagger-ui:2.2.2') //用于提供测试RESTful API的界面
```

### 关于集成swagger-ui的说明
- Swagger2所生成的API内容，是JSON格式的，可以访问如下地址来查看：http://localhost:8080/springBootSample/v2/api-docs。
- 引入swagger-ui依赖后，会自动在界面中展示上面的JSON所对应的API文档。
- 也可以不集成swagger-ui。下载官方的[工程文件](https://github.com/swagger-api/swagger-ui),把里面的dist目录下的所有文件考入springMVC的静态资源下，并修改拷贝的index.html文件（修改后，访问该index.html即可查看API文档）。

### 其他注意事项
- 需要通过URL传参的方法中，注解的参数配置需要增加： paramType="path"。否则所有的参数类型都会是body，获取不到请求参数。 

###Refer
- [Spring Boot中使用Swagger2构建强大的RESTful API文档](http://blog.didispace.com/springbootswagger2/)
- [swagger常用注解说明](https://www.jianshu.com/p/12f4394462d5)
- [Swagger2相关注解的官方说明wiki ](https://github.com/swagger-api/swagger-core/wiki/Annotations)
- [配置不同环境下启用swagger，在生产环境关闭swagger](http://www.cnblogs.com/woshimrf/p/disable-swagger.html)