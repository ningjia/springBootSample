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

---

## SpringBoot+Mybatis集成分页插件PageHelper
- @See: https://github.com/pagehelper/Mybatis-PageHelper/tree/master/wikis/zh
- @See: https://github.com/pagehelper/pagehelper-spring-boot
### 1. build.gradle中配置依赖
```
dependencies {
    compile group: 'com.github.pagehelper', name: 'pagehelper', version: '4.1.0'
}
```

### 2. 添加配置类，注册分页插件PageHelper
```
import java.util.Properties;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import com.github.pagehelper.PageHelper;  
/*  
* 注册MyBatis分页插件PageHelper  
*/  
@Configuration  
public class MybatisConf {  
      @Bean  
      public PageHelper pageHelper() {  
         System.out.println("=========MyBatisConfiguration.pageHelper()");  
          PageHelper pageHelper = new PageHelper();  
          Properties p = new Properties();  
          p.setProperty("offsetAsPageNum", "true");  
          p.setProperty("rowBoundsWithCount", "true");  
          p.setProperty("reasonable", "true");  
          pageHelper.setProperties(p);  
          return pageHelper;  
      }  
}
```
### 3. 在代码中调用
```
/*  
 * 第一个参数是第几页；第二个参数是每页显示条数。  
 */  
PageHelper.startPage(1,2);  
userService.fingByName(name);  
```
### 4. 其他事项
- 只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页。。
    - 如：有一个查询数据的方法,写在了PageHelper.startPage(x, y)下面，但是这个查询方法里面
包含两个查询语句的话，该插件就只会对第一个查询语句所查询的数据进行分页。
- 分页插件不支持带有for update语句的分页。
- 分页插件不支持嵌套结果映射。
    - 由于嵌套结果方式会导致结果集被折叠，因此分页查询的结果在折叠后总数会减少，所以无法保证分页结果数量正确。
