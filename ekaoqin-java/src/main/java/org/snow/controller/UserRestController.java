package org.snow.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.snow.model.security.User;
import org.snow.security.JwtTokenUtil;
import org.snow.security.JwtUser;
import org.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "用户RestController", tags = {"用户信息Restful接口"})
public class UserRestController {

    @Autowired
    private UserService userService;
    //认证相关
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @ApiOperation(value = "获取全部用户信息", notes = "获取全部用户信息")
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "根据id获取一条用户信息", notes = "根据url的id来获取一条用户信息")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(
        @ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "新增用户信息", notes = "新增用户信息（不用填写id）")
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public Boolean updateUser(
        @ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Long id,
        @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(
        @ApiParam(name = "id", value = "用户ID", required = true) @PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @ApiOperation(value = "查询用户信息", notes = "根据POST的传递条件查询用户信息（目前支持username，enable）")
    @RequestMapping(path = "/user/search", method = RequestMethod.POST)
    public PageInfo<User> searchUsers(
        @RequestBody User user,
        @ApiParam(name = "page", value = "查询页码", required = true) @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @ApiParam(name = "size", value = "每页行数", required = true) @RequestParam(value = "size", required = false, defaultValue = "20") int size) {

        return userService.searchUsers(user, page, size);
    }

    @ApiOperation(value = "密码验证", notes = "用于用户修改密码时，新旧密码的验证")
    @RequestMapping(path = "/user/validatePassword", method = RequestMethod.POST)
    public Boolean validatePassword(
        @RequestBody String password,
        HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return userService.validatePassword(username, password);
    }
    @ApiOperation(value = "密码修改", notes = "用于用户修改自己的密码")
    @RequestMapping(path = "/user/changePassword", method = RequestMethod.POST)
    public Boolean changePassword(
        @RequestBody String password,
        HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return userService.changePassword(user.getId(), password);
    }


    //认证相关
    @ApiOperation(value = "获得登陆用户信息", notes = "获得用户信息,根据token获得用户信息，无参数")
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


}
