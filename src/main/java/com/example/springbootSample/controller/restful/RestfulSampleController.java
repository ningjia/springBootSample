package com.example.springbootSample.controller.restful;

import com.example.springbootSample.model.User;
import com.example.springbootSample.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/users", description = "用户对象user的RESTful操作")
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
     * @param pageNo 分页参数：当前页数；可选参数，默认为0；
     * @param pageSize 分页参数：每页记录数；可选参数，默认为0；
     *
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="支持分页，需要传入pageNo和pageSize。否则默认返回所有数据。")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "当前页数", required = false, dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, dataType = "int", paramType = "query")
    })
    @GetMapping(value="/") //等同于RequestMapping(method=RequestMethod.GET)
    public List<User> getUserList(@RequestParam(value="pageNo",required=false,defaultValue="0") int pageNo, @RequestParam(value="pageSize",required=false,defaultValue="0")  int pageSize) {
        List<User> users = userService.getUserList(pageNo, pageSize);
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
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
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
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
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
    @ApiOperation(value="更新用户的详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
    })
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
    @ApiOperation(value="删除用户", notes="根据url的id来删除指定的用户数据")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path")
    @DeleteMapping(value="/{id}") //等同于RequestMapping(method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "success";
    }

}
