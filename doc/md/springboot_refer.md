## SpringBoot使用Gradle构建war包
### 步骤：
1. 修改gradle.build文件
    - 添加如下配置：
        ```
        apply plugin: 'war' 
        ```

    - 修改依赖，将tomcat的依赖范围修改为providedCompile。确保内嵌的servlet容器不会干扰将要部署WAR文件的servlet容器。
        ```
        dependencies {  
            compile('org.springframework.boot:spring-boot-starter-web')  
            providedCompile("org.springframework.boot:spring-boot-starter-tomcat")  
            testCompile('org.springframework.boot:spring-boot-starter-test')  
        } 
        ```
2. 主类继承SpringBootServletInitializer，重写configure方法，改变启动方式。
    ```
    @SpringBootApplication  
    public class GradletestApplication extends SpringBootServletInitializer {  
      
        public static void main(String[] args) {  
            SpringApplication.run(GradletestApplication.class, args);  
        }  
      
        @Override  
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
            return builder.sources(GradletestApplication.class);  
        }  
    }  
    ```
3. 上述修改完成后，执行gradle war命令（或gradle build ），就可以在/build/ilbs目录下生成war包了

### providedCompile和compile的区别
- providedCompile是在调试使用时会加载对应的包，但是在打包时不会将对应的包加入到war包的lib中；
- compile是两种情况都要调用对应的包。
    
### 其他需要注意的问题
- 打成的包的名称应该和application.properties中的server.context-path保持一致；
- 按上述方式修改后，仍可以使用bootRun来使用内嵌的Tomcat进行调试；
