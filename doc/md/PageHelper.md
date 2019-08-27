## SpringBoot+Mybatis集成分页插件PageHelper
- @See: https://github.com/pagehelper/Mybatis-PageHelper/tree/master/wikis/zh
- @See: https://github.com/pagehelper/pagehelper-spring-boot
### 1. build.gradle中配置依赖
```groovy
dependencies {
    compile group: 'com.github.pagehelper', name: 'pagehelper', version: '4.1.0'
}
```

### 2. 添加配置类，注册分页插件PageHelper
```java
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
